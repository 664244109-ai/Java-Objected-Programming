public abstract class Document {
    protected String docId,title,createdBy;
    public Document(String docId,String title,String createdBy){this.docId=docId;this.title=title;this.createdBy=createdBy;}
    public abstract String getDocumentType(); public abstract void printContent();
    public void printHeader(){System.out.printf("[%s] %s : %s (Created by: %s)%n",getDocumentType(),docId,title,createdBy);}
}
