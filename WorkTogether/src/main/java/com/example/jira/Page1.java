package com.example.jira;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Page1 extends Application {
    private String siteName; // Variable to store the site name

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page 1");

        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background_image.jpg"));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);

        StackPane layout = new StackPane();
        layout.setPadding(new Insets(50));

        Text jiraLogo = new Text("WorkTogether");
        jiraLogo.setFont(new Font("Arial", 40));
        jiraLogo.setFill(Color.DARKBLUE);

        Text title = new Text("Let's name your site");
        title.setFont(new Font("Arial", 24));
        title.setFill(Color.BLACK);

        Text subtitle = new Text("Your site name is part of your WorkTogether URL. Most people use their team or company name.");
        subtitle.setFont(new Font("Arial", 14));
        subtitle.setFill(Color.GRAY);
        subtitle.setWrappingWidth(400);

        TextFlow subtitleFlow = new TextFlow(subtitle);
        subtitleFlow.setTextAlignment(TextAlignment.CENTER);

        VBox subtitleContainer = new VBox(subtitleFlow);
        subtitleContainer.setAlignment(Pos.CENTER);

        Label yourSiteLabel = new Label("Your site");
        yourSiteLabel.setFont(new Font("Arial", 18));
        TextField siteInput = new TextField();
        siteInput.setPromptText("worktogether93");
        siteInput.setStyle("-fx-border-color: #00a3e0; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5 10 5 10;");
        siteInput.setPrefWidth(300);

        Label urlSuffix = new Label(".WorkTogether.net");
        urlSuffix.setFont(new Font("Arial", 18));
        urlSuffix.setStyle("-fx-padding: 5 10 5 10;");

        HBox siteInputBox = new HBox(5);
        siteInputBox.setAlignment(Pos.CENTER);
        siteInputBox.getChildren().addAll(siteInput, urlSuffix);

        Text suggestionText = new Text("This site name is just a suggestion. Feel free to change to something your team will recognize.");
        suggestionText.setFont(new Font("Arial", 12));
        suggestionText.setFill(Color.BLACK);
        suggestionText.setWrappingWidth(400);

        Button continueButton = new Button("Continue");
        continueButton.setPrefWidth(100);
        continueButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14;");
        continueButton.setOnAction(e -> {
            UserData userData = new UserData();
            userData.setSiteName(siteInput.getText() + ".WorkTogether.net");
            Page2 page2 = new Page2(userData);
            page2.start(primaryStage);
        });

        Text atlassianLogo = new Text("WorkTogether");
        atlassianLogo.setFont(new Font("Arial", 16));
        atlassianLogo.setFill(Color.DARKBLUE);

        VBox contentLayout = new VBox(20);
        contentLayout.setPadding(new Insets(20));
        contentLayout.setAlignment(Pos.TOP_CENTER);
        contentLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); " +
                "-fx-border-color: #00a3e0; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10;");

        contentLayout.getChildren().addAll(jiraLogo, title, subtitleContainer, yourSiteLabel, siteInputBox, suggestionText, continueButton, atlassianLogo);

        layout.getChildren().addAll(imageView, contentLayout);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public String getData() {
        return "Page 1: Let's name your site. Your site name is part of your WorkTogether URL.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
