package cs3500.pa05.model;

/**
 * Represents a calendar item abstract class.
 */
public abstract class AcalendarItem implements IcalendarItem {

  private String name;
  private String description;
  private Day dayOfWeek;
  private String category;

  /**
   * Constructs an empty calendar item.
   */
  public AcalendarItem() {
    this.name = "";
    this.description = "";
    this.dayOfWeek = null;
    this.category = null;
  }

  /**
   * Constructs a calendar item with the specified properties.
   *
   * @param name        The name of the calendar item.
   * @param description The description of the calendar item.
   * @param dayOfWeek   The day of the week for the calendar item.
   * @param category    The category of the calendar item.
   */
  public AcalendarItem(String name, String description, Day dayOfWeek, String category) {
    this.name = name;
    this.description = description;
    this.dayOfWeek = dayOfWeek;
    this.category = category;
  }

  /**
   * Retrieves the name of the calendar item.
   *
   * @return The name of the calendar item.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the calendar item.
   *
   * @param name The name of the calendar item.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the description of the calendar item.
   *
   * @return The description of the calendar item.
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the calendar item.
   *
   * @param description The description of the calendar item.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Retrieves the day of the week for the calendar item.
   *
   * @return The day of the week for the calendar item.
   */
  @Override
  public Day getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Sets the day of the week for the calendar item.
   *
   * @param dayOfWeek The day of the week for the calendar item.
   */
  @Override
  public void setDayOfWeek(Day dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Retrieves the category of the calendar item.
   *
   * @return The category of the calendar item.
   */
  @Override
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category of the calendar item.
   *
   * @param category The category of the calendar item.
   */
  @Override
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Sets the completion status of the calendar item.
   * This method is not implemented in the provided code snippet.
   *
   * @param status The completion status of the calendar item.
   */
  @Override
  public void setIsComplete(boolean status) {
    // Implementation not provided in the code snippet
  }
}
