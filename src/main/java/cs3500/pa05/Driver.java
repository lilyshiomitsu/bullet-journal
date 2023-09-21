package cs3500.pa05;

import cs3500.pa05.controller.BulletBoardController;
import cs3500.pa05.controller.IbulletBoardController;
import cs3500.pa05.model.WeekModel;
import cs3500.pa05.view.WeekView;
import cs3500.pa05.view.WeekViewImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry Point
 */
public class Driver extends Application {
  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param stage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage stage) throws IllegalStateException, IOException {

    WeekModel model = new WeekModel();
    IbulletBoardController controller = new BulletBoardController(model, stage, getHostServices());

    WeekView view = new WeekViewImpl(controller);

    try {
      // load and place the view's scene onto the stage
      stage.setScene(view.load());

      // run the controller
      controller.run();

      // render the stage
      stage.show();

    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * The main entry point for the application.
   *
   * @param args The command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    launch();
  }
}
