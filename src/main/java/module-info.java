module SchoolSystem1 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports school.loginapp to javafx.graphics;
    opens school.loginapp to javafx.fxml;
}