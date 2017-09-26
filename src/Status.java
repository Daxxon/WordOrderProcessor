public enum Status {

  INITIAL ("initial"),
  ASSIGNED ("assigned"),
  IN_PROGRESS ("inProgress"),
  DONE ("done");

  private String englishName;

  Status (String englishName) {
    this.englishName = englishName;
  }

  public String getEnglishName() {
    return this.englishName;
  }
}