import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Lab3 extends JFrame {
    
    private static final int YEAR = 2027;
    private static final String[] THAI_MONTHS = {
        "มกราคม 2570", "กุมภาพันธ์ 2570", "มีนาคม 2570", "เมษายน 2570",
        "พฤษภาคม 2570", "มิถุนายน 2570", "กรกฎาคม 2570", "สิงหาคม 2570",
        "กันยายน 2570", "ตุลาคม 2570", "พฤศจิกายน 2570", "ธันวาคม 2570"
    };
    
    private static final String[] CHINESE_MONTH_NAMES = {
        "๑๒", "๑", "๒", "๓", "๔", "๕", "๖", "๗", "๘", "๙", "๑๐", "๑๑"
    };
    
    private static final String[] DAY_NAMES_TH = {"อา", "จ", "อ", "พ", "พฤ", "ศ", "ส"};
    private static final String[] CHINESE_DAY_NAMES = {"日", "一", "二", "三", "四", "五", "六"};
    
    private static final String[] LUNAR_DAYS = {
        "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
        "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
        "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"
    };
    
    private static final int[][][] LUNAR_DATA = generateLunarData();
    private static final String[] FESTIVAL_DATA = generateFestivalData();
    
    private static final Color CHINESE_RED = new Color(196, 30, 30);
    private static final Color DARK_RED = new Color(160, 20, 20);
    private static final Color GOLD = new Color(212, 175, 55);
    private static final Color CREAM = new Color(255, 248, 230);
    private static final Color FESTIVAL_BG = new Color(255, 215, 215);
    private static final Color SUN_BG = new Color(255, 242, 242);
    
    public Lab3() {
        setTitle("ปฏิทินจีน 2570 ปีมะแม (丁未年)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(CREAM);
        
        JPanel headerPanel = createHeader();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel monthsPanel = new JPanel(new GridLayout(4, 3, 12, 12));
        monthsPanel.setBackground(CREAM);
        monthsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        for (int m = 0; m < 12; m++) {
            monthsPanel.add(createMonthPanel(m));
        }
        
        JScrollPane scrollPane = new JScrollPane(monthsPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(CREAM);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel footerPanel = createFooter();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private JPanel createHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(180, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("ปฏิทินจีน 2570", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sarabun", Font.BOLD, 42));
        titleLabel.setForeground(GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subLabel = new JLabel(
            "ปีมะแม 丁未年 (Year of the Goat)  |  พ.ศ. 2570  ค.ศ. 2027  |  ตรุษจีน 6 กุมภาพันธ์ 2570",
            SwingConstants.CENTER);
        subLabel.setFont(new Font("Sarabun", Font.PLAIN, 20));
        subLabel.setForeground(new Color(255, 220, 180));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(subLabel);
        
        return panel;
    }
    
    private JPanel createFooter() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(180, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        
        JLabel footerLabel = new JLabel(
            "☯  ฤกษ์งามยามดีตามปฏิทินจีนโบราณ  ｜  ปีมะแม 丁未  ｜  เหมาะแก่การเสี่ยงโชค  ☯");
        footerLabel.setFont(new Font("Sarabun", Font.PLAIN, 16));
        footerLabel.setForeground(new Color(255, 220, 150));
        
        panel.add(footerLabel);
        return panel;
    }
    
    private JPanel createMonthPanel(int monthIndex) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 150, 100), 2),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        panel.setBackground(Color.WHITE);
        
        JPanel monthHeader = new JPanel(new BorderLayout());
        monthHeader.setBackground(CHINESE_RED);
        monthHeader.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        
        JLabel monthLabel = new JLabel(THAI_MONTHS[monthIndex], SwingConstants.CENTER);
        monthLabel.setFont(new Font("Sarabun", Font.BOLD, 18));
        monthLabel.setForeground(GOLD);
        
        JLabel zodiacLabel = new JLabel("เดือน " + CHINESE_MONTH_NAMES[monthIndex], SwingConstants.CENTER);
        zodiacLabel.setFont(new Font("Sarabun", Font.PLAIN, 13));
        zodiacLabel.setForeground(new Color(255, 200, 150));
        
        monthHeader.add(monthLabel, BorderLayout.CENTER);
        monthHeader.add(zodiacLabel, BorderLayout.SOUTH);
        
        JPanel dayHeaderPanel = new JPanel(new GridLayout(1, 7, 1, 0));
        dayHeaderPanel.setBackground(DARK_RED);
        
        for (int d = 0; d < 7; d++) {
            JPanel dayCell = new JPanel(new BorderLayout());
            dayCell.setBackground(DARK_RED);
            dayCell.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            
            JLabel thaiLabel = new JLabel(DAY_NAMES_TH[d], SwingConstants.CENTER);
            thaiLabel.setFont(new Font("Sarabun", Font.BOLD, 12));
            thaiLabel.setForeground(GOLD);
            
            JLabel cnLabel = new JLabel(CHINESE_DAY_NAMES[d], SwingConstants.CENTER);
            cnLabel.setFont(new Font("Sarabun", Font.PLAIN, 10));
            cnLabel.setForeground(new Color(255, 200, 150));
            
            dayCell.add(thaiLabel, BorderLayout.CENTER);
            dayCell.add(cnLabel, BorderLayout.SOUTH);
            dayHeaderPanel.add(dayCell);
        }
        
        JPanel daysGrid = new JPanel(new GridLayout(0, 7, 1, 1));
        daysGrid.setBackground(new Color(220, 200, 170));
        
        Calendar cal = new GregorianCalendar(YEAR, monthIndex, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        for (int i = 0; i < startDay; i++) {
            daysGrid.add(createEmptyDayCell());
        }
        
        for (int day = 1; day <= daysInMonth; day++) {
            int[] lunarInfo = getLunarDate(monthIndex, day);
            String festival = getFestivalForDay(monthIndex, day);
            daysGrid.add(createDayCell(day, lunarInfo, festival, monthIndex));
        }
        
        int totalCells = startDay + daysInMonth;
        int remaining = (7 - (totalCells % 7)) % 7;
        for (int i = 0; i < remaining; i++) {
            daysGrid.add(createEmptyDayCell());
        }
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(dayHeaderPanel, BorderLayout.NORTH);
        contentPanel.add(daysGrid, BorderLayout.CENTER);
        
        panel.add(monthHeader, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createDayCell(int day, int[] lunarInfo, String festival, int monthIndex) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(new Color(230, 210, 190), 1));
        
        Calendar cal = new GregorianCalendar(YEAR, monthIndex, day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        boolean isSunday = (dayOfWeek == Calendar.SUNDAY);
        
        boolean hasFestival = (festival != null);
        boolean isFirstOfLunarMonth = lunarInfo != null && lunarInfo[0] == 1 && lunarInfo[1] == 1;
        
        if (hasFestival) {
            panel.setBackground(FESTIVAL_BG);
        } else if (isFirstOfLunarMonth) {
            panel.setBackground(new Color(255, 200, 180));
        } else if (isSunday) {
            panel.setBackground(SUN_BG);
        } else {
            panel.setBackground(Color.WHITE);
        }
        
        JLabel dayLabel = new JLabel(String.valueOf(day), SwingConstants.RIGHT);
        dayLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dayLabel.setForeground((isSunday || hasFestival) ? CHINESE_RED : Color.DARK_GRAY);
        dayLabel.setBorder(BorderFactory.createEmptyBorder(1, 2, 0, 2));
        
        String lunarText = "";
        if (lunarInfo != null) {
            if (lunarInfo[0] == 1) {
                lunarText = LUNAR_DAYS[lunarInfo[1] - 1];
            } else if (lunarInfo[1] == 15) {
                lunarText = LUNAR_DAYS[14];
            } else if (lunarInfo[1] % 5 == 0) {
                lunarText = LUNAR_DAYS[lunarInfo[1] - 1];
            }
        }
        
        boolean isFullMoon = (lunarInfo != null && lunarInfo[1] == 15);
        
        JLabel lunarLabel = new JLabel(lunarText, SwingConstants.CENTER);
        lunarLabel.setFont(new Font("Sarabun", isFullMoon ? Font.BOLD : Font.PLAIN, isFullMoon ? 10 : 9));
        lunarLabel.setForeground(isFullMoon ? new Color(180, 50, 50) : new Color(100, 50, 50));
        lunarLabel.setBorder(BorderFactory.createEmptyBorder(0, 1, 1, 1));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(dayLabel, BorderLayout.EAST);
        
        panel.add(topPanel, BorderLayout.NORTH);
        
        if (hasFestival) {
            JLabel festLabel = new JLabel(festival, SwingConstants.CENTER);
            festLabel.setFont(new Font("Sarabun", Font.BOLD, 9));
            festLabel.setForeground(CHINESE_RED);
            festLabel.setBorder(BorderFactory.createEmptyBorder(0, 1, 1, 1));
            
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setOpaque(false);
            bottomPanel.add(lunarLabel, BorderLayout.NORTH);
            bottomPanel.add(festLabel, BorderLayout.SOUTH);
            panel.add(bottomPanel, BorderLayout.CENTER);
        } else {
            panel.add(lunarLabel, BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    private JPanel createEmptyDayCell() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 240, 230));
        panel.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210), 1));
        return panel;
    }
    
    private static int[][][] generateLunarData() {
        int[][][] data = new int[12][][];
        int[] lunarMonthLengths = {30, 29, 30, 29, 30, 29, 30, 30, 29, 30, 29, 30, 29};
        
        // Month 0 - January (in previous lunar year)
        data[0] = new int[31][];
        for (int d = 0; d < 31; d++) {
            int day = d + 1;
            int lunarDay;
            boolean isFirst = false;
            if (day <= 6) {
                lunarDay = 24 + day - 1;
            } else {
                lunarDay = day - 6;
                if (lunarDay == 1) isFirst = true;
            }
            if (lunarDay > 30) lunarDay -= 30;
            data[0][d] = new int[]{isFirst ? 1 : 0, lunarDay};
        }
        
        // Month 1 - February (CNNY Feb 6 = 正月初一)
        data[1] = new int[28][];
        for (int d = 0; d < 28; d++) {
            int day = d + 1;
            int lunarDay;
            boolean isFirst = false;
            if (day <= 5) {
                lunarDay = day + 25;
            } else {
                lunarDay = day - 5;
                if (lunarDay == 1) isFirst = true;
            }
            data[1][d] = new int[]{isFirst ? 1 : 0, lunarDay > 30 ? lunarDay - 30 : lunarDay};
        }
        
        // Months 2-11 (March to December)
        int curLunarMonth = 1;
        int curLunarDay = 2;
        
        for (int m = 2; m < 12; m++) {
            int daysInMonth;
            switch (m) {
                case 2: daysInMonth = 31; break;
                case 3: daysInMonth = 30; break;
                case 4: daysInMonth = 31; break;
                case 5: daysInMonth = 30; break;
                case 6: daysInMonth = 31; break;
                case 7: daysInMonth = 31; break;
                case 8: daysInMonth = 30; break;
                case 9: daysInMonth = 31; break;
                case 10: daysInMonth = 30; break;
                case 11: daysInMonth = 31; break;
                default: daysInMonth = 30;
            }
            
            data[m] = new int[daysInMonth][];
            for (int d = 0; d < daysInMonth; d++) {
                curLunarDay++;
                int monthLen = lunarMonthLengths[curLunarMonth - 1];
                if (curLunarDay > monthLen) {
                    curLunarDay = 1;
                    curLunarMonth++;
                    if (curLunarMonth > 12) curLunarMonth = 1;
                }
                boolean isFirst = (curLunarDay == 1);
                data[m][d] = new int[]{isFirst ? 1 : 0, curLunarDay};
            }
        }
        
        return data;
    }
    
    private static String[] generateFestivalData() {
        String[] data = new String[366];
        data[0] = "วันขึ้นปีใหม่";        // Jan 1
        data[36] = "ตรุษจีนวัน1";          // Feb 6
        data[37] = "ตรุษจีนวัน2";          // Feb 7
        data[38] = "ตรุษจีนวัน3";          // Feb 8
        data[64] = "วันมาฆบูชา";           // Mar 6
        data[94] = "เช็งเม้ง";              // Apr 5
        data[102] = "วันสงกรานต์";         // Apr 13
        data[103] = "วันสงกรานต์";         // Apr 14
        data[104] = "วันสงกรานต์";         // Apr 15
        data[120] = "วันแรงงาน";           // May 1
        data[124] = "วันฉัตรมงคล";         // May 5
        data[138] = "วันวิสาขบูชา";         // May 19
        data[153] = "วันไหว้บ๊ะจ่าง";       // Jun 3
        data[155] = "วันสิ่งแวดล้อม";       // Jun 5
        data[187] = "วันอาสาฬหบูชา";       // Jul 7
        data[188] = "วันเข้าพรรษา";         // Jul 8
        data[223] = "วันแม่";               // Aug 12
        data[257] = "วันไหว้พระจันทร์";     // Sep 15
        data[271] = "วันสารทจีน";           // Sep 29
        data[285] = "วันปิยมหาราช";         // Oct 13
        data[295] = "วันลอยกระทง";          // Oct 23
        data[303] = "วันฮาโลวีน";           // Oct 31
        data[326] = "วันลอยกระทง";          // Nov 23
        data[338] = "วันพ่อ";               // Dec 5
        data[343] = "วันรัฐธรรมนูญ";        // Dec 10
        data[358] = "วันคริสต์มาส";         // Dec 25
        data[364] = "วันสิ้นปี";             // Dec 31
        return data;
    }
    
    private static int getAbsoluteDay(int month, int day) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int abs = 0;
        for (int m = 0; m < month; m++) abs += monthDays[m];
        return abs + day - 1;
    }
    
    private static int[] getLunarDate(int month, int day) {
        if (LUNAR_DATA != null && month >= 0 && month < 12 && day > 0 && day <= LUNAR_DATA[month].length) {
            return LUNAR_DATA[month][day - 1];
        }
        return null;
    }
    
    private static String getFestivalForDay(int month, int day) {
        if (FESTIVAL_DATA != null) {
            int absDay = getAbsoluteDay(month, day);
            if (absDay >= 0 && absDay < FESTIVAL_DATA.length) {
                return FESTIVAL_DATA[absDay];
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(Lab3::new);
    }
}