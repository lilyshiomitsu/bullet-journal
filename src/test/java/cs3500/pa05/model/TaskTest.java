package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TaskTest {
  private Task task;

  @BeforeEach
  void setUp() {
    task = new Task();
  }

  @Test
  void testDefaultConstructor() {
    assertFalse(task.getIsComplete());
    assertEquals("Task", task.getType());
  }

  @Test
  void testParameterizedConstructor() {
    String name = "Task 1";
    String description = "Task description";
    Day dayOfWeek = Day.MONDAY;
    String category = "Work";
    String type = "Task";
    boolean isComplete = true;

    task = new Task(name, description, dayOfWeek, category, type, isComplete);

    assertEquals(name, task.getName());
    assertEquals(description, task.getDescription());
    assertEquals(dayOfWeek, task.getDayOfWeek());
    assertEquals(category, task.getCategory());
    assertEquals(type, task.getType());
    assertEquals(isComplete, task.getIsComplete());
  }

  @Test
  void testSetIsComplete() {
    assertFalse(task.getIsComplete());

    task.setIsComplete(true);
    assertTrue(task.getIsComplete());

    task.setIsComplete(false);
    assertFalse(task.getIsComplete());
  }

  @Test
  void testTaskToString() {
    String name = "Task 1";
    String description = "Task description";
    Day dayOfWeek = Day.MONDAY;
    String category = "Work";

    task = new Task(name, description, dayOfWeek, category);

    String expected = "Name: " + name + "\n"
            + "Description: " + "blah" + "\n"
            + "Category: " + category;

    assertEquals(expected, task.taskToString("blah"));
  }

  @Test
  void testGetType() {
    assertEquals("Task", task.getType());
  }

}