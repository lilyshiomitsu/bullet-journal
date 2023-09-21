package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tester class for the Day class
 */
public class DayTest {
  @Test
  void getDay() {
    Day testDay = Day.FRIDAY;
    assertEquals(5, testDay.getDayNumber());
  }

}
