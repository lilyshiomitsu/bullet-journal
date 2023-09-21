package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;





/**
 * tests the weekmodel class
 */
public class WeekModelTest {
  private WeekModel weekModel;

  @BeforeEach
  public void setUp() {
    weekModel = new WeekModel();
  }

  private List<DayModel> createDefaultDays() {
    List<DayModel> days = new ArrayList<>();
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.SUNDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.MONDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.TUESDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.WEDNESDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.THURSDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.FRIDAY));
    days.add(new DayModel(10, 10, new ArrayList<>(), Day.SATURDAY));
    return days;
  }

  private WeekModel createDetailedWeekModel() {
    Task task = new Task("Task 1", "", Day.SATURDAY, "", "", true);

    List<IcalendarItem> tasks = new ArrayList<>();
    tasks.add(task);

    List<DayModel> day = new ArrayList<>();
    day.add(new DayModel(1, 1, tasks, Day.SATURDAY));

    WeekModel week = new WeekModel("empty", 1, 1, day,
            new ArrayList<String>(), "", "");
    return week;
  }

  @Test
  public void getName() {
    String expectedName = "untitled";
    assertEquals(expectedName, weekModel.getName());
  }

  @Test
  public void testWeekLeft() {
    List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    assertEquals(expected, weekModel.weekLeft());
  }


  @Test
  public void getDays() {
    List<DayModel> expectedDays = createDefaultDays();
    assertArrayEquals(expectedDays.toArray(), weekModel.getDays().toArray());
  }

  @Test
  public void getMaxEvents() {
    int expectedMaxEvents = 10;
    assertEquals(expectedMaxEvents, weekModel.getMaxEvents());
  }

  @Test
  public void setMaxEvents() {
    int newMaxEvents = 5;
    weekModel.setMaxEvents(newMaxEvents);
    assertEquals(newMaxEvents, weekModel.getMaxEvents());

    for (DayModel day : weekModel.getDays()) {
      assertEquals(newMaxEvents, day.getMaxEvents());
    }
  }

  @Test
  public void getMaxTasks() {
    int expectedMaxTasks = 10;
    assertEquals(expectedMaxTasks, weekModel.getMaxTasks());
  }

  @Test
  public void setMaxTasks() {
    int newMaxTasks = 5;
    weekModel.setMaxTasks(newMaxTasks);
    assertEquals(newMaxTasks, weekModel.getMaxTasks());

    for (DayModel day : weekModel.getDays()) {
      assertEquals(newMaxTasks, day.getMaxTasks());
    }
  }


  @Test
  public void setDays() {
    List<DayModel> newDays = new ArrayList<>();
    newDays.add(new DayModel(5, 5, new ArrayList<>(), Day.SUNDAY));
    newDays.add(new DayModel(5, 5, new ArrayList<>(), Day.MONDAY));
    weekModel.setDays(newDays);
    assertEquals(newDays, weekModel.getDays());
  }

  @Test
  public void getCategories() {
    List<String> expectedCategories = new ArrayList<>();
    expectedCategories.add("none");
    assertEquals(expectedCategories, weekModel.getCategories());
  }

  @Test
  public void getText() {
    String expectedText = "";
    assertEquals(expectedText, weekModel.getText());
  }

  @Test
  public void setName() {
    weekModel.setName("test");
    assertEquals("test", weekModel.getName());
  }

  @Test
  public void setText() {
    weekModel.setText("test");
    assertEquals("test", weekModel.getText());
  }

  @Test
  public void testToString() {

    List<DayModel> days = createDefaultDays();
    String text = "This is a test";

    StringBuilder expectedString = new StringBuilder("THIS IS THE TEXT AREA: \n");
    for (DayModel item : days) {
      expectedString.append(item.toString()).append("\n");
    }
    expectedString.append("Password: ");

    assertEquals(expectedString.toString(), weekModel.toString());
  }

  @Test
  public void weekCompleted() {
    List<Double> list = new ArrayList<>();
    list.add(1.0);
    assertEquals(list, createDetailedWeekModel().weekCompleted());
  }

  @Test
  public void detailedConstructor() {

    Task task = new Task("Task 1", "", Day.SATURDAY, "", "", true);

    List<IcalendarItem> tasks = new ArrayList<>();
    tasks.add(task);

    List<DayModel> day = new ArrayList<>();
    day.add(new DayModel(1, 1, tasks, Day.SATURDAY));

    WeekModel testWeek = createDetailedWeekModel();
    testWeek.setDays(day);
    assertEquals("empty", testWeek.getName());
    assertEquals(1, testWeek.getMaxEvents());
    assertEquals(1, testWeek.getMaxTasks());
    assertEquals(day, testWeek.getDays());
    assertEquals(new ArrayList<String>(), testWeek.getCategories());
    assertEquals("", testWeek.getText());
  }
}
