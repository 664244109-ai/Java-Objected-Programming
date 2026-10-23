public class ExceptionTest {
    public static void main(String[] args) {
        Inventory stock = new Inventory("กระดาษ A4", 50);
        try {
            stock.withdrawStock(30);
            stock.withdrawStock(40);
        } catch (InsufficientStockException e) {
            System.out.println("เกิดข้อผิดพลาด: " + e.getMessage());
        } finally { System.out.println("ตรวจสอบสต็อกเสร็จสิ้น"); }
        try { LeaveRequest.requestLeave(5); LeaveRequest.requestLeave(-2); }
        catch (InvalidLeaveDaysException e) { System.out.println("เกิดข้อผิดพลาด: " + e.getMessage()); }
        try { registerEmployee("E001", "E001"); }
        catch (DuplicateEmployeeIdException e) { System.out.println("เกิดข้อผิดพลาด: " + e.getMessage()); }
        try { stock.withdrawStock(100); LeaveRequest.requestLeave(40); }
        catch (InsufficientStockException | InvalidLeaveDaysException e) { System.out.println("multi-catch: " + e.getMessage()); }
    }
    static void registerEmployee(String existing, String candidate) throws DuplicateEmployeeIdException {
        if (existing.equals(candidate)) throw new DuplicateEmployeeIdException("รหัสพนักงานซ้ำ: " + candidate);
    }
}
