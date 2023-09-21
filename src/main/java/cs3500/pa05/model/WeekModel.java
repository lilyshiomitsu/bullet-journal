package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * represents the model for the week
 */
public class WeekModel {
  private String name;
  private int maxEvents;
  private int maxTasks;
  private List<DayModel> days;
  private List<String> categories;
  private String text;
  private String password;

  /**
   * Constructs a new WeekModel object with the specified parameters.
   *
   * @param name      The name of the week.
   * @param maxEvents The maximum number of events allowed for the week.
   * @param maxTasks  The maximum number of tasks allowed for the week.
   * @param days      The list of DayModel objects representing the days of the week.
   */
  @JsonCreator
  public WeekModel(
          String name, int maxEvents, int maxTasks, List<DayModel> days, List<String> categories,
          String text, String password) {
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.days = days;
    this.categories = new ArrayList<>();
    this.text = text;
    this.password = password;
  }

  /**
   * default constructor for week model
   */
  public WeekModel() {
    this.name = "untitled";
    this.maxEvents = 10;
    this.maxTasks = 10;
    this.categories = new ArrayList<>();
    categories.add("none");
    List<DayModel> week = new ArrayList<>();
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.SUNDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.MONDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.TUESDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.WEDNESDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.THURSDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.FRIDAY));
    week.add(new DayModel(10, 10, new ArrayList<>(), Day.SATURDAY));
    this.days = week;
    this.text = "";
    this.password = "";
  }

  /**
   * Returns the text field
   *
   * @return the string in the text field
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text field
   *
   * @param text the text that we want to replace the current text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Get the name of the week.
   *
   * @return The name of the week.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the week.
   *
   * @param name The name of the week.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the maximum number of events allowed for the week.
   *
   * @return The maximum number of events allowed.
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Set the maximum number of events allowed for the week.
   *
   * @param maxEvents The maximum number of events allowed.
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
    for (DayModel d : days) {
      d.setMaxEvents(maxEvents);
    }
  }

  /**
   * Get the maximum number of tasks allowed for the week.
   *
   * @return The maximum number of tasks allowed.
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Set the maximum number of tasks allowed for the week.
   *
   * @param maxTasks The maximum number of tasks allowed.
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
    for (DayModel d : days) {
      d.setMaxTasks(maxTasks);
    }
  }

  /**
   * Get the list of DayModel objects representing the days of the week.
   *
   * @return The list of DayModel objects.
   */
  public List<DayModel> getDays() {
    return this.days;
  }

  /**
   * Set the list of DayModel objects representing the days of the week.
   *
   * @param days The list of DayModel objects.
   */
  public void setDays(List<DayModel> days) {
    this.days = days;
  }

  /**
   * Returns the list of categories in the WeekModel.
   *
   * @return The list of categories.
   */
  public List<String> getCategories() {
    return categories;
  }

  /**
   * Returns a string representation of the WeekModel, including the string representations
   * of each DayModel in the model.
   *
   * @return The string representation of the WeekModel.
   */
  public String toString() {
    String str = "THIS IS THE TEXT AREA: " + text + "\n";
    for (DayModel item : days) {
      str += item.toString() + "\n";
    }
    str += "Password: " + password;
    return str;
  }

  /**
   * Calculates the completion progress for each day in the WeekModel and returns a list of
   * the completion percentages for each day.
   *
   * @return The list of completion percentages for each day.
   */
  public List<Double> weekCompleted() {
    List<Double> progressBarNumbers = new ArrayList<>();
    for (DayModel day : days) {
      progressBarNumbers.add(day.calculateCompleted());
    }
    return progressBarNumbers;
  }

  /**
   * Calculates the number of incomplete tasks for each day in the week.
   *
   * @return A list of integers representing the number of incomplete tasks for each day.
   */
  public List<Integer> weekLeft() {
    List<Integer> left = new ArrayList<>();
    for (DayModel day : days) {
      left.add(day.calculateLeft());
    }
    return left;
  }


  /**
   * Sets the password for the week model.
   *
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the password for the week model.
   *
   * @return The password for the week model.
   */
  public String getPassword() {
    return this.password;
  }


}