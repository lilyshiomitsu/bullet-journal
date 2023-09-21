package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for writing JSON data to a file.
 */
public class JsonFileWriter {

  /**
   * Writes the JSON data to a file.
   *
   * @param filePath The path to the file to write.
   * @param json The JSON object to write to the file.
   * @throws IOException If an I/O error occurs during file writing.
   */
  public static void writeJsonToFile(String filePath, WeekModel json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(filePath);
    String fileName = file.getName();

    // Check if the file already exists with the .bujo extension
    if (!fileName.endsWith(".bujo")) {
      file = new File(fileName.substring(0) + ".bujo");
    }

    if (!file.exists()) {
      file.createNewFile();
    }

    try (FileWriter fileWriter = new FileWriter(file)) {
      mapper.writeValue(fileWriter, json);
    }
  }
}
