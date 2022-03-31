package mirbozorgi.base.domain.offlineChat;

public class ReportData {

  private long date;

  private Boolean report;

  private String reason;

  private String userReporterUuId;

  public ReportData(
      long date,
      Boolean report,
      String reason,
      String userReporterUuId) {
    this.date = date;
    this.report = report;
    this.reason = reason;
    this.userReporterUuId = userReporterUuId;
  }

  public ReportData() {
  }

  public long getDate() {
    return date;
  }

  public Boolean getReport() {
    return report;
  }

  public String getReason() {
    return reason;
  }

  public String getUserReporterUuId() {
    return userReporterUuId;
  }
}
