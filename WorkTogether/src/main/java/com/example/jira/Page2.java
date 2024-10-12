package com.example.jira;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Page2 extends Application {
    private UserData userData; // Instance variable to hold UserData

    public Page2(UserData userData) {
        this.userData = userData; // Use constructor to initialize UserData
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page 2");

        Text title = new Text("How does your team plan to use WorkTogether?");
        title.setFont(new Font("Arial", 24));
        title.setFill(Color.BLACK);

        Text subtitle = new Text("Your choices won’t limit what you can do");
        subtitle.setFont(new Font("Arial", 14));
        subtitle.setFill(Color.GRAY);

        // Create checkboxes
        CheckBox option1 = new CheckBox("Track bugs");
        CheckBox option2 = new CheckBox("Manage tasks");
        CheckBox option3 = new CheckBox("Estimate time & effort");
        CheckBox option4 = new CheckBox("Prioritize work");
        CheckBox option5 = new CheckBox("Improve team processes");
        CheckBox option6 = new CheckBox("Map work dependencies");

        // Set styles for checkboxes
        option1.setStyle("-fx-font-size: 16;");
        option2.setStyle("-fx-font-size: 16;");
        option3.setStyle("-fx-font-size: 16;");
        option4.setStyle("-fx-font-size: 16;");
        option5.setStyle("-fx-font-size: 16;");
        option6.setStyle("-fx-font-size: 16;");

        // Add border and padding around each checkbox
        VBox borderedOption1 = createBorderedOption(option1);
        VBox borderedOption2 = createBorderedOption(option2);
        VBox borderedOption3 = createBorderedOption(option3);
        VBox borderedOption4 = createBorderedOption(option4);
        VBox borderedOption5 = createBorderedOption(option5);
        VBox borderedOption6 = createBorderedOption(option6);

        // Organize checkboxes into HBoxes
        HBox row1 = new HBox(20, borderedOption1, borderedOption2);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(20, borderedOption3, borderedOption4);
        row2.setAlignment(Pos.CENTER);
        HBox row3 = new HBox(20, borderedOption5, borderedOption6);
        row3.setAlignment(Pos.CENTER);

        Text instructionText = new Text("Choose up to 3 options");
        instructionText.setFont(new Font("Arial", 18));
        instructionText.setFill(Color.BLACK);

        Button backButton = new Button("Back");
        backButton.setPrefWidth(100);
        backButton.setStyle("-fx-border-color: black; -fx-font-size: 14;");

        Button continueButton = new Button("Continue");
        continueButton.setPrefWidth(100);
        continueButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14;");

        // Handle checkbox selection
        option1.setOnAction(e -> handleOptionSelection(option1, option1.getText(), option1.isSelected()));
        option2.setOnAction(e -> handleOptionSelection(option2, option2.getText(), option2.isSelected()));
        option3.setOnAction(e -> handleOptionSelection(option3, option3.getText(), option3.isSelected()));
        option4.setOnAction(e -> handleOptionSelection(option4, option4.getText(), option4.isSelected()));
        option5.setOnAction(e -> handleOptionSelection(option5, option5.getText(), option5.isSelected()));
        option6.setOnAction(e -> handleOptionSelection(option6, option6.getText(), option6.isSelected()));

        // Back button action to go back to Page 1
        backButton.setOnAction(e -> {
            Page1 page1 = new Page1();
            page1.start(primaryStage);  // Load Page 1
        });

        // Continue button action to go to Page 3
        continueButton.setOnAction(e -> {
            userData.limitToMax(3); // Limit to 3 options
            Page3 page3 = new Page3(userData); // Pass UserData to Page3
            page3.start(primaryStage);  // Load Page 3
        });

        // Button container
        HBox buttonContainer = new HBox(20, backButton, continueButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // Main layout
        VBox mainLayout = new VBox(20, title, subtitle, instructionText, row1, row2, row3, buttonContainer);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Set scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleOptionSelection(CheckBox checkBox, String option, boolean isSelected) {
        if (isSelected) {
            if (userData.getSelectedOptions().size() < 3) {
                userData.addOption(option); // Add the option if less than 3 are selected
            } else {
                showAlert("Selection Limit", "You can only select up to 3 options.");
                checkBox.setSelected(false); // Deselect the current checkbox
            }
        } else {
            userData.getSelectedOptions().remove(option); // Remove if unselected
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION); // Create an information alert
        alert.setTitle(title); // Set the title
        alert.setHeaderText(null); // No header
        alert.setContentText(message); // Set the content text
        alert.showAndWait(); // Show the alert and wait for it to close
    }

    private VBox createBorderedOption(CheckBox checkBox) {
        VBox borderedBox = new VBox(checkBox);
        borderedBox.setPadding(new Insets(10));
        borderedBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
        borderedBox.setAlignment(Pos.CENTER);
        borderedBox.setPrefWidth(200); // Set width to match the image proportions
        return borderedBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
