package com.example.jira;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ConnPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<AdminTimeLinePage.Task> tasks = FXCollections.observableArrayList(
                new AdminTimeLinePage.Task("Sample Task 1", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 5), "Ongoing"),
                new AdminTimeLinePage.Task("Sample Task 2", LocalDate.of(2024, 10, 3), LocalDate.of(2024, 10, 10), "Completed")
        );

        VBox layout = new VBox(20);
        Button openPage6 = new Button("Open Admin's Timeline Page");
        Button openPage5 = new Button("Open Timeline Page");

        openPage6.setOnAction(e -> {
            Stage stage = new Stage();
            AdminTimeLinePage page6 = new AdminTimeLinePage(tasks);
            page6.start(stage);
        });

        openPage5.setOnAction(e -> {
            Stage stage = new Stage();
            TimelinePage page5 = new TimelinePage(tasks);
            page5.start(stage);
        });

        layout.getChildren().addAll(openPage6, openPage5);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
