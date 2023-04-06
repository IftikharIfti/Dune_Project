module com.example.begindune {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.begindune to javafx.fxml;
    exports com.example.begindune;
}