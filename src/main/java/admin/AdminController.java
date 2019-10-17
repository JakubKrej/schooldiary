package admin;

import dbUtil.dbConnection;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
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
    private TableColumn<StudentData, String> idCOLUMN;
    @FXML
    private TableColumn<StudentData, String> firstnameCOLUMN;
    @FXML
    private TableColumn<StudentData, String> lastnameCOLUMN;
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableView<StudentData> studentTABLE;
    @FXML
    private TableView<MarksData> marksTABLE;
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
    private Label nameLABEL;
    @FXML
    private Label lastnameLABEL;
    @FXML
    private Label emailLABEL;
    @FXML
    private Label dobLABEL;
    @FXML
    private ComboBox<Marks> selectmarkBOX;
    @FXML
    private Button addmarkBUTTON;
    @FXML
    private Button deleteBUTTON;
    @FXML
    private Button logoutBUTTON;
    @FXML
    private TableColumn<MarksData,String> MARKcolumn;



    private ObservableList<StudentData> data;
    private dbConnection dc;

    public AdminController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();

        addmarkBUTTON.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                System.out.println(selectmarkBOX.getValue().toString());

            }
        });


        studentTABLE.setOnMousePressed(new EventHandler<MouseEvent>() {


            private ObservableList<MarksData> data1;

            @Override
            public void handle(MouseEvent event) {

                nameLABEL.setText(studentTABLE.getSelectionModel().getSelectedItem().getFirstName());
                lastnameLABEL.setText(studentTABLE.getSelectionModel().getSelectedItem().getLastName());
                emailLABEL.setText(studentTABLE.getSelectionModel().getSelectedItem().getEmail());
                dobLABEL.setText(studentTABLE.getSelectionModel().getSelectedItem().getDob());


                if(studentTABLE.getSelectionModel().getSelectedItem()!=null){

                    try {
                        Connection conn = dbConnection.getConnection();
                        data1 = FXCollections.observableArrayList();

                        StudentData cellectstudenttable = studentTABLE.getSelectionModel().getSelectedItem();
                        String value =  cellectstudenttable.getID();

                        System.out.println(value);

                        String sqlmarks = "SELECT * FROM '" + value + "' ;";
                        System.out.println( sqlmarks);


                        ResultSet rs = conn.createStatement().executeQuery(sqlmarks);
                        while (rs.next()) {
                            this.data1.add(new MarksData(rs.getString(1)));
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    MARKcolumn.setCellValueFactory(new PropertyValueFactory<MarksData,String>("marks"));


                    marksTABLE.setItems(null);
                    marksTABLE.setItems(data1);


                }

            }
        });

        this.selectmarkBOX.setItems(FXCollections.observableArrayList(Marks.values()));
    }


    private ObservableList<MarksData> data2;

    @FXML
    private void addMark(ActionEvent event){

        StudentData cellectstudenttable = studentTABLE.getSelectionModel().getSelectedItem();
        String value =  cellectstudenttable.getID();

        //String mark = selectmarkBOX.getValue().toString();

        //String colname= " ";

        String sql = "INSERT INTO '"+ value +"' ('1') VALUES ( ? ) ;" ;
        //String newcol = "ALTER TABLE '" + value + "' ADD '" + colname + "' TEXT";

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt5 = conn.prepareStatement(sql);


            this.data2 = FXCollections.observableArrayList();

            if(this.selectmarkBOX.getValue().toString() != null) {
                stmt5.setString(1, this.selectmarkBOX.getSelectionModel().getSelectedItem().toString());

                stmt5.execute();

                String getingmarks = "SELECT * FROM '" + value + "' ;";

                System.out.println(getingmarks);

                ResultSet rs = conn.createStatement().executeQuery(getingmarks);
                while (rs.next()) {
                    this.data2.add(new MarksData(rs.getString(1)));
                }

            }else{
                System.out.println( "Pole jest puste!");
            }


            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

        MARKcolumn.setCellValueFactory(new PropertyValueFactory<MarksData,String>("mark"));


    }




    @FXML
    private void selectedStudent(ActionEvent event){
        if(studentTABLE.getSelectionModel().getSelectedItem()!=null) {

            try {

                Connection conn = dbConnection.getConnection();

                StudentData slcstudent = studentTABLE.getSelectionModel().getSelectedItem();
                String value = slcstudent.getID();

                String sqlselected = "SELECT id_users,fname,lname,email,DOB FROM students WHERE id_users = '" + value + "' ;";
                System.out.println(sqlselected);

                Statement stmt3 = null;
                stmt3 = conn.createStatement();
                stmt3.executeUpdate(sqlselected);


                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


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

        this.idCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.firstnameCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastnameCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String >("lastName"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

        this.studentTABLE.setItems(null);
        this.studentTABLE.setItems(this.data);
    }

    @FXML
    private void addStudent(ActionEvent event) {
        String sql = "INSERT INTO `students`( `fname`, `lname`, `email`, `DOB`) VALUES ( ?, ?, ?, ?)";
        String adduserstologintable = "INSERT INTO 'login' ('username', 'password', 'division') VALUES ( ?,?,?)";
        String newstudent = "SELECT id_users FROM students WHERE id_users = (SELECT MAX(id_users) FROM students)";
        String id = "SELECT id_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login)";

        this.errorLABEL.setText("");

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(adduserstologintable);
            ResultSet rs = conn.createStatement().executeQuery(newstudent);
            ResultSet rs1 = conn.createStatement().executeQuery(id);


            if (this.firstname.getText().equals("") || this.lastname.getText().equals("") || this.email.getText().equals("") || this.dob.getEditor().getText().equals("")) {
                this.errorLABEL.setText("                       Wprowadź poprawne dane!");
            } else {

                stmt.setString(1, this.firstname.getText());
                stmt.setString(2, this.lastname.getText());
                stmt.setString(3, this.email.getText());
                stmt.setString(4, this.dob.getEditor().getText());

                stmt2.setString(1, this.firstname.getText());
                stmt2.setString(2, this.lastname.getText());
                stmt2.setString(3, "Student");

                stmt.execute();
                stmt2.execute();


                String idlaststudent = rs.getString(1);
                int val = Integer.valueOf(idlaststudent) + 1;

                String idlastlogin = rs1.getString(1);
                int valst = Integer.valueOf(idlastlogin) + 1;

                String insertintoid = "UPDATE 'login' SET 'id_userslogin' = '" + val +"' WHERE id_login = '" + valst + "' ;";
                System.out.println(insertintoid);

                PreparedStatement stmt3 = conn.prepareStatement(insertintoid);



                String newtab = "CREATE TABLE '" + val + "' ( '1' TEXT );";
                PreparedStatement ps = conn.prepareStatement(newtab);
                System.out.println(newtab);


                ps.execute();
                stmt3.execute();





            }

            conn.close();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            this.errorLABEL.setText("                       Wprowadź poprawne dane!");
        }

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

        this.idCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.firstnameCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastnameCOLUMN.setCellValueFactory(new PropertyValueFactory<StudentData, String >("lastName"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

        this.studentTABLE.setItems(null);
        this.studentTABLE.setItems(this.data);
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
                String value = selecterow.getID();
                String cell = "id";
                String sqldelete = "DELETE FROM students WHERE id_users = '" + value + "' ;";
                System.out.println(sqldelete);

                Statement stmt = null;
                stmt = conn.createStatement();
                stmt.executeUpdate(sqldelete);

                studenttable.getItems().removeAll(selecterow);

                String tabledelete = "DROP TABLE '" + value + "' ;" ;
                Statement dltb = conn.createStatement();
                dltb.executeUpdate(tabledelete);

                conn.close();

            } catch (SQLException e) {
                System.out.println("Nie moge usunąć danych " + e.getMessage());
            }
        }else{
            System.out.println("Wybierz Studenta!");
        }


    }

    @FXML
    private void logOUT(ActionEvent event){

        try {
            Stage stage = (Stage) this.logoutBUTTON.getScene().getWindow();
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