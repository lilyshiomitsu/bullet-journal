package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents an event in the bullet board
 */
public class Event extends AcalendarItem {

  private Time startTime;
  private Time duration;

  /**
   * constructor for event object
   */
  public Event() {
    super();
    startTime = new Time();
    duration = new Time();
  }

  /**
   * Constructs an Event object with the specified properties.
   *
   * @param name        The name of the event.
   * @param description The description of the event.
   * @param dayOfWeek   The day of the week when the event takes place.
   * @param startTime   The start time of the event.
   * @param duration    The duration of the event.
   * @param category    The category of the event.
   * @param json        Flag indicating if the event is created from JSON.
   */
  public Event(String name, String description, Day dayOfWeek, Time startTime, Time duration,
               String category, boolean json) {
    super(name, description, dayOfWeek, category);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * json creator for event
   *
   * @param name name of event
   * @param description description of event
   * @param dayOfWeek day of event
   * @param startTime start time of event
   * @param duration duration of event
   * @param type of event
   * @param category of event
   */
  @JsonCreator
  public Event(@JsonProperty("name") String name,
               @JsonProperty("description") String description,
               @JsonProperty("dayOfWeek") Day dayOfWeek,
               @JsonProperty("startTime") Time startTime,
               @JsonProperty("duration") Time duration,
               @JsonProperty("type") String type,
               @JsonProperty("category") String category) {
    super(name, description, dayOfWeek, category);
    this.startTime = startTime;
    this.duration = duration;
  }


  /**
   * Get the start time of the event.
   *
   * @return The start time of the event.
   */
  public Time getStartTime() {
    return this.startTime;
  }

  /**
   * Set the start time of the event.
   *
   * @param startTime The start time of the event.
   */
  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  /**
   * Get the duration of the event.
   *
   * @return The duration of the event.
   */
  public Time getDuration() {
    return this.duration;
  }

  /**
   * Set the duration of the event.
   *
   * @param duration The duration of the event.
   */
  public void setDuration(Time duration) {
    this.duration = duration;
  }

  /**
   * Returns a string representation of the event.
   *
   * @param description The description of the event.
   * @return A string representation of the event.
   */
  public String eventToString(String description) {
    return "Name: " + this.getName() + "\n"
        + "Description: " + description + "\n"
        + "Start time: " + this.getStartTime().timeToString() + "\n"
        + "Duration: " + this.getDuration().timeToString() + "\n"
        + "Category: " + this.getCategory();
  }

  /**
   * Return the type field of the Event
   *
   * @return the type of this event
   */
  public String getType() {
    return "Event";
  }

}


