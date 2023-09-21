package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task in a calendar.
 */
public class Task extends AcalendarItem {
  private boolean isComplete;

  /**
   * Constructs a Task object with default values.
   */
  public Task() {
    super();
    isComplete = false;
  }

  /**
   * Constructs a Task object with the specified parameters.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param dayOfWeek   the day of the week the task occurs on
   * @param category    the category of the task
   * @param type        the type of the task
   * @param isComplete  the completion status of the task
   */
  @JsonCreator
  public Task(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("dayOfWeek") Day dayOfWeek,
      @JsonProperty("category") String category,
      @JsonProperty("type") String type,
      @JsonProperty("isComplete") boolean isComplete) {
    super(name, description, dayOfWeek, category);
    this.isComplete = isComplete;
  }

  /**
   * Constructs a Task object with the specified parameters.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param dayOfWeek   the day of the week the task occurs on
   * @param category    the category of the task
   */
  public Task(String name, String description, Day dayOfWeek, String category) {
    super(name, description, dayOfWeek, category);
    isComplete = false;
  //  type = "Task";
  }

  /**
   * Returns the completion status of the task.
   *
   * @return true if the task is complete, false otherwise
   */
  public boolean getIsComplete() {
    return isComplete;
  }

  /**
   * Sets the completion status of the task.
   *
   * @param status the completion status of the task
   */
  public void setIsComplete(boolean status) {
    isComplete = status;
  }

  /**
   * Returns a string representation of the task.
   *
   * @return a string representation of the task
   */
  public String taskToString(String description) {
    return "Name: " + this.getName() + "\n"
        + "Description: " + description + "\n"
        + "Category: " + this.getCategory();
  }

  /**
   * Returns the type of the task.
   *
   * @return the type of the task
   */
  public String getType() {
    return "Task";
  }
}
