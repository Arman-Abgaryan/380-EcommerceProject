module com.example.comp380project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.comp380project to javafx.fxml;
    exports com.example.comp380project;
}