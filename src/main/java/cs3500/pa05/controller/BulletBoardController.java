package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayModel;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.IcalendarItem;
import cs3500.pa05.model.JsonFileReader;
import cs3500.pa05.model.JsonFileWriter;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.WeekModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




/**
 * The BulletBoardController class is responsible for controlling the interactions between the
 * user interface and the WeekModel in the Bullet Board application.
 */
public class  BulletBoardController implements IbulletBoardController {

  private WeekModel model;
  private HostServices services;
  @FXML
  private AnchorPane root;
  @FXML
  private Button create;
  @FXML
  private GridPane gridPane;
  @FXML
  private Button save;
  @FXML
  private Button load;
  @FXML
  private Button edit;
  @FXML
  private Button editEntry;
  @FXML
  private Button setPassword;
  @FXML
  private Label maxEvents;
  @FXML
  private Label thisWeeksTasks;
  @FXML
  private Label sunday;
  @FXML
  private Label monday;
  @FXML
  private Label tuesday;
  @FXML
  private Label wednesday;
  @FXML
  private Label thursday;
  @FXML
  private Label friday;
  @FXML
  private Label saturday;
  @FXML
  private Button deleteEntry;
  @FXML
  private Label maxTasks;
  @FXML
  private Label quotes;
  @FXML
  private Label title;
  private Stage stage;
  private List<Task> incompleteTasks;
  @FXML
  private Button categoriesButton;
  @FXML
  private ProgressBar sundayProgress;
  @FXML
  private ProgressBar mondayProgress;
  @FXML
  private ProgressBar tuesdayProgress;
  @FXML
  private ProgressBar wednesdayProgress;
  @FXML
  private TextArea textField;
  @FXML
  private ProgressBar thursdayProgress;
  @FXML
  private ProgressBar fridayProgress;
  @FXML
  private ProgressBar saturdayProgress;
  private List<ProgressBar> progressBarList;

  /**
   * Constructs a BulletBoardController with the specified WeekModel and Stage.
   *
   * @param model The WeekModel representing the data of the Bullet Board.
   * @param stage The Stage for the user interface.
   */
  public BulletBoardController(WeekModel model, Stage stage, HostServices services) {
    this.model = Objects.requireNonNull(model);
    this.stage = stage;
    this.services = services;
  }

  /**
   * Sets the style for the FXML components
   */
  public void setStyle() {
    save.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    load.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    create.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    edit.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    categoriesButton.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    deleteEntry.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    setPassword.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    editEntry.setStyle("-fx-background-color: #ef8c8c; -fx-text-fill: #fffbfb;");
    stage.getScene().getRoot().setStyle("-fx-background-color: #c2ffe8; -fx-text-fill: #fffbfb;");
    create.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    load.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    save.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    edit.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    categoriesButton.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    deleteEntry.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    editEntry.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    maxTasks.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    setPassword.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    maxEvents.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    thisWeeksTasks.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    sunday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    monday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    tuesday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    wednesday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    thursday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    friday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    saturday.setFont(Font.font("American Typewriter", FontWeight.BOLD, 12));
    quotes.setFont(Font.font("American Typewriter", FontWeight.BOLD, 18));
    title.setFont(Font.font("American Typewriter", FontWeight.BOLD, 48));
    gridPane.setStyle("-fx-background-color: #c0e1d0; -fx-text-fill: #fffbfb;");
  }

  /**
   * Shows the startup dialog when the application starts.
   */
  private void showStartupDialog() {
    Dialog<String> startupDialog = new ChoiceDialog<>("New", "New", "Load",
        "Template");
    startupDialog.setTitle("Bullet Board");
    startupDialog.setHeaderText("Welcome to Bullet Board");
    startupDialog.setContentText("Choose an option:");
    Optional<String> startupResult = startupDialog.showAndWait();
    startupResult.ifPresent(option -> {
      if (option.equals("Load")) {
        handleLoadButtonAction();
      } else if (option.equals("Template")) {
        handleTemplate();
      }
    });
  }

  /**
   * handles the actions when the template option is selected
   */
  private void handleTemplate() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load Bullet Board Template");
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
      JsonFileReader reader = new JsonFileReader();
      model = reader.loadFromFile(selectedFile.getPath());
      model.setPassword("");
      int tasks = model.getMaxTasks();
      int events = model.getMaxEvents();
      List<DayModel> week = new ArrayList<>();
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.SUNDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.MONDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.TUESDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.WEDNESDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.THURSDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.FRIDAY));
      week.add(new DayModel(events, tasks, new ArrayList<>(), Day.SATURDAY));
      model.setDays(week);
      initMaxLabels();
    }
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Set Title");
    dialog.setContentText("Enter the title of your bullet journal: ");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(entryType ->
        title.setText(entryType));
    updateScene();
  }

  /**
   * Configures the text area to wrap text and adds a listener to track
   * changes in the text.
   * When the text is changed, the updated text is stored in the model.
   */
  public void handleTextArea() {
    textField.setWrapText(true);
    textField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
                          String newValue) {
        // Store the updated text to a file or perform any other actions
        model.setText(newValue);
      }
    });
  }

  /**
   * Handles the action when the load button is clicked. Shows a file
   * chooser dialog for selecting
   * the file to load.
   */
  private void handleLoadButtonAction() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load Bullet Board");
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
      JsonFileReader reader = new JsonFileReader();
      WeekModel tempModel = reader.loadFromFile(selectedFile.getPath());
      boolean loadable = false;
      if (tempModel.getPassword() != null && !tempModel.getPassword().equals("")) {
        TextInputDialog passwordInput = new TextInputDialog();
        passwordInput.setTitle("Password Protected");
        passwordInput.setContentText("Enter your password: ");
        Optional<String> pass = passwordInput.showAndWait();
        if (pass.isPresent()) {
          while (!pass.get().equals(tempModel.getPassword())) {
            passwordInput.setHeaderText("Invalid password. Please try again.");
            pass = passwordInput.showAndWait();
            if (!pass.isPresent()) {
              // User canceled password input, exit the loop and don't load the file
              return;
            }
          }
        } else {
          // User canceled password input, don't load the file
          return;
        }
      }
      loadable = true;
      if (loadable) {
        this.model = tempModel;
        initMaxLabels();
        textField.setText(model.getText()); // Set the loaded text back to the textField
      }
    }
    updateScene();
  }


  /**
   * Handles the action when the categories button is clicked. Shows a dialog for creating a new
   * category.
   */
  private void handleCategoriesButtonAction() {
    TextInputDialog category = new TextInputDialog();
    category.setTitle("Create Category");
    category.setHeaderText(null);
    category.setContentText("Create a new category type: ");
    Optional<String> categoryResult = category.showAndWait();
    // Handle entry type selection
    categoryResult.ifPresent(entryType -> {
      model.getCategories().add(entryType);
    });
  }

  /**
   * Runs the Bullet Board application.
   *
   * @throws IllegalStateException If the application encounters an illegal state.
   * @throws IOException           If an I/O error occurs
   */
  @Override
  @FXML
  public void run() throws IllegalStateException, IOException {
    loadSplashScreen();
    showStartupDialog(); // Prompt for file navigation on startup
    initMaxLabels();
    updateScene();
    handleTextArea();
    editEntry.setOnAction(e -> handleEditEntry());
    deleteEntry.setOnAction(e -> handleDeleteEntry());
    create.setOnAction(e -> handleCreateButtonAction());
    save.setOnAction(e -> handleSaveButtonAction());
    categoriesButton.setOnAction(e -> handleCategoriesButtonAction());
    setPassword.setOnAction(e -> handleSetPassword());
    load.setOnAction(e -> handleLoadButtonAction());
    // Show load file dialog when load button is clicked
    edit.setOnAction(e -> handleEditButtonAction());
    setStyle();
  }

  /**
   * handles actions when create button is clicked
   */
  public void handleCreateButtonAction() {
    // Show dialog to choose task or event
    Dialog<String> entryTypeDialog = new ChoiceDialog<>("Task", "Task", "Event");
    entryTypeDialog.setTitle("Create Entry");
    entryTypeDialog.setHeaderText("Select Entry Type");
    entryTypeDialog.setContentText("Choose the type of entry you want to create:");
    Optional<String> entryTypeResult = entryTypeDialog.showAndWait();
    // Handle entry type selection
    entryTypeResult.ifPresent(entryType -> {
      if (entryType.equals("Task")) {
        createTask();
      } else if (entryType.equals("Event")) {
        createEvent();
      }
    });
  }

  /**
   * Displays a dialog to set a password and updates the model with the entered password.
   * The method prompts the user to set a password and sets it in the model if a password
   * is provided.
   */
  public void handleSetPassword() {
    TextInputDialog nameDialog = new TextInputDialog();
    nameDialog.setTitle("Password Protected");
    nameDialog.setHeaderText(null);
    nameDialog.setContentText("Please set your password:");
    Optional<String> nameResult = nameDialog.showAndWait();
    nameResult.ifPresent(password -> {
      model.setPassword(nameResult.get());
    });
  }

  /**
   * Displays a dialog to edit an entry for a selected day.
   * The method prompts the user to select a day, and then shows a dialog with the tasks and
   * events for that day.
   * The user can choose an entry to edit, and the corresponding edit window is opened.
   * After editing, the scene is updated to reflect the changes.
   */
  public void handleEditEntry() {
    List<String> dayOptions = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday");
    ChoiceDialog<String> dayDialog = new ChoiceDialog<>(dayOptions.get(0), dayOptions);
    dayDialog.setTitle("Edit Entry");
    dayDialog.setHeaderText("Select a day to edit an entry from:");
    dayDialog.setContentText("Day:");
    Optional<String> selectedDay = dayDialog.showAndWait();
    selectedDay.ifPresent(day -> {
      int dayIndex = dayOptions.indexOf(day);
      DayModel dayModel = model.getDays().get(dayIndex);
      Dialog<IcalendarItem> entriesDialog = new Dialog<>();
      entriesDialog.setTitle("Edit Entry");
      entriesDialog.setHeaderText("Tasks and Events for " + day + ":");
      entriesDialog.setResizable(true);
      ListView<IcalendarItem> itemsListView = new ListView<>();
      List<IcalendarItem> items = dayModel.getItems();
      for (IcalendarItem item : items) {
        itemsListView.getItems().add(item);
      }
      itemsListView.setCellFactory(param -> new ListCell<IcalendarItem>() {
        protected void updateItem(IcalendarItem item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(item.getName());
          }
        }
      });
      entriesDialog.getDialogPane().setContent(itemsListView);
      ButtonType editButton = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
      entriesDialog.getDialogPane().getButtonTypes().addAll(editButton, ButtonType.CANCEL);
      entriesDialog.setResultConverter(dialogButton -> {
        if (dialogButton == editButton) {
          IcalendarItem selectedItem = itemsListView.getSelectionModel().getSelectedItem();
          if (selectedItem != null) {
            editWindow(selectedItem);
          }
        }
        return null;
      });
      entriesDialog.showAndWait();
      updateScene();
    });
  }

  /**
   * Displays a dialog for editing a specific IcalendarItem.
   *
   * @param item The item to be edited.
   */
  private void editWindow(IcalendarItem item) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Edit Task");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20));
    TextField nameField = new TextField();
    gridPane.add(new Label("Name:"), 0, 0);
    gridPane.add(nameField, 1, 0);
    TextField descriptionField = new TextField();
    gridPane.add(new Label("Description:"), 0, 1);
    gridPane.add(descriptionField, 1, 1);
    ChoiceBox<String> categories = new ChoiceBox<>();
    categories.getItems().addAll(model.getCategories());
    gridPane.add(new Label("Category:"), 0, 2);
    gridPane.add(categories, 1, 2);
    dialog.getDialogPane().setContent(gridPane);
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == okButton) {
        String name = nameField.getText();
        String category = categories.getValue();
        String description = descriptionField.getText();
        if (name != "" && name != null) {
          item.setName(name);
        }
        if (category != "" && category != null) {
          item.setCategory(category);
        }
        if (description != "" && description != "no change" && description != null) {
          item.setDescription(description);
        }
        return ButtonType.OK;
      }
      return ButtonType.CANCEL;
    });
    dialog.showAndWait();
  }

  /**
   * Handles the deletion of an entry for a selected day.
   * Displays a dialog to select a day, and then a dialog to choose and
   * delete an entry for the selected day.
   */
  private void handleDeleteEntry() {
    List<String> dayOptions = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday");
    ChoiceDialog<String> dayDialog = new ChoiceDialog<>(dayOptions.get(0), dayOptions);
    dayDialog.setTitle("Delete Entry");
    dayDialog.setHeaderText("Select a day to delete an entry from:");
    dayDialog.setContentText("Day:");
    Optional<String> selectedDay = dayDialog.showAndWait();
    selectedDay.ifPresent(day -> {
      int dayIndex = dayOptions.indexOf(day);
      DayModel dayModel = model.getDays().get(dayIndex);
      Dialog<IcalendarItem> entriesDialog = new Dialog<>();
      entriesDialog.setTitle("Delete Entry");
      entriesDialog.setHeaderText("Tasks and Events for " + day + ":");
      entriesDialog.setResizable(true);
      List<IcalendarItem> items = dayModel.getItems();
      ListView<IcalendarItem> itemsListView = new ListView<>();
      for (IcalendarItem item : items) {
        itemsListView.getItems().add(item);
      }
      itemsListView.setCellFactory(param -> new ListCell<IcalendarItem>() {
        protected void updateItem(IcalendarItem item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(item.getName());
          }
        }
      });
      entriesDialog.getDialogPane().setContent(itemsListView);
      ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
      entriesDialog.getDialogPane().getButtonTypes().addAll(deleteButton, ButtonType.CANCEL);
      entriesDialog.setResultConverter(dialogButton -> {
        if (dialogButton == deleteButton) {
          IcalendarItem selectedItem = itemsListView.getSelectionModel().getSelectedItem();
          if (selectedItem != null) {
            items.remove(selectedItem);
          }
        }
        return null;
      });
      entriesDialog.showAndWait();
      updateScene();
    });
  }

  /**
   * Initializes the maximum events and tasks labels.
   * This method retrieves the maximum number of events and tasks from
   * the first day in the model's list of days
   * and updates the corresponding labels with the retrieved values.
   */
  public void initMaxLabels() {
    int maxevents = model.getDays().get(0).getMaxEvents();
    int maxtasks = model.getDays().get(0).getMaxTasks();
    maxTasks.setText("Max Tasks: " + maxtasks);
    maxEvents.setText("Max Events: " + maxevents);
  }

  /**
   * Handles the action when the Edit button is clicked.
   * This method displays input dialogs to allow the user to edit the maximum events and tasks
   * values.
   * The user can enter new values for maximum events and tasks, and the method updates the model
   * with the new values. The maximum events and tasks labels are then updated to reflect the
   * changes.
   */
  public void handleEditButtonAction() {
    TextInputDialog maxEventsInput = new TextInputDialog();
    maxEventsInput.setTitle("Edit Maximum Events");
    maxEventsInput.setHeaderText("Maximum Events: ");
    TextInputDialog maxTasksInput = new TextInputDialog();
    maxTasksInput.setTitle("Edit Maximum Tasks");
    maxTasksInput.setHeaderText("Maximum Tasks: ");
    Optional<String> maxTasks = maxTasksInput.showAndWait();
    Optional<String> maxEvents = maxEventsInput.showAndWait();
    maxTasks.ifPresent(maxTask ->
        model.setMaxTasks(Integer.parseInt(maxTask)));
    maxEvents.ifPresent(maxEvent ->
        model.setMaxEvents(Integer.parseInt(maxEvent)));
    this.maxTasks.setText("Max Tasks: " + model.getMaxTasks());
    this.maxEvents.setText("Max Events: " + model.getMaxEvents());
  }

  /**
   * Handles the action when the Save button is clicked.
   * This method opens a file chooser dialog to allow the user to select a file for
   * saving the bullet board.
   * If a file is selected, the file path is retrieved and passed to the saveToFile() method t
   * o save the bullet board data.
   */
  @FXML
  private void handleSaveButtonAction() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Bullet Board");
    File selectedFile = fileChooser.showSaveDialog(stage);
    if (selectedFile != null) {
      String filePath = selectedFile.getPath();
      saveToFile(filePath);
    }
  }

  /**
   * Creates a new task by prompting the user to enter the task name, description, day,
   * and category.
   * If the maximum tasks for the selected day is reached, a corresponding error message is shown.
   * Otherwise, a new Task object is created and added to the corresponding day model
   * in the WeekModel.
   * Finally, the scene is updated.
   */
  private void createTask() {
    TextInputDialog nameDialog = new TextInputDialog();
    nameDialog.setTitle("Task Name");
    nameDialog.setHeaderText(null);
    nameDialog.setContentText("Enter the name of the task:");

    TextInputDialog descriptionDialog = new TextInputDialog();
    descriptionDialog.setTitle("Task Description");
    descriptionDialog.setHeaderText(null);
    descriptionDialog.setContentText("Enter the description of the task:");

    List<Day> daysOfWeek = new ArrayList<>(Arrays.asList(Day.values()));
    ChoiceDialog<Day> dayDialog = new ChoiceDialog<>(daysOfWeek.get(0), daysOfWeek);
    dayDialog.setTitle("Task Day");
    dayDialog.setHeaderText(null);
    dayDialog.setContentText("Choose the day of the task:");
    ChoiceDialog<String> categoryDialog = new ChoiceDialog<>("none", model.getCategories());
    categoryDialog.setTitle("Task Category");
    categoryDialog.setHeaderText(null);
    categoryDialog.setContentText("Choose the category of the task:");
    Optional<String> nameResult = nameDialog.showAndWait();
    Optional<String> descriptionResult = descriptionDialog.showAndWait();
    Optional<Day> dayResult = dayDialog.showAndWait();
    Optional<String> categoryResult = categoryDialog.showAndWait();
    nameResult.ifPresent(name -> {
      descriptionResult.ifPresent(description -> {
        dayResult.ifPresent(day -> {
          categoryResult.ifPresent(category -> {
            DayModel dayModel = model.getDays().get(day.getDayNumber());
            if (dayModel.getTasks().size() >= dayModel.getMaxTasks()) {
              showAlert("Cannot create task",
                  "Maximum tasks reached for " + dayModel.getDayOfWeek());
            } else {
              Task newTask = new Task(name, description, day, category);
              dayModel.getItems().add(newTask);
              updateScene();
            }
          });
        });
      });
    });
  }

  /**
   * handles the splash screen for the application
   */
  private void loadSplashScreen() {
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Welcome");
    confirmation.setHeaderText("Welcome to your Bullet Board Journal");
    confirmation.setContentText("Press OK to continue");
    confirmation.getButtonTypes().remove(ButtonType.CANCEL);
    ButtonType result = confirmation.showAndWait().orElse(ButtonType.CANCEL);
  }


  /**
   * Creates a new event by prompting the user to enter the event name, description, day,
   * start time,duration, and category.
   * If the maximum events for the selected day is reached, a corresponding
   * error message is shown. Otherwise, a new Event object is created and added to the corresponding
   * day model in the WeekModel. Finally, the scene is updated.
   */
  private void createEvent() {
    TextInputDialog nameDialog = new TextInputDialog();
    nameDialog.setTitle("Event Name");
    nameDialog.setHeaderText(null);
    nameDialog.setContentText("Enter the name of the event:");

    TextInputDialog descriptionDialog = new TextInputDialog();
    descriptionDialog.setTitle("Event Description");
    descriptionDialog.setHeaderText(null);
    descriptionDialog.setContentText("Enter the description of the event:");
    List<Day> daysOfWeek = new ArrayList<>(Arrays.asList(Day.values()));
    ChoiceDialog<Day> dayDialog = new ChoiceDialog<>(daysOfWeek.get(0), daysOfWeek);
    dayDialog.setTitle("Event Day");
    dayDialog.setHeaderText(null);
    dayDialog.setContentText("Choose the day of the event:");
    TextInputDialog startTimeDialog = new TextInputDialog();
    startTimeDialog.setTitle("Event Start Time");
    startTimeDialog.setHeaderText(null);
    startTimeDialog.setContentText("Enter the start time of the event (HH:MM):");
    TextInputDialog durationDialog = new TextInputDialog();
    durationDialog.setTitle("Event Duration");
    durationDialog.setHeaderText(null);
    durationDialog.setContentText("Enter the duration of the event (in hours):");
    ChoiceDialog<String> categoryDialog = new ChoiceDialog<>("none",
        model.getCategories());
    categoryDialog.setTitle("Event Category");
    categoryDialog.setHeaderText(null);
    categoryDialog.setContentText("Choose the category of the event:");
    Optional<String> nameResult = nameDialog.showAndWait();
    Optional<String> descriptionResult = descriptionDialog.showAndWait();
    Optional<Day> dayResult = dayDialog.showAndWait();
    Optional<String> categoryResult = categoryDialog.showAndWait();
    Optional<String> startTimeResult = startTimeDialog.showAndWait();
    Optional<String> durationResult = durationDialog.showAndWait();
    nameResult.ifPresent(name -> {
      descriptionResult.ifPresent(description -> {
        dayResult.ifPresent(day -> {
          startTimeResult.ifPresent(startTime -> {
            durationResult.ifPresent(duration -> {
              categoryResult.ifPresent(category -> {
                DayModel dayModel = model.getDays().get(day.getDayNumber());
                if (dayModel.getEvents().size() >= dayModel.getMaxEvents()) {
                  showAlert("Cannot create event",
                      "Maximum events reached for " + dayModel.getDayOfWeek());
                } else {
                  Time eventStartTime = parseTime(startTime);
                  Time eventDuration = parseTime(duration);
                  Event newEvent =
                      new Event(name, description, day, eventStartTime, eventDuration, category,
                          "Event");
                  dayModel.getItems().add(newEvent);
                  updateScene();
                }
              });
            });
          });
        });
      });
    });
  }

  /**
   * Displays an alert dialog with the specified title and message.
   *
   * @param title   The title of the alert dialog.
   * @param message The message to be displayed in the alert dialog.
   */
  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.showAndWait();
  }

  /**
   * Updates the scene by clearing the existing content in the grid pane, creating day containers
   * for each day of the week, creating scroll panes for the day containers, and updating progress
   * bars.
   * Finally, the updated scene is displayed.
   */
  private void updateScene() {
    gridPane.getChildren().clear(); // Clear existing content
    double columnWidth = 150.0; // Fixed column width
    double rowHeight = 30.0; // Fixed row height
    double spacing = 10.0; // Spacing between tasks
    List<Day> daysOfWeek = Arrays.asList(Day.values());
    VBox incompleteTasksContainer = new VBox(spacing);
    incompleteTasksContainer.setPadding(new Insets(5));
    incompleteTasks = new ArrayList<>();
    for (Day day : daysOfWeek) {
      List<IcalendarItem> items = getItemsForDay(day);
      if (!items.isEmpty()) {
        VBox dayContainer = createDayContainer(items, day, columnWidth, spacing);
        ScrollPane scrollPane =
            createScrollPane(dayContainer, columnWidth, rowHeight, items.size());
        scrollPane.setStyle("-fx-background-color: #c0e1d0");
        gridPane.add(scrollPane, daysOfWeek.indexOf(day) + 1, 0);
      }
    }
    for (Task task : incompleteTasks) {
      BorderPane borderPane = createBorderPane(task, columnWidth);
      incompleteTasksContainer.getChildren().add(borderPane);
    }
    ScrollPane incompleteTasksScrollPane =
        createScrollPane(incompleteTasksContainer, columnWidth, rowHeight, incompleteTasks.size());
    incompleteTasksScrollPane.setStyle("-fx-background-color: #c0e1d0;");
    gridPane.add(incompleteTasksScrollPane, 0, 0);
    updateProgressBars();
    model.toString();
    stage.show();
  }

  /**
   * Creates a day container for the specified items and day, with the specified column width
   * and spacing.
   *
   * @param items       The list of IcalendarItems for the specified day.
   * @param day         The day for which the container is created.
   * @param columnWidth The width of the columns in the day container.
   * @param spacing     The spacing between tasks in the day container.
   * @return The created VBox day container.
   */
  private VBox createDayContainer(List<IcalendarItem> items, Day day, double columnWidth,
                                  double spacing) {
    VBox dayContainer = new VBox(spacing);
    dayContainer.setPadding(new Insets(5));

    for (IcalendarItem item : items) {
      if (item.getDayOfWeek() == day) {
        if (item instanceof Task && !((Task) item).getIsComplete()) {
          if (!incompleteTasks.contains(item)) {
            incompleteTasks.add((Task) item);
          }
        }
        BorderPane borderPane = createBorderPane(item, columnWidth);
        if (item instanceof Task) {
          CheckBox checkBox = new CheckBox(); // Create CheckBox
          checkBox.setSelected(((Task) item).getIsComplete());
          borderPane.setLeft(checkBox); // Set CheckBox on the left side of the BorderPane
          checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
              item.setIsComplete(true);
              updateProgressBars();
              updateScene();
            } else {
              item.setIsComplete(false);
              updateProgressBars();
              updateScene();
            }
          });
        }
        dayContainer.getChildren().add(borderPane);
      }

    }
    return dayContainer;
  }

  /**
   * Creates a scroll pane for the specified content, with the specified column width, row height,
   * and item count.
   *
   * @param content     The content to be displayed in the scroll pane.
   * @param columnWidth The width of the columns in the scroll pane.
   * @param rowHeight   The height of the rows in the scroll pane.
   * @param itemCount   The number of items in the scroll pane.
   * @return The created ScrollPane.
   */
  private ScrollPane createScrollPane(VBox content, double columnWidth, double rowHeight,
                                      int itemCount) {
    ScrollPane scrollPane = new ScrollPane(content);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setPrefViewportWidth(columnWidth);
    scrollPane.setPrefViewportHeight(rowHeight * itemCount);
    scrollPane.setStyle("-fx-background-color: #c0e1d0;");

    return scrollPane;
  }

  /**
   * Creates a border pane for the specified IcalendarItem with the specified column width.
   *
   * @param item        The IcalendarItem for which the border pane is created.
   * @param columnWidth The width of the columns in the border pane.
   * @return The created BorderPane.
   */
  private BorderPane createBorderPane(IcalendarItem item, double columnWidth) {
    BorderPane borderPane = new BorderPane();
    borderPane.setStyle("-fx-border-color: black; -fx-border-width: 1px; "
        + "-fx-background-color: #ffffff;");

    Label label = new Label();
    TextFlow flow = new TextFlow();
    String description = processDescription(flow, item);
    if (item instanceof Event) {
      label.setText(((Event) item).eventToString(description));
    } else if (item instanceof Task) {
      label.setText(((Task) item).taskToString(description));
    } else {
      label.setText(item.toString());
    }
    label.setWrapText(true);
    label.setMaxWidth(columnWidth - 10);

    VBox vbox = new VBox(label, flow);
    vbox.setPadding(new Insets(5));

    // Set the wrapping width for each Text node within the TextFlow
    flow.getChildren().forEach(node -> {
      if (node instanceof Text) {
        ((Text) node).setWrappingWidth(columnWidth - 10);
      }
    });
    borderPane.setCenter(vbox);
    return borderPane;
  }

  /**
   * Processes the description of an iCalendar item to detect and handle HTTP/HTTPS links.
   * This method takes a TextFlow and an iCalendar item as parameters. It searches for HTTP/HTTPS
   * links in the item's
   * description using a regular expression pattern. Each link is converted into a Hyperlink
   * within the TextFlow,
   * allowing users to click on the links to open them. The remaining non-link text is returned
   * as a string.
   *
   * @param textFlow The TextFlow to display the processed description.
   * @param item The iCalendar item containing the description to be processed.
   * @return The remaining non-link text from the description.
   */
  private String processDescription(TextFlow textFlow, IcalendarItem item) {
    // Define the regular expression pattern for detecting HTTP/HTTPS links
    String urlPattern = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]";
    String description = item.getDescription();
    String remainderText = "";
    Pattern pattern = Pattern.compile(urlPattern);
    Matcher matcher = pattern.matcher(description);
    int lastIndex = 0;
    while (matcher.find()) {
      int matchStart = matcher.start();
      int matchEnd = matcher.end();
      if (lastIndex != matchStart) {
        remainderText = description.substring(lastIndex, matchStart);
      }

      String url = description.substring(matchStart, matchEnd);
      Hyperlink hyperlink = new Hyperlink(url);
      hyperlink.setWrapText(true);
      hyperlink.setOnAction(event -> services.showDocument(url));
      textFlow.getChildren().add(hyperlink);
      lastIndex = matchEnd;
    }
    if (lastIndex < description.length()) {
      remainderText += description.substring(lastIndex);
    }
    return remainderText;
  }


  /**
   * Retrieves the list of IcalendarItems for the specified day from the WeekModel.
   *
   * @param day The day for which the items are retrieved.
   * @return The list of IcalendarItems for the specified day.
   */
  private List<IcalendarItem> getItemsForDay(Day day) {
    for (DayModel dayModel : model.getDays()) {
      if (dayModel.getDayOfWeek() == day) {
        return dayModel.getItems();
      }
    }
    return Collections.emptyList();
  }

  /**
   * Parses the given time string into a Time object.
   *
   * @param timeString The time string to be parsed.
   * @return The parsed Time object.
   * @throws IllegalArgumentException If the time string has an invalid format.
   */
  private Time parseTime(String timeString) {
    String[] timeParts = timeString.split(":");
    int hour;
    int minute;
    if (timeParts.length == 1) {
      hour = Integer.parseInt(timeParts[0]);
      minute = 0; // Assume minutes as zero
    } else if (timeParts.length == 2) {
      hour = Integer.parseInt(timeParts[0]);
      minute = Integer.parseInt(timeParts[1]);
    } else {
      throw new IllegalArgumentException("Invalid time format. Expected format: HH:MM");
    }
    return new Time(hour, minute);
  }

  /**
   * Saves the current WeekModel to a JSON file at the specified file path.
   *
   * @param filePath The path of the file where the WeekModel should be saved.
   * @throws RuntimeException If an error occurs while saving the file.
   */
  public void saveToFile(String filePath) {
    try {
      JsonFileWriter.writeJsonToFile(filePath, model);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads a WeekModel from a JSON file at the specified file path and updates the scene.
   *
   * @param filePath The path of the file from which the WeekModel should be loaded.
   */
  public void loadFromFile(String filePath) {
    JsonFileReader reader = new JsonFileReader();
    WeekModel model = reader.loadFromFile(filePath);
    this.model = model;
    updateScene(); // Add this line to update the scene immediately after loading
  }

  /**
   * Updates the progress bars based on the completion status of the tasks in the WeekModel.
   */
  public void updateProgressBars() {
    List<Double> list = model.weekCompleted();
    List<Integer> left = model.weekLeft();
    sundayProgress.setProgress(list.get(0));
    sunday.setText("Sunday: " + left.get(0));
    mondayProgress.setProgress(list.get(1));
    monday.setText("Monday: " + left.get(1));
    tuesdayProgress.setProgress(list.get(2));
    tuesday.setText("Tuesday: " + left.get(2));
    wednesdayProgress.setProgress(list.get(3));
    wednesday.setText("Wednesday: " + left.get(3));
    thursdayProgress.setProgress(list.get(4));
    thursday.setText("Thursday: " + left.get(4));
    fridayProgress.setProgress(list.get(5));
    friday.setText("Friday: " + left.get(5));
    saturdayProgress.setProgress(list.get(6));
    saturday.setText("Saturday: " + left.get(6));
  }
}
