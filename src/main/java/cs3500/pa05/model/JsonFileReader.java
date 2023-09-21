package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for reading a JSON file and deserializing it into a WeekModel object.
 */
public class JsonFileReader {

  /**
   * Load a WeekModel from the specified JSON file.
   *
   * @param filePath The path to the JSON file.
   * @return The loaded WeekModel.
   */
  public WeekModel loadFromFile(String filePath) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new CustomDeserializerModule());

      // Read the JSON from the file
      WeekModel weekModel = mapper.readValue(new File(filePath), WeekModel.class);


      return weekModel;
    } catch (IOException e) {
      e.printStackTrace();
      // Handle the exception appropriately
      return null;
    }
  }



  /**
   * Custom deserializer module to register the deserializers.
   */
  static class CustomDeserializerModule extends SimpleModule {
    public CustomDeserializerModule() {
      addDeserializer(WeekModel.class, new WeekModelDeserializer());
      addDeserializer(DayModel.class, new DayModelDeserializer());
      // Add more deserializers for other classes if needed
    }
  }

  /**
   * Deserializer for WeekModel objects.
   */
  public static class WeekModelDeserializer extends JsonDeserializer<WeekModel> {
    @Override
    public WeekModel deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {
      ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
      JsonNode node = mapper.readTree(jsonParser);

      int maxTasks = 0;
      int maxEvents = 0;


      List<DayModel> days = new ArrayList<>();
      JsonNode daysNode = node.get("days");
      if (daysNode != null && daysNode.isArray()) {
        for (JsonNode dayNode : daysNode) {
          JsonNode itemsNode = dayNode.get("items");
          if (itemsNode != null && itemsNode.isArray()) {
            List<IcalendarItem> items = new ArrayList<>();
            for (JsonNode itemNode : itemsNode) {
              JsonNode typeNode = itemNode.get("type");
              if (typeNode != null && typeNode.isTextual()) {
                String itemType = typeNode.asText();
                IcalendarItem item;
                switch (itemType) {
                  case "Event":
                    item = mapper.treeToValue(itemNode, Event.class);
                    break;
                  case "Task":
                    item = mapper.treeToValue(itemNode, Task.class);
                    break;
                  default:
                    throw new IllegalArgumentException("Unknown item type: " + itemType);
                }
                items.add(item);
              } else {
                throw new IllegalArgumentException("'type' field is missing or not a string");
              }
            }
            maxEvents = dayNode.get("maxEvents").asInt();
            maxTasks = dayNode.get("maxTasks").asInt();
            Day dayOfWeek = Day.valueOf(dayNode.get("dayOfWeek").asText());
            DayModel day = new DayModel(maxEvents, maxTasks, items, dayOfWeek);
            days.add(day);
          } else {
            throw new IllegalArgumentException("'items' field is missing, not an array, or empty");
          }
        }
      } else {
        throw new IllegalArgumentException("'days' field is missing or not an array");
      }
      String text = node.get("text").asText();
      String password = node.get("password").asText();
      WeekModel weekModel = new WeekModel();
      weekModel.setDays(days);
      weekModel.setMaxTasks(maxTasks);
      weekModel.setMaxEvents(maxEvents);
      weekModel.setText(text); // Set the "text" field value in the WeekModel
      weekModel.setPassword(password);
      return weekModel;
    }
  }

  /**
   * * Deserializer for DayModel objects.
   */
  static class DayModelDeserializer extends StdDeserializer<DayModel> {
    public DayModelDeserializer() {
      super(DayModel.class);
    }


    /**
     * deserializes the JSON data into a DayModel object.
     * reads the 'type' field from the JSON node to determine the appropriate class for
     * deserialization.
     * if the 'type' is "dayModel", converts the JSON node into a DayModel object .
     * otherwise, throws an illegalargumentexception with an appropriate error message.
     *
     * @param jsonParser             Parsed used for reading JSON content
     * @param deserializationContext Context that can be used to access information about
     *                               this deserialization activity
     * @return the deserialized DayModel object
     * @throws IOException              if an I/O error occurs
     * @throws JsonProcessingException  if a JSON processing error occurs
     * @throws IllegalArgumentException if the 'type' field is missing or an unknown type
     */
    @Override
    public DayModel deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext) throws IOException,
        JsonProcessingException {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode node = jsonParser.getCodec().readTree(jsonParser);

      // Read the 'type' field to determine the appropriate class for deserialization
      String type = node.get("type").asText();
      if ("DayModel".equals(type)) {
        return objectMapper.treeToValue(node, DayModel.class);
      }

      throw new IllegalArgumentException("Unknown type: " + type);
    }

  }
}
