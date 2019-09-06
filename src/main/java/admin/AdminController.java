package admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{
    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableColumn<StudentData, String> idcolumn;
    @FXML
    private TableColumn<StudentData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentData, String> emailcolumn;
    @FXML
    private TableColumn<StudentData, String> dobcolumn;
    @FXML
    private Label errorLABEL;
    @FXML
    private Button deleteBUTTON;

    private ObservableList<StudentData> data;
    private dbConnection dc;

    public AdminController() {
    }

    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();
    }

    @FXML
    private void loadStudentData(ActionEvent event)
    {
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }



        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String >("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String >("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String >("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String >("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String >("dob"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);
    }

    @FXML
    private void addStudent(ActionEvent event)
    {
        String sql = "INSERT INTO `students`(`id`, `fname`, `lname`, `email`, `DOB`) VALUES (? , ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());

            stmt.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            this.errorLABEL.setText("                       Wprowadź poprawne dane!");
        }
    }

    @FXML
    private void clearFields(ActionEvent event){

            this.id.setText("");
            this.firstname.setText("");
            this.lastname.setText("");
            this.email.setText("");
            this.dob.setValue(null);


    }

    @FXML
    private void deleteStudent(ActionEvent event)  {



        if(studenttable.getSelectionModel().getSelectedItem() != null) {
            try {

                Connection conn = dbConnection.getConnection();

                StudentData selecterow = studenttable.getSelectionModel().getSelectedItem();
                String value = selecterow.getEmail();
                String cell = "email";
                String sqldelete = "DELETE FROM students WHERE email = '" + value + "' ;";
                System.out.println(sqldelete);

                Statement stmt = null;
                stmt = conn.createStatement();
                stmt.executeUpdate(sqldelete);

                conn.close();

                studenttable.getItems().removeAll(selecterow);
                System.out.println(cell);

            } catch (SQLException e) {
                System.out.println("Nie moge usunąć danych " + e.getMessage());
            }
        }else{
            System.out.println("Wybierz Studenta!");
        }


    }


}