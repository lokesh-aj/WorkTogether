package com.example.jira;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Page4 extends Application {
    private UserData userData;

    public Page4(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Summary Page");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Summary of Your Selections");
        title.setFont(new Font("Arial", 24));
        title.setFill(Color.BLACK);

        if (userData == null) {
            Text errorText = new Text("Error: User data not found.");
            errorText.setFont(new Font("Arial", 18));
            errorText.setFill(Color.RED);
            layout.getChildren().add(errorText);
        } else {
            StringBuilder summary = new StringBuilder("Site Name: " + userData.getSiteName() + "\n" +
                    "Selected Work Type: " + userData.getProjectType() + "\n" +
                    "Selected Options: " + String.join(", ", userData.getSelectedOptions()));

            Text summaryText = new Text(summary.toString());
            summaryText.setFont(new Font("Arial", 18));
            summaryText.setFill(Color.DARKBLUE);

            layout.getChildren().add(summaryText);
        }

        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 50 10 50;");
        restartButton.setCursor(javafx.scene.Cursor.HAND);

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 50 10 50;");
        exitButton.setCursor(javafx.scene.Cursor.HAND);

        restartButton.setOnAction(e -> {
            Page1 page1 = new Page1();
            page1.start(primaryStage);
        });

        exitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    primaryStage.close();
                }
            });
        });

        layout.getChildren().addAll(title, restartButton, exitButton);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
