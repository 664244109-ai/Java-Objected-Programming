public class LeaveRequest {
    public static void requestLeave(int days) throws InvalidLeaveDaysException {
        if (days <= 0 || days > 30) throw new InvalidLeaveDaysException("จำนวนวันลาไม่ถูกต้อง: " + days + " วัน");
        System.out.println("อนุมัติการลา " + days + " วัน เรียบร้อยแล้ว");
    }
}
