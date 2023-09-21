package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a model for a day in a calendar.
 */
public class DayModel {

  private int maxEvents;
  private int maxTasks;
  private List<IcalendarItem> items;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Day dayOfWeek;

  /**
   * Constructs a new instance of the DayModel class with the specified values.
   *
   * @param maxEvents  The maximum number of events allowed for the day.
   * @param maxTasks   The maximum number of tasks allowed for the day.
   * @param items      The list of calendar items for the day.
   * @param dayOfWeek  The day of the week.
   */
  public DayModel(int maxEvents, int maxTasks, List<IcalendarItem> items, Day dayOfWeek) {
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.items = items;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Gets the maximum number of events allowed for the day.
   *
   * @return The maximum number of events.
   */
  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * Sets the maximum number of events allowed for the day.
   *
   * @param maxEvents The maximum number of events.
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Gets the maximum number of tasks allowed for the day.
   *
   * @return The maximum number of tasks.
   */
  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * Sets the maximum number of tasks allowed for the day.
   *
   * @param maxTasks The maximum number of tasks.
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Gets the list of calendar items for the day.
   *
   * @return The list of calendar items.
   */
  public List<IcalendarItem> getItems() {
    return items;
  }

  /**
   * returns the tasks in the day
   *
   * @return a list of tasks
   */
  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    for (IcalendarItem item : items) {
      if (item instanceof Task) {
        tasks.add((Task) item);
      }
    }
    return tasks;
  }

  /**
   * Retrieves the list of events in the DayModel.
   *
   * @return The list of events in the DayModel.
   */
  public List<Event> getEvents() {
    List<Event> events = new ArrayList<>();
    for (IcalendarItem item : items) {
      if (item instanceof Event) {
        events.add((Event) item);
      }
    }
    return events;
  }

  /**
   * Sets the list of calendar items for the day.
   *
   * @param items The list of calendar items.
   */
  public void setItems(List<IcalendarItem> items) {
    this.items = items;
  }

  /**
   * Gets the day of the week.
   *
   * @return The day of the week.
   */
  public Day getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Sets the day of the week.
   *
   * @param dayOfWeek The day of the week.
   */
  public void setDayOfWeek(Day dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Calculates the percentage of completed tasks.
   *
   * @return The percentage of completed tasks.
   */
  public double calculateCompleted() {
    List<Task> tasks = new ArrayList<>();
    int count = 0;
    for (IcalendarItem item : items) {
      if (item instanceof Task) {
        tasks.add((Task) item);
        if (((Task) item).getIsComplete()) {
          count++;
        }
      }
    }
    return (double) count / tasks.size();
  }

  /**
   * Calculates the number of incomplete tasks in the DayModel.
   *
   * @return The count of incomplete tasks in the DayModel.
   */
  public int calculateLeft() {
    int count = 0;
    for (IcalendarItem item : items) {
      if (item instanceof Task) {
        if (!(((Task) item).getIsComplete())) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Returns a string representation of the DayModel object.
   *
   * @return A string representation of the DayModel object.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("DayModel{");
    sb.append("maxEvents=").append(maxEvents);
    sb.append(", maxTasks=").append(maxTasks);
    sb.append(", items=[");
    for (int i = 0; i < items.size(); i++) {
      IcalendarItem item = items.get(i);
      if (item instanceof Event) {
        sb.append(((Event) item).eventToString(item.getDescription()));
      } else if (item instanceof Task) {
        sb.append(((Task) item).taskToString(item.getDescription()));
      }
      if (i < items.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    sb.append(", dayOfWeek=").append(dayOfWeek);
    sb.append('}');
    return sb.toString();
  }

  /**
   * Returns the hash code value for the DayModel object.
   *
   * @return The hash code value for the DayModel object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(maxEvents, maxTasks, items, dayOfWeek);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj The reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    DayModel other = (DayModel) obj;
    return this.maxEvents == other.maxEvents
            && this.maxTasks == other.maxTasks
            && this.dayOfWeek == other.dayOfWeek
            && Objects.equals(this.items, other.items);
  }
}
