package com.example.jira;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Cursor;

import java.io.InputStream;

public class Page3 extends Application {
    private UserData userData;

    public Page3(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label title = new Label("What kind of work do you do?");
        title.setFont(new Font(36));
        title.setTextFill(Color.BLACK);

        Label description = new Label("This helps us suggest templates that help your team do their best work.");
        description.setFont(new Font(18));
        description.setTextFill(Color.BLACK);

        Label selectedOptionsLabel = new Label("Selected Options: " + String.join(", ", userData.getSelectedOptions()));
        selectedOptionsLabel.setFont(new Font(16));
        selectedOptionsLabel.setTextFill(Color.BLACK);

        root.getChildren().addAll(title, description, selectedOptionsLabel);

        HBox[] options = {
                createOption("Software Development", "/images/sw_dev.png"),
                createOption("Marketing", "/images/marketing.png"),
                createOption("Product Management", "/images/prod_mang.png"),
                createOption("Design", "/images/desing.png"),
                createOption("Project Management", "/images/pm.png"),
                createOption("Operations", "/images/operations.png"),
                createOption("IT Support", "/images/itsupport.png"),
                createOption("Human Resources", "/images/hr.png"),
                createOption("Customer Service", "/images/ervices.png"),
                createOption("Legal", "/images/legal.png"),
                createOption("Finance", "/images/cash.png"),
                createOption("Sales", "/images/sales.png"),
                createOption("Data Science", "/images/ds.png"),
                createOption("Other", "/images/others.png")
        };

        for (int i = 0; i < options.length; i += 3) {
            HBox row = new HBox(20);
            row.setAlignment(Pos.CENTER);
            row.getChildren().add(options[i]);

            if (i + 1 < options.length) {
                row.getChildren().add(options[i + 1]);
            }
            if (i + 2 < options.length) {
                row.getChildren().add(options[i + 2]);
            }
            root.getChildren().add(row);
        }

        Button goBackButton = new Button("Go Back");
        goBackButton.setPrefWidth(100);
        goBackButton.setStyle("-fx-border-color: black; -fx-font-size: 14;");

        goBackButton.setOnAction(e -> {
            Page2 page2 = new Page2(userData);
            page2.start(primaryStage);
        });

        root.getChildren().add(goBackButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Work Type Selection");
        primaryStage.show();
    }

    private HBox createOption(String labelText, String imagePath) {
        Image image = loadImage(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        Label label = new Label(labelText);
        label.setFont(new Font(18));
        label.setPadding(new Insets(0, 0, 0, 10));

        HBox hBox = new HBox(10, imageView, label);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10; -fx-background-color: white; -fx-border-radius: 5;");
        hBox.setPrefWidth(250);

        hBox.setOnMouseClicked(event -> {
            userData.setProjectType(labelText);
            Page4 page4 = new Page4(userData);
            page4.start((Stage) hBox.getScene().getWindow());
        });

        hBox.setOnMouseEntered(event -> {
            hBox.setStyle("-fx-border-color: darkgrey; -fx-border-width: 2; -fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-radius: 10;");
            hBox.setCursor(Cursor.HAND);
        });

        hBox.setOnMouseExited(event -> {
            hBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10; -fx-background-color: white; -fx-border-radius: 5;");
            hBox.setCursor(Cursor.DEFAULT);
        });

        return hBox;
    }

    private Image loadImage(String path) {
        InputStream input = getClass().getResourceAsStream(path);
        if (input == null) {
            System.err.println("Image not found: " + path);
            return null;
        }
        return new Image(input);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
