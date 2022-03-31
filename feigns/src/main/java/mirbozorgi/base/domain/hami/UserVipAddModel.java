package mirbozorgi.base.domain.hami;

public class UserVipAddModel {


  private long startDate;

  private long endDate;

  private long periodExtraTime;


  public UserVipAddModel(long startDate, long endDate, long periodExtraTime) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.periodExtraTime = periodExtraTime;
  }

  public long getPeriodExtraTime() {
    return periodExtraTime;
  }

  public long getStartDate() {
    return startDate;
  }

  public long getEndDate() {
    return endDate;
  }
}
