package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TimeTest {
  private Time time;

  @BeforeEach
  void setUp() {
    time = new Time();
  }

  @Test
  void testDefaultConstructor() {
    assertEquals(0, time.getHour());
    assertEquals(0, time.getMinute());
  }

  @Test
  void testParameterizedConstructor() {
    int hour = 10;
    int minute = 30;
    time = new Time(hour, minute);

    assertEquals(hour, time.getHour());
    assertEquals(minute, time.getMinute());
  }

  @Test
  void testSetHour() {
    int hour = 5;
    time.setHour(hour);
    assertEquals(hour, time.getHour());
  }

  @Test
  void testSetHourOutOfRange() {
    assertThrows(IllegalArgumentException.class, () -> new Time(-1, 59));
    assertThrows(IllegalArgumentException.class, () -> new Time(24, 1));
  }

  @Test
  void testSetMinute() {
    int minute = 45;
    time.setMinute(minute);
    assertEquals(minute, time.getMinute());
  }

  @Test
  void testSetMinuteOutOfRange() {
    assertThrows(IllegalArgumentException.class, () -> new Time(1, 60));
    assertThrows(IllegalArgumentException.class, () -> new Time(1, -1));
  }

  @Test
  void testTimeToString() {
    time = new Time(8, 15);
    assertEquals("8:15", time.timeToString());
  }
}
