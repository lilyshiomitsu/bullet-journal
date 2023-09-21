package cs3500.pa05.controller;

import java.io.IOException;

/**
 * The IBulletBoardController interface represents a
 * controller for a bullet board application.
 * It provides methods for running the application,
 * saving data to a file, and loading data from a file.
 */
public interface IbulletBoardController {

  /**
   * Runs the bullet board application.
   *
   * @throws IllegalStateException If the application is in an illegal state.
   * @throws IOException            If an I/O error occurs.
   */
  void run() throws IllegalStateException, IOException;

  /**
   * Saves the data of the bullet board to a file.
   *
   * @param filePath The path of the file to save the data to.
   */
  void saveToFile(String filePath);

  /**
   * Loads data for the bullet board from a file.
   *
   * @param filePath The path of the file to load the data from.
   */
  void loadFromFile(String filePath);
}
