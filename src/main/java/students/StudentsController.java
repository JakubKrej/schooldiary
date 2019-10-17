package students;

import admin.AdminController;
import admin.Marks;
import admin.MarksData;
import admin.StudentData;
import dbUtil.dbConnection;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import school.loginapp.LoginController;
import school.loginapp.LoginModel;
import school.loginapp.Option;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private TableView<MarksData> marksTABLEST;
    @FXML
    private TableColumn<MarksData,String > stmarksCOLUMN;
    @FXML
    private Button logOUT;
    @FXML
    private Button loadmarksBUTTON;

    private ObservableList<MarksData> data3;


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

    @FXML
    private void loadMarks(ActionEvent event){

        try {
            Connection conn = dbConnection.getConnection();
            this.data3 = FXCollections.observableArrayList();


            ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM '" + this.idLABEL.getText() + "' ;");
            while (rs1.next()) {
                this.data3.add(new MarksData(rs1.getString("1")));

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }



        this.stmarksCOLUMN.setCellValueFactory(new PropertyValueFactory<MarksData,String>("mark"));

        this.marksTABLEST.setItems(null);
        this.marksTABLEST.setItems(this.data3);
    }



    public void getInfo() {

        try {

            Connection conn = dbConnection.getConnection();
            data3 = FXCollections.observableArrayList();

            String selectinfo = "SELECT * FROM students where id_users = '" + this.idLABEL.getText() + "' ;";
            System.out.println(selectinfo);

            ResultSet rs = conn.createStatement().executeQuery(selectinfo);
            this.nameLABELST.setText(rs.getString(2));
            this.lastnameLABELST.setText(rs.getString(3));
            this.emailLABELST.setText(rs.getString(4));


            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

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
