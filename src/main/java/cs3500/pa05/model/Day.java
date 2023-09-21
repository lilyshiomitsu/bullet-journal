package cs3500.pa05.model;

/**
 * Represents the days of the week.
 */
public enum Day {
  /**
   * Represents Sunday.
   */
  SUNDAY(0),

  /**
   * Represents Monday.
   */
  MONDAY(1),

  /**
   * Represents Tuesday.
   */
  TUESDAY(2),

  /**
   * Represents Wednesday.
   */
  WEDNESDAY(3),

  /**
   * Represents Thursday.
   */
  THURSDAY(4),

  /**
   * Represents Friday.
   */
  FRIDAY(5),

  /**
   * Represents Saturday.
   */
  SATURDAY(6);

  private final int dayNumber;

  /**
   * Constructs a Day enum constant with the specified day number.
   *
   * @param value The day number.
   */
  Day(int value) {
    dayNumber = value;
  }

  /**
   * Returns the day number associated with this Day.
   *
   * @return The day number.
   */
  public int getDayNumber() {
    return dayNumber;
  }
}
