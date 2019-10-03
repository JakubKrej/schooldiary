package admin;

import admin.StudentData;
import dbUtil.dbConnection;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import school.loginapp.LoginApp;

import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.sql.*;
import java.util.ResourceBundle;

public class signStudentController implements Initializable {

    @FXML
    private TextField userFIRSTNAME;
    @FXML
    private TextField userLASTNAME;
    @FXML
    private TextField userEMAIL;
    @FXML
    private DatePicker signDATE;

    @FXML
    private TextField userLOGIN;
    @FXML
    private TextField userPASSWORD;
    @FXML
    private Button joinBUTTON;
    @FXML
    private Label errorTEXT;
    @FXML
    private Button backLOGINMENU;


    private dbConnection dc;


    public signStudentController(){

    }

    public void initialize(URL url, ResourceBundle rb){
        this.dc = new dbConnection();
    }

    public void joinUS(ActionEvent event) throws Exception {

        String sqlUsers = "INSERT INTO 'login' ('username', 'password', 'division') VALUES (?,?, ?)";
        String sql = "INSERT INTO `students`( `fname`, `lname`, `email`, `DOB`) VALUES ( ?, ?, ?, ?)";

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUsers);
            PreparedStatement stmt2 = conn.prepareStatement(sql);

            if(this.userLOGIN.getText().equals("") || this.userPASSWORD.getText().equals("") || this.userFIRSTNAME.getText().equals("") || this.userLASTNAME.getText().equals("") || this.userEMAIL.getText().equals("") || this.signDATE.getEditor().getText().equals("")){
                this.errorTEXT.setText("   Complete all fields!");

            }else {

                stmt.setString(1, this.userLOGIN.getText());
                stmt.setString(2, this.userPASSWORD.getText());
                stmt.setString(3, "Student");

                stmt2.setString(1, this.userFIRSTNAME.getText());
                stmt2.setString(2, this.userLASTNAME.getText());
                stmt2.setString(3, this.userEMAIL.getText());
                stmt2.setString(4, this.signDATE.getEditor().getText());

                stmt.execute();
                stmt2.execute();



                Stage stage = (Stage) this.joinBUTTON.getScene().getWindow();
                stage.close();


                LoginApp lgapp = new LoginApp();
                lgapp.start(stage);


                String newstudent = "SELECT id FROM students WHERE id = (SELECT MAX(id) FROM students)";


                ResultSet rs = conn.createStatement().executeQuery(newstudent);
                System.out.println(rs.getString(1));
                String idlaststudent = rs.getString(1);

                String newtab = "CREATE TABLE '" + idlaststudent + "' ( '1' TEXT );";
                PreparedStatement ps = conn.prepareStatement(newtab);
                System.out.println(newtab);
                ps.execute();
                conn.close();

            }



        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }



    }

    public void goBack(ActionEvent event){

        try {
            Stage stage = (Stage) this.backLOGINMENU.getScene().getWindow();
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

    public void newStudentTable(){


    }
}
