package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the daymodel class
 */
class DayModelTest {

  DayModel testDayModel;


  @BeforeEach
  void setup() {
    testDayModel = new DayModel(10, 10, new ArrayList<IcalendarItem>(),  Day.MONDAY);
  }

  @Test
  void getMaxEvents() {
    assertEquals(10, testDayModel.getMaxEvents());
  }

  @Test
  void setMaxEvents() {
    testDayModel.setMaxEvents(1);
    assertEquals(1, testDayModel.getMaxEvents());
  }

  @Test
  void getMaxTasks() {
    assertEquals(10, testDayModel.getMaxTasks());
  }

  @Test
  void setMaxTasks() {
    testDayModel.setMaxTasks(1);
    assertEquals(1, testDayModel.getMaxTasks());
  }

  @Test
  void getItems() {
    assertEquals(new ArrayList<>(), testDayModel.getItems());
  }

  //  @Test
  //  void setItems() {
  //    List<IcalendarItem> items = new ArrayList<>();
  //    items.add(new AcalendarItem());
  //    testDayModel.setItems(items);
  //    assertEquals(items, testDayModel.getItems());
  //  }

  @Test
  void getEvents() {
    Event testEvent = new Event();
    List<IcalendarItem> items = new ArrayList<>();
    items.add(new Task("code", "", Day.SATURDAY, "", "", true));
    items.add(testEvent);
    //items.add(new AcalendarItem());
    testDayModel.setItems(items);

    List<Event> expectedEvents = new ArrayList<>();
    expectedEvents.add(testEvent);

    assertEquals(expectedEvents, testDayModel.getEvents());
  }

  @Test
  void getTasks() {
    Task testTask = new Task("code", "", Day.SATURDAY, "", "", true);
    List<IcalendarItem> items = new ArrayList<>();
    items.add(testTask);
    items.add(new Event());
    //items.add(new AcalendarItem());
    testDayModel.setItems(items);

    List<Task> expectedEvents = new ArrayList<>();
    expectedEvents.add(testTask);

    assertEquals(expectedEvents, testDayModel.getTasks());
  }

  @Test
  void testCalculateLeft() {
    List<IcalendarItem> items = new ArrayList<>();
    Task testTask = new Task("code", "", Day.SATURDAY, "", "", false);
    Task testTaskComplete = new Task("code", "", Day.SATURDAY, "", "", true);
    Event detailedEvent = new Event("Meeting", "Team meeting", Day.MONDAY,
            new Time(10, 0), new Time(1, 30), "Work", false);
    items.add(testTask);
    items.add(detailedEvent);
    items.add(testTaskComplete);
    testDayModel.setItems(items);

    assertEquals(testDayModel.calculateLeft(), 1);
  }

  @Test
  void getDayOfWeek() {
    assertEquals(Day.MONDAY, testDayModel.getDayOfWeek());
  }

  @Test
  void setDayOfWeek() {
    testDayModel.setDayOfWeek(Day.FRIDAY);
    assertEquals(Day.FRIDAY, testDayModel.getDayOfWeek());
  }

  @Test
  void calculateCompleted() {
    List<IcalendarItem> tasks = new ArrayList<>();
    tasks.add(new Task("code", "", Day.SATURDAY, "", "", true));
    tasks.add(new Event());
    tasks.add(new Task("code", "", Day.SATURDAY, "", "", false));
    testDayModel.setItems(tasks);
    assertEquals(0.5, testDayModel.calculateCompleted());
  }

  @Test
  void testToString() {
    List<IcalendarItem> items = new ArrayList<>();
    items.add(new Task("Code", "Code PA", Day.SATURDAY, "Code", "", true));
    items.add(new Event("Meeting", "Team meeting", Day.MONDAY, new Time(10, 0),
          new Time(1, 30), "Work", false));
    //items.add(new AcalendarItem());
    testDayModel.setItems(items);

    String expectedOutput = "DayModel{maxEvents=10, "
            + "maxTasks=10, "
            + "items=[Name: Code\n"
            + "Description: Code PA\n"
            + "Category: Code, "
            + "Name: Meeting\n"
            + "Description: Team meeting\n"
            + "Start time: 10:0\n"
            + "Duration: 1:30\n"
            + "Category: Work], "
            + "dayOfWeek=MONDAY}";
    assertEquals(expectedOutput, testDayModel.toString());
  }

  @Test
  public void sameObject() {
    assertTrue(testDayModel.equals(testDayModel));
  }

  @Test
  public void nullObject() {
    assertFalse(testDayModel.equals(null));
  }

  @Test
  public void differentClass() {
    assertFalse(testDayModel.equals("hello"));
  }

  @Test
  public void equalProperties() {
    DayModel dayModel1 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel2 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    assertTrue(dayModel1.equals(dayModel2));
  }

  @Test
  public void differentYear() {
    DayModel dayModel1 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel3 = new DayModel(9, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);

    assertFalse(dayModel1.equals(dayModel3));
  }

  @Test
  public void differentItems() {
    List<IcalendarItem> items = new ArrayList<>();
    items.add(new Task());
    DayModel dayModel1 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel4 = new DayModel(10, 10, items, Day.MONDAY);

    assertFalse(dayModel1.equals(dayModel4));
  }

  @Test
  public void differentMonth() {
    DayModel dayModel1 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel5 = new DayModel(10, 9, new ArrayList<IcalendarItem>(), Day.MONDAY);
    assertFalse(dayModel1.equals(dayModel5));
  }

  @Test
  public void differentDayOfWeek() {
    DayModel dayModel1 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel6 = new DayModel(10, 10, new ArrayList<IcalendarItem>(), Day.FRIDAY);

    assertFalse(dayModel1.equals(dayModel6));
  }

  @Test
  public void testHashCode() {
    DayModel dayModel1 = new DayModel(10, 5, new ArrayList<IcalendarItem>(), Day.MONDAY);
    DayModel dayModel2 = new DayModel(10, 5, new ArrayList<IcalendarItem>(), Day.MONDAY);

    assertTrue(dayModel1.equals(dayModel2) && dayModel2.equals(dayModel1));
    assertEquals(dayModel1.hashCode(), dayModel2.hashCode());
  }

}