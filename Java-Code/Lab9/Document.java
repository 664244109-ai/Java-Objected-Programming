public abstract class Document {
    protected String title;
    protected String createdBy;
    public Document(String title, String createdBy) { this.title=title; this.createdBy=createdBy; }
    public abstract void printContent();
    public void showHeader() { System.out.println("เอกสาร: " + title + " | ผู้จัดทำ: " + createdBy); }
}
