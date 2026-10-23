public class Report extends Document {
    private final String summary;
    public Report(String title, String createdBy, String summary) { super(title, createdBy); this.summary=summary; }
    @Override public void printContent() { showHeader(); System.out.println("สรุป: " + summary); }
}
