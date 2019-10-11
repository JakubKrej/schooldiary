module SchoolSystem1 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;

    exports school.loginapp to javafx.graphics;
    opens school.loginapp to javafx.fxml;
    opens admin to javafx.fxml, javafx.base;
    opens students to javafx.fxml, javafx.base;
}