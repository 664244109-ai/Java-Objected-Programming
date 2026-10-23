import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

public class HouseholdExpenseApp extends JFrame {
    static {
        ThaiFontSupport.configureSwingDefaults();
    }

    private static final String[] CATEGORIES = {"อาหาร", "ที่อยู่อาศัย", "เดินทาง", "สาธารณูปโภค", "สุขภาพ", "การศึกษา", "ของใช้ในบ้าน", "อื่น ๆ"};
    private final List<HouseholdExpense> expenses = new ArrayList<>();
    private final JTextField dateField = new JTextField(LocalDate.now().toString(), 10);
    private final JComboBox<String> categoryBox = new JComboBox<>(CATEGORIES);
    private final JTextField descriptionField = new JTextField(12);
    private final JTextField amountField = new JTextField(9);
    private final JComboBox<YearMonth> monthBox = new JComboBox<>();
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"วันที่", "หมวดหมู่", "รายละเอียด", "จำนวนเงิน (บาท)"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable table = new JTable(tableModel);
    private final JLabel currentTotalLabel = new JLabel("0.00 บาท");
    private final JLabel previousTotalLabel = new JLabel("0.00 บาท");
    private final JLabel differenceLabel = new JLabel("0.00 บาท");
    private final ExpenseChartPanel chartPanel = new ExpenseChartPanel(expenses);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);

    public HouseholdExpenseApp() {
        super("Lab 4 | ค่าใช้จ่ายครัวเรือน");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 720));
        setLocationRelativeTo(null);
        seedSampleData();
        for (int i = 0; i < 12; i++) monthBox.addItem(YearMonth.now().minusMonths(i));
        monthBox.setSelectedItem(YearMonth.now());
        buildUi();
        refreshAll();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        root.setBackground(new Color(245, 247, 251));
        setContentPane(root);

        JLabel title = new JLabel("ค่าใช้จ่ายครัวเรือน");
        title.setFont(ThaiFontSupport.create(Font.BOLD, 24));
        JLabel subtitle = new JLabel("บันทึกรายการและเปรียบเทียบค่าใช้จ่ายรายเดือน");
        subtitle.setFont(ThaiFontSupport.create(Font.PLAIN, 13));
        JPanel heading = new JPanel(new GridLayout(2, 1, 0, 4));
        heading.setOpaque(false); heading.add(title); heading.add(subtitle);

        JPanel input = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        input.setBackground(Color.WHITE);
        input.setBorder(BorderFactory.createTitledBorder("เพิ่มรายการค่าใช้จ่าย"));
        input.add(new JLabel("วันที่ (yyyy-MM-dd)")); input.add(dateField);
        input.add(new JLabel("หมวดหมู่")); input.add(categoryBox);
        input.add(new JLabel("รายละเอียด")); input.add(descriptionField);
        input.add(new JLabel("จำนวนเงิน")); input.add(amountField);
        JButton addButton = new JButton("เพิ่มรายการ");
        addButton.addActionListener(e -> addExpense()); input.add(addButton);

        JPanel north = new JPanel(new BorderLayout(0, 12));
        north.setOpaque(false); north.add(heading, BorderLayout.NORTH); north.add(input, BorderLayout.SOUTH);
        root.add(north, BorderLayout.NORTH);

        JPanel dashboard = new JPanel(new BorderLayout(8, 8));
        dashboard.setOpaque(false);
        JPanel monthSelect = new JPanel(new FlowLayout(FlowLayout.LEFT)); monthSelect.setOpaque(false);
        monthSelect.add(new JLabel("เดือนที่ต้องการดู:")); monthSelect.add(monthBox);
        monthBox.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean focus) {
                super.getListCellRendererComponent(list, value, index, selected, focus);
                if (value instanceof YearMonth) setText(((YearMonth) value).toString());
                return this;
            }
        });
        monthBox.addActionListener(e -> refreshAll());
        dashboard.add(monthSelect, BorderLayout.NORTH);
        dashboard.add(buildSummaryCards(), BorderLayout.CENTER);
        dashboard.add(chartPanel, BorderLayout.SOUTH);
        root.add(dashboard, BorderLayout.CENTER);

        table.setRowHeight(24);
        table.setFont(ThaiFontSupport.create(Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("รายการค่าใช้จ่าย"));
        scroll.setPreferredSize(new Dimension(900, 180));
        root.add(scroll, BorderLayout.SOUTH);
    }

    private JPanel buildSummaryCards() {
        JPanel cards = new JPanel(new GridLayout(1, 3, 10, 0)); cards.setOpaque(false);
        cards.add(card("รวมเดือนที่เลือก", currentTotalLabel, new Color(28, 108, 180)));
        cards.add(card("รวมเดือนก่อนหน้า", previousTotalLabel, new Color(110, 92, 170)));
        cards.add(card("ส่วนต่างจากเดือนก่อน", differenceLabel, new Color(28, 145, 112)));
        return cards;
    }
    private JPanel card(String title, JLabel value, Color accent) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 4)); panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, accent), BorderFactory.createEmptyBorder(10, 12, 10, 12)));
        JLabel label = new JLabel(title); label.setFont(ThaiFontSupport.create(Font.PLAIN, 12));
        value.setFont(ThaiFontSupport.create(Font.BOLD, 20)); value.setForeground(accent);
        panel.add(label); panel.add(value); return panel;
    }

    private void addExpense() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText().trim(), dateFormatter);
            double amount = Double.parseDouble(amountField.getText().trim().replace(",", ""));
            expenses.add(new HouseholdExpense(date, (String) categoryBox.getSelectedItem(), descriptionField.getText(), amount));
            amountField.setText(""); descriptionField.setText("");
            refreshAll();
        } catch (DateTimeParseException ex) {
            showError("รูปแบบวันที่ไม่ถูกต้อง กรุณาใช้ yyyy-MM-dd");
        } catch (NumberFormatException ex) {
            showError("กรุณากรอกจำนวนเงินเป็นตัวเลข");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }
    private void showError(String message) { JOptionPane.showMessageDialog(this, message, "ข้อมูลไม่ถูกต้อง", JOptionPane.WARNING_MESSAGE); }

    private void refreshAll() {
        YearMonth selected = (YearMonth) monthBox.getSelectedItem();
        if (selected == null) return;
        YearMonth previous = selected.minusMonths(1);
        double currentTotal = totalFor(selected), previousTotal = totalFor(previous);
        currentTotalLabel.setText(String.format("%,.2f บาท", currentTotal));
        previousTotalLabel.setText(String.format("%,.2f บาท", previousTotal));
        double difference = currentTotal - previousTotal;
        differenceLabel.setText(String.format("%s%,.2f บาท", difference > 0 ? "+" : "", difference));
        differenceLabel.setForeground(difference > 0 ? new Color(196, 70, 60) : new Color(28, 145, 112));
        tableModel.setRowCount(0);
        expenses.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).forEach(x ->
                tableModel.addRow(new Object[]{x.getDate(), x.getCategory(), x.getDescription(), String.format("%,.2f", x.getAmount())}));
        chartPanel.setComparisonMonths(selected, previous);
        chartPanel.revalidate(); chartPanel.repaint();
    }
    private double totalFor(YearMonth month) {
        return expenses.stream().filter(e -> YearMonth.from(e.getDate()).equals(month)).mapToDouble(HouseholdExpense::getAmount).sum();
    }
    private void seedSampleData() {
        YearMonth now = YearMonth.now(), prior = now.minusMonths(1);
        expenses.add(new HouseholdExpense(prior.atDay(3), "อาหาร", "ซื้อของเข้าบ้าน", 4200));
        expenses.add(new HouseholdExpense(prior.atDay(8), "ที่อยู่อาศัย", "ค่าน้ำ", 650));
        expenses.add(new HouseholdExpense(prior.atDay(10), "สาธารณูปโภค", "ค่าไฟ", 2100));
        expenses.add(new HouseholdExpense(prior.atDay(14), "เดินทาง", "ค่าน้ำมัน", 1800));
        expenses.add(new HouseholdExpense(prior.atDay(20), "อาหาร", "ตลาด", 3200));
        expenses.add(new HouseholdExpense(now.atDay(2), "อาหาร", "ซื้อของเข้าบ้าน", 3900));
        expenses.add(new HouseholdExpense(now.atDay(7), "ที่อยู่อาศัย", "ค่าเช่าบ้าน", 8000));
        expenses.add(new HouseholdExpense(now.atDay(12), "สาธารณูปโภค", "ค่าไฟและอินเทอร์เน็ต", 2600));
        expenses.add(new HouseholdExpense(now.atDay(16), "เดินทาง", "ค่าเดินทาง", 1400));
        expenses.add(new HouseholdExpense(now.atDay(21), "สุขภาพ", "ยาและเวชภัณฑ์", 850));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HouseholdExpenseApp().setVisible(true));
    }
}
