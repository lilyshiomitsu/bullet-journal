package cs3500.pa05.view;

import cs3500.pa05.controller.IbulletBoardController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * represents a week view class
 */

public class WeekViewImpl implements WeekView {
  private FXMLLoader loader;

  /**
   * Constructs a WeekViewImpl object with the specified controller.
   *
   * @param controller The controller for the week view.
   */
  public WeekViewImpl(IbulletBoardController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("week.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads the week view layout and returns the corresponding scene.
   *
   * @return The scene representing the loaded week view layout.
   * @throws IllegalStateException if unable to load the layout.
   */
  @Override
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}