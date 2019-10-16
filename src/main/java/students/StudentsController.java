package students;

import admin.AdminController;
import admin.Marks;
import admin.StudentData;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import school.loginapp.LoginController;
import school.loginapp.LoginModel;
import school.loginapp.Option;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private Label nameLABELST;
    @FXML
    private Label lastnameLABELST;
    @FXML
    private Label emailLABELST;
    @FXML
    private Label idLABEL;
    @FXML
    private TableView<Marks> stmarksTABLE;
    @FXML
    private Button logOUT;

    private dbConnection dc;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        this.dc = new dbConnection();


    }


    public StudentsController(){

    }

    public void SetLabelID(String s2){
        this.idLABEL.setText(s2);
    }

    public void logOut(ActionEvent event){


        try {
            Stage stage = (Stage) this.logOUT.getScene().getWindow();
            stage.close();

            Stage singstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("School Managment System");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
