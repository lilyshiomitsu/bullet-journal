package cs3500.pa05.model;

/**
 * represents the time in the event class
 */
public class Time {

  private int hour;
  private int minute;

  /**
   * Constructs a Time object with default values (0 hour and 0 minute).
   */
  public Time() {
    this.hour = 0;
    this.minute = 0;
  }

  /**
   * Constructs a Time object with the specified hour and minute.
   *
   * @param hour   the hour value (between 0 and 23)
   * @param minute the minute value (between 0 and 59)
   * @throws IllegalArgumentException if the hour or minute value is out of range
   */
  public Time(int hour, int minute) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("please input hour between 0 and 23");
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("please input minute between 0 and 59");
    }
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * Returns the hour value of the time.
   *
   * @return the hour value
   */
  public int getHour() {
    return this.hour;
  }

  /**
   * Returns the minute value of the time.
   *
   * @return the minute value
   */
  public int getMinute() {
    return this.minute;
  }

  /**
   * Sets the hour value of the time.
   *
   * @param hour the hour value to be set
   */
  public void setHour(int hour) {
    this.hour = hour;
  }

  /**
   * Sets the minute value of the time.
   *
   * @param minute the minute value to be set
   */
  public void setMinute(int minute) {
    this.minute = minute;
  }

  /**
   * Returns a string representation of the time in the format "hour:minute".
   *
   * @return a string representation of the time
   */
  public String timeToString() {
    return this.hour + ":" + this.minute;
  }


}
