package cs3500.pa05.model;

/**
 * represents a calendar item
 */
public interface IcalendarItem {

  /**
   * Returns the name of the calendar item.
   *
   * @return The name of the calendar item.
   */
  String getName();

  /**
   * Returns the description of the calendar item.
   *
   * @return The description of the calendar item.
   */
  String getDescription();

  /**
   * Returns the day of the week for the calendar item.
   *
   * @return The day of the week for the calendar item.
   */
  Day getDayOfWeek();

  /**
   * Returns the category of the calendar item.
   *
   * @return The category of the calendar item.
   */
  String getCategory();

  /**
   * Sets the name of the calendar item.
   *
   * @param name The name of the calendar item.
   */
  void setName(String name);

  /**
   * Sets the description of the calendar item.
   *
   * @param description The description of the calendar item.
   */
  void setDescription(String description);

  /**
   * Sets the day of the week for the calendar item.
   *
   * @param dayOfWeek The day of the week for the calendar item.
   */
  void setDayOfWeek(Day dayOfWeek);

  /**
   * Sets the category of the calendar item.
   *
   * @param category The category of the calendar item.
   */
  void setCategory(String category);

  /**
   * Sets the completion status of the calendar item.
   *
   * @param status The completion status of the calendar item.
   */
  void setIsComplete(boolean status);
}
