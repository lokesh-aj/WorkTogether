package com.example.jira;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AdminTimeLinePage extends Application {

    private ObservableList<Task> tasks;
    private Task taskToUpdate;
    private TableView<Task> taskTableView;
    private Stage primaryStage;

    public AdminTimeLinePage(ObservableList<Task> tasks) {
        this.tasks = tasks;
        this.taskToUpdate = null;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox formLayout = createForm();
        taskTableView = createTaskTable();

        BorderPane root = new BorderPane();
        root.setCenter(taskTableView);
        root.setBottom(formLayout);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Admin's Timeline Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createForm() {
        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(20));

        Label nameLabel = new Label("Task Name:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter task name");

        Label startDateLabel = new Label("Start Date:");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Select start date");

        Label endDateLabel = new Label("End Date:");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("Select end date");

        Label statusLabel = new Label("Status:");
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Ongoing", "Completed", "About to Start");
        statusComboBox.setValue("Ongoing");

        Button saveButton = new Button("Add Task");
        saveButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        saveButton.setOnAction(e -> {
            String taskName = nameInput.getText();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            String status = statusComboBox.getValue();

            if (taskName.isEmpty() || startDate == null || endDate == null || startDate.isAfter(endDate)) {
                showAlert("Invalid Input", "Please ensure all fields are filled and dates are valid.");
            } else {
                if (taskToUpdate == null) {
                    Task newTask = new Task(taskName, startDate, endDate, status);
                    tasks.add(newTask);
                } else {
                    taskToUpdate.setName(taskName);
                    taskToUpdate.setStartDate(startDate);
                    taskToUpdate.setEndDate(endDate);
                    taskToUpdate.setStatus(status);
                    tasks.set(tasks.indexOf(taskToUpdate), taskToUpdate);
                    taskToUpdate = null;
                }

                nameInput.clear();
                startDatePicker.setValue(null);
                endDatePicker.setValue(null);
                statusComboBox.setValue("Ongoing");

                taskTableView.setItems(tasks);
            }
        });

        Button editButton = new Button("Edit Task");
        editButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        editButton.setOnAction(e -> {
            Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                taskToUpdate = selectedTask;
                nameInput.setText(selectedTask.getName());
                startDatePicker.setValue(LocalDate.parse(selectedTask.getStartDate()));
                endDatePicker.setValue(LocalDate.parse(selectedTask.getEndDate()));
                statusComboBox.setValue(selectedTask.getStatus());
                saveButton.setText("Update Task");
            } else {
                showAlert("No Selection", "Please select a task to edit.");
            }
        });

        formLayout.getChildren().addAll(nameLabel, nameInput, startDateLabel, startDatePicker, endDateLabel, endDatePicker, statusLabel, statusComboBox, saveButton, editButton);

        return formLayout;
    }

    private TableView<Task> createTaskTable() {
        TableView<Task> table = new TableView<>();

        TableColumn<Task, String> nameColumn = new TableColumn<>("Task Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setMinWidth(150);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Task, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setMinWidth(150);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<Task, String> durationColumn = new TableColumn<>("Duration (Days)");
        durationColumn.setMinWidth(150);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Task, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(nameColumn, startDateColumn, endDateColumn, durationColumn, statusColumn);
        table.setItems(tasks);

        return table;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Task {
        private final SimpleStringProperty name;
        private final SimpleStringProperty startDate;
        private final SimpleStringProperty endDate;
        private final SimpleStringProperty duration;
        private final SimpleStringProperty status;

        public Task(String name, LocalDate startDate, LocalDate endDate, String status) {
            this.name = new SimpleStringProperty(name);
            this.startDate = new SimpleStringProperty(startDate.toString());
            this.endDate = new SimpleStringProperty(endDate.toString());
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            this.duration = new SimpleStringProperty(String.valueOf(daysBetween));
            this.status = new SimpleStringProperty(status);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getStartDate() {
            return startDate.get();
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate.set(startDate.toString());
        }

        public String getEndDate() {
            return endDate.get();
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate.set(endDate.toString());
        }

        public String getDuration() {
            return duration.get();
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String status) {
            this.status.set(status);
        }
    }
}
