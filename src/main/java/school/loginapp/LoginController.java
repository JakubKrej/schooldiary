package school.loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    LoginModel loginModel = new LoginModel();

    @FXML
    private AnchorPane rootPene;

    @FXML
    private Label dbstatus;

    @FXML
    private Label loginstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Option> combobox;

    @FXML
    private Button loginbutton;

    @FXML
    private Button signbutton;


    public void initialize(URL url, ResourceBundle rb) {
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected");
        }else{
            this.dbstatus.setText("Not connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    @FXML
    public void signUp(ActionEvent event){


        try{

            Stage stage = (Stage) this.signbutton.getScene().getWindow();
            stage.close();

            Stage singstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/fxml/signFXML.fxml").openStream());

            Scene scene = new Scene(root);
            singstage.setScene(scene);
            singstage.setTitle("SIGN UP!");
            singstage.setResizable(true);
            singstage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void Login(ActionEvent event){

        try{

            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((Option)this.combobox.getValue()).toString())){
                Stage stage = (Stage) this.loginbutton.getScene().getWindow();
                stage.close();

                switch (((Option) this.combobox.getValue()).toString()){

                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }

            }else{
                    this.loginstatus.setText("Try again!");
            }

        } catch (Exception e) {

        }

    }

    public void studentLogin(){
        try{
            Stage adminstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/fxml/studentFXML.fxml").openStream());

            AdminController adminController = (AdminController)loader.getController();

            Scene scene = new Scene(root);
            adminstage.setScene(scene);
            adminstage.setTitle("Student Dashboard");
            adminstage.setResizable(false);
            adminstage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void adminLogin(){
        try{
            Stage adminstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/fxml/adminFXML.fxml").openStream());

            AdminController adminController = (AdminController)loader.getController();

            Scene scene = new Scene(root);
            adminstage.setScene(scene);
            adminstage.setTitle("Admin Dashboard");
            adminstage.setResizable(true);
            adminstage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
