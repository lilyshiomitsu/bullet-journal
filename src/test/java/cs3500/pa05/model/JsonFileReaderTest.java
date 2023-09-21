package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JsonFileReaderTest {
  private JsonFileReader reader;

  @BeforeEach
  void setUp() throws IOException {
    reader = new JsonFileReader();
  }

  @Test
  void loadValidFile() {
    String filePath = "testBujo/valid.bujo";
    WeekModel weekModel = reader.loadFromFile(filePath);
    assertNotNull(weekModel);
  }

  @Test
  void loadNullItemFile() {
    String filePath = "testBujo/nullItem.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

  @Test
  void loadInvalidTypeFile() {
    String filePath = "testBujo/nullType.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

  @Test
  void loadNullItemListFile() {
    String filePath = "testBujo/nullItemList.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

  @Test
  void loadNullDayFile() {
    String filePath = "testBujo/nullDay.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }


  @Test
  void loadFromFileInvalidFile() {
    String filePath = "testBujo/invalid.bujo";
    assertNull(reader.loadFromFile(filePath));
  }

  @Test
  void loadNonArrayItemListFile() {
    String filePath = "testBujo/nonArrayItems.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

  @Test
  void loadNonArrayDayFile() {
    String filePath = "testBujo/nonArrayDay.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

  @Test
  void loadNonTextualItemFile() {
    String filePath = "testBujo/nonTextualItem.bujo";
    assertThrows(IllegalArgumentException.class, () -> reader.loadFromFile(filePath));
  }

}
