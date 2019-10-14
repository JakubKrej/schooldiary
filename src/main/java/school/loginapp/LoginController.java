package school.loginapp;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Button buttonSIGN;


    public void initialize(URL url, ResourceBundle rb) {
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected");
        }else{
            this.dbstatus.setText("Not connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }


    @FXML
    public void Login(ActionEvent event) {



        try {
            Connection conn = dbConnection.getConnection();

            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((Option) this.combobox.getValue()).toString())) {
                Stage stage = (Stage) this.loginbutton.getScene().getWindow();
                stage.close();

                String b = loginModel.isLogin1(this.username.getText(), this.password.getText(), ((Option) this.combobox.getValue()).toString());


                switch (((Option) this.combobox.getValue()).toString()) {

                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        System.out.println(b+22);
                        studentLogin();
                        break;

                }


            } else {
                this.loginstatus.setText("Try again!");
            }

            conn.close();

        } catch (Exception e) {

        }


    }







    public void studentLogin(){
        try{
            Stage adminstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/fxml/studentFXML.fxml").openStream());


            String b = loginModel.isLogin1(this.username.getText(), this.password.getText(), ((Option) this.combobox.getValue()).toString());
            System.out.println(b);
            StudentsController SC = new StudentsController(b);


            Scene scene = new Scene(root);
            adminstage.setScene(scene);
            adminstage.setTitle("Student Dashboard");
            adminstage.setResizable(false);
            adminstage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void adminLogin(){
        try{
            Stage adminstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/fxml/adminFXML.fxml").openStream());


            Scene scene = new Scene(root);
            adminstage.setScene(scene);
            adminstage.setTitle("Admin Dashboard");
            adminstage.setResizable(true);
            adminstage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void signUp(ActionEvent event){


        try{

            Stage stage = (Stage) this.buttonSIGN.getScene().getWindow();
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
}
