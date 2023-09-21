package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing Class for the Writer Class
 */
public class JsonFileWriterTest {
  JsonFileWriter writer;

  @BeforeEach
  void setup() {
    writer = new JsonFileWriter();
  }


  @Test
  void writingNewFile() throws IOException {
    writer.writeJsonToFile("newFile.bujo", new WeekModel());
    assertTrue(Files.exists(Path.of("newFile.bujo")));
    Files.delete(Path.of("newFile.bujo"));
  }

  @Test
  void writingExistingFileWithBujo() throws IOException {
    writer.writeJsonToFile("testBujo/goodbye.bujo", new WeekModel());
    File existingTestFile = Path.of("testBujo/goodbye.bujo").toFile();
    assertEquals(713, existingTestFile.length());
    Files.write(existingTestFile.toPath(), "".getBytes());
  }

  @Test
  void writingFileWithoutBujo() throws IOException {
    new File("file");
    writer.writeJsonToFile("file", new WeekModel());
    File newName = Path.of("file.bujo").toFile();
    assertEquals(713, newName.length());
    newName.delete();
  }

}
