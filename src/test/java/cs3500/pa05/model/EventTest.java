package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the event class
 */
public class EventTest {
  Event event;

  @BeforeEach
  void setup() {
    event = new Event();
  }

  @Test
  public void testEventToString() {
    Event detailedEvent = new Event("Meeting", "Team meeting", Day.MONDAY,
                new Time(10, 0), new Time(1, 30), "Work", false);

    String expectedString = "Name: Meeting\n"
                + "Description: Team meeting\n"
                + "Start time: 10:0\n"
                + "Duration: 1:30\n"
                + "Category: Work";

    assertEquals(expectedString, detailedEvent.eventToString("Team meeting"));
  }

  @Test
  public void testGetType() {
    assertEquals("Event", event.getType());
  }

  @Test
  public void testGetStartTime() {
    Time startTime = new Time(10, 0);
    event.setStartTime(startTime);

    assertEquals(startTime, event.getStartTime());
  }

  @Test
  public void testSetStartTime() {
    Time startTime = new Time(10, 0);
    event.setStartTime(startTime);

    assertEquals(startTime, event.getStartTime());
  }

  @Test
  public void testGetDuration() {
    Time duration = new Time(1, 30);
    event.setDuration(duration);
    assertEquals(duration, event.getDuration());
  }

  @Test
  public void testSetDuration() {

    Time duration = new Time(1, 30);
    event.setDuration(duration);

    assertEquals(duration, event.getDuration());
  }

  @Test
  public void testJsonCreatorConstructor() {
    String name = "Meeting";
    String description = "Team meeting";
    Day dayOfWeek = Day.MONDAY;
    Time startTime = new Time(10, 0);
    Time duration = new Time(1, 30);
    String type = "Event";
    String category = "Work";

    Event event = new Event(name, description, dayOfWeek, startTime, duration, type, category);

    assertEquals(name, event.getName());
    assertEquals(description, event.getDescription());
    assertEquals(dayOfWeek, event.getDayOfWeek());
    assertEquals(startTime, event.getStartTime());
    assertEquals(duration, event.getDuration());
    assertEquals(type, event.getType());
    assertEquals(category, event.getCategory());
  }
}

