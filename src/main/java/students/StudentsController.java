package students;

import admin.AdminController;
import admin.StudentData;
import dbUtil.dbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController {

    @FXML
    private Label nameLABELST;
    @FXML
    private Label lastnameLABELST;
    @FXML
    private Label emailLABELST;

    private dbConnection dc;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

        this.dc = new dbConnection();



    }




}
