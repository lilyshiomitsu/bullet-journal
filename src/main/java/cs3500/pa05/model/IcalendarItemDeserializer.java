package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/**
 * represents the icalendar item deserializer for jsons
 */
public class IcalendarItemDeserializer extends JsonDeserializer<IcalendarItem> {

  @Override
  public IcalendarItem deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectMapper mapper = (ObjectMapper) parser.getCodec();
    ObjectNode root = mapper.readTree(parser);

    // Check if the "type" field is present in the JSON
    JsonNode typeNode = root.get("type");
    if (typeNode == null) {
      throw new IllegalArgumentException("'type' field is missing in the JSON");
    }

    // Determine the type of the concrete class based on the JSON structure
    String itemType = typeNode.asText();

    // Use a switch or if-else statements to map the JSON to the appropriate concrete class
    if (itemType.equals("Event")) {
      // Deserialize as Event
      return mapper.treeToValue(root, Event.class);
    } else if (itemType.equals("Task")) {
      // Deserialize as Task
      return mapper.treeToValue(root, Task.class);
    } else {
      // Handle unknown item types or throw an exception
      throw new UnsupportedOperationException("Unknown item type: " + itemType);
    }
  }
}
