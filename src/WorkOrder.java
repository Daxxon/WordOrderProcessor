public class WorkOrder {

  private int id;
  private String description;
  private String senderName;
  private Status status;

  public WorkOrder() {}

  public WorkOrder(int id, String description, String senderName, Status status) {
    this.id = id;
    this.description = description;
    this. senderName = senderName;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSenderName() {
    return this.senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String toString() {
    return this.getId() + ", " + this.getDescription() + ", " + this.getSenderName();
  }
}
