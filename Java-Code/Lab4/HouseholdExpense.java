import java.time.LocalDate;

public class HouseholdExpense {
    private final LocalDate date;
    private final String category;
    private final String description;
    private final double amount;

    public HouseholdExpense(LocalDate date, String category, String description, double amount) {
        if (date == null) throw new IllegalArgumentException("กรุณาระบุวันที่");
        if (category == null || category.trim().isEmpty()) throw new IllegalArgumentException("กรุณาเลือกหมวดหมู่");
        if (!Double.isFinite(amount) || amount <= 0) throw new IllegalArgumentException("จำนวนเงินต้องมากกว่า 0");
        this.date = date;
        this.category = category.trim();
        this.description = description == null ? "" : description.trim();
        this.amount = amount;
    }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}
