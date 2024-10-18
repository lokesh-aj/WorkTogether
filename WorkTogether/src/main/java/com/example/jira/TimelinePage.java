package com.example.jira;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimelinePage extends Application {

    private ObservableList<AdminTimeLinePage.Task> tasks;

    public TimelinePage(ObservableList<AdminTimeLinePage.Task> tasks) {
        this.tasks = tasks;
    }
    public void start(Stage primaryStage) {
        TableView<AdminTimeLinePage.Task> taskTableView = new TableView<>();

        TableColumn<AdminTimeLinePage.Task, String> nameColumn = new TableColumn<>("Task Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<AdminTimeLinePage.Task, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setMinWidth(150);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<AdminTimeLinePage.Task, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setMinWidth(150);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<AdminTimeLinePage.Task, String> durationColumn = new TableColumn<>("Duration (Days)");
        durationColumn.setMinWidth(150);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<AdminTimeLinePage.Task, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusColumn.setCellFactory(cell -> new TableCell<AdminTimeLinePage.Task, String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setText(item);
                    if ("Ongoing".equals(item)) {
                        setStyle("-fx-background-color: #28a745;");
                    } else if ("Completed".equals(item)) {
                        setStyle("-fx-background-color: #007bff;");
                    } else if ("About to Start".equals(item)) {
                        setStyle("-fx-background-color: #ffeb3b;");
                    }
                }
            }
        });

        taskTableView.getColumns().addAll(nameColumn, startDateColumn, endDateColumn, durationColumn, statusColumn);

        taskTableView.setItems(tasks);

        BorderPane root = new BorderPane(taskTableView);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Timeline Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
