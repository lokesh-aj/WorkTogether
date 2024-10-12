module com.example.jira {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jira to javafx.fxml;
    exports com.example.jira;
}