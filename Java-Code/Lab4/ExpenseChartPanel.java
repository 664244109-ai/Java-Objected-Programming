import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExpenseChartPanel extends JPanel {
    private final List<HouseholdExpense> expenses;
    private YearMonth currentMonth = YearMonth.now();
    private YearMonth previousMonth = currentMonth.minusMonths(1);
    private final Color currentColor = new Color(36, 135, 198);
    private final Color previousColor = new Color(153, 166, 181);
    private final Color[] palette = {new Color(36,135,198), new Color(245,157,61), new Color(76,175,126), new Color(153,111,190), new Color(225,95,95), new Color(73,170,177), new Color(204,176,65), new Color(118,133,151)};

    public ExpenseChartPanel(List<HouseholdExpense> expenses) {
        this.expenses = expenses;
        setOpaque(false); setPreferredSize(new Dimension(900, 230));
    }
    public void setComparisonMonths(YearMonth current, YearMonth previous) { currentMonth=current; previousMonth=previous; }

    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int gap = 12, width = (getWidth()-gap)/2, height=getHeight();
        drawCategoryComparison(g, 0, 0, width, height);
        drawMonthlyTrend(g, width+gap, 0, width, height);
        g.dispose();
    }
    private void drawPanel(Graphics2D g, int x, int y, int w, int h, String title) {
        g.setColor(Color.WHITE); g.fillRoundRect(x,y,w,h,14,14);
        g.setColor(new Color(220,225,232)); g.drawRoundRect(x,y,w-1,h-1,14,14);
        g.setColor(new Color(40,48,60)); g.setFont(ThaiFontSupport.create(Font.BOLD,14)); g.drawString(title,x+16,y+23);
    }
    private void drawCategoryComparison(Graphics2D g,int x,int y,int w,int h) {
        drawPanel(g,x,y,w,h,"เปรียบเทียบรายหมวดหมู่");
        int left=x+48, right=x+w-15, top=y+54, bottom=y+h-60;
        Map<String,double[]> values=new TreeMap<>();
        for(HouseholdExpense e:expenses) {
            YearMonth m=YearMonth.from(e.getDate());
            if(m.equals(currentMonth) || m.equals(previousMonth)) {
                double[] pair=values.computeIfAbsent(e.getCategory(),k->new double[2]);
                pair[m.equals(currentMonth)?0:1]+=e.getAmount();
            }
        }
        double max=values.values().stream().flatMapToDouble(a->Arrays.stream(a)).max().orElse(0);
        if(max<=0) max=1;
        g.setFont(ThaiFontSupport.create(Font.PLAIN,10));
        for(int i=0;i<=4;i++) {
            int yy=bottom-(bottom-top)*i/4;
            g.setColor(new Color(232,235,239)); g.drawLine(left,yy,right,yy);
            g.setColor(new Color(100,108,118)); g.drawString(String.format("%.0f",max*i/4),x+5,yy+4);
        }
        int n=Math.max(values.size(),1), slot=(right-left)/n, barW=Math.max(7,Math.min(18,slot/3));
        int index=0;
        for(Map.Entry<String,double[]> entry:values.entrySet()) {
            int center=left+slot*index+slot/2;
            int h1=(int)((bottom-top)*entry.getValue()[0]/max), h2=(int)((bottom-top)*entry.getValue()[1]/max);
            g.setColor(currentColor); g.fillRoundRect(center-barW-1,bottom-h1,barW,h1,5,5);
            g.setColor(previousColor); g.fillRoundRect(center+1,bottom-h2,barW,h2,5,5);
            g.setColor(new Color(65,72,80));
            String label=entry.getKey().length()>7?entry.getKey().substring(0,7):entry.getKey();
            FontMetrics fm=g.getFontMetrics(); g.drawString(label,center-fm.stringWidth(label)/2,bottom+16);
            index++;
        }
        drawLegend(g,x+14,y+h-18,currentColor,currentMonth.toString());
        drawLegend(g,x+125,y+h-18,previousColor,previousMonth.toString());
    }
    private void drawMonthlyTrend(Graphics2D g,int x,int y,int w,int h) {
        drawPanel(g,x,y,w,h,"แนวโน้มค่าใช้จ่าย 6 เดือน");
        int left=x+45,right=x+w-15,top=y+54,bottom=y+h-43;
        YearMonth first=currentMonth.minusMonths(5);
        double[] totals=new double[6];
        for(HouseholdExpense e:expenses) {
            YearMonth m=YearMonth.from(e.getDate());
            long offset=java.time.temporal.ChronoUnit.MONTHS.between(first,m);
            if(offset>=0&&offset<6) totals[(int)offset]+=e.getAmount();
        }
        double max=Arrays.stream(totals).max().orElse(0); if(max<=0)max=1;
        g.setFont(ThaiFontSupport.create(Font.PLAIN,10));
        for(int i=0;i<=4;i++) {
            int yy=bottom-(bottom-top)*i/4;
            g.setColor(new Color(232,235,239));g.drawLine(left,yy,right,yy);
            g.setColor(new Color(100,108,118));g.drawString(String.format("%.0f",max*i/4),x+4,yy+4);
        }
        int slot=(right-left)/6,barW=Math.max(10,Math.min(32,slot/2));
        for(int i=0;i<6;i++) {
            int bh=(int)((bottom-top)*totals[i]/max), bx=left+i*slot+(slot-barW)/2;
            g.setColor(palette[i%palette.length]);g.fillRoundRect(bx,bottom-bh,barW,bh,6,6);
            String label=first.plusMonths(i).toString().substring(2);
            g.setColor(new Color(65,72,80));FontMetrics fm=g.getFontMetrics();g.drawString(label,bx+(barW-fm.stringWidth(label))/2,bottom+16);
        }
    }
    private void drawLegend(Graphics2D g,int x,int y,Color color,String text) {
        g.setColor(color);g.fillRect(x,y-8,10,10);g.setColor(new Color(65,72,80));g.setFont(ThaiFontSupport.create(Font.PLAIN,10));g.drawString(text,x+14,y);
    }
}
