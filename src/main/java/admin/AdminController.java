package admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


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
    private TableColumn<MarksData, String> IDcolumnmarks;
    @FXML
    private TableColumn<MarksData, String> mark1column;
    @FXML
    private TableColumn<MarksData,String> mark2column;


    private ObservableList<StudentData> data;
    private dbConnection dc;

    public AdminController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();


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

                        String sqlmarks = "SELECT * FROM stmarks WHERE id = '" + value + "' ;";
                        System.out.println( sqlmarks);


                        ResultSet rs = conn.createStatement().executeQuery(sqlmarks);
                        while (rs.next()) {
                            this.data1.add(new MarksData(rs.getString(1),rs.getString(2),rs.getString(3)));
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    IDcolumnmarks.setCellValueFactory(new PropertyValueFactory<MarksData,String>("ID"));
                    mark1column.setCellValueFactory(new PropertyValueFactory<MarksData,String>("mark1"));
                    mark2column.setCellValueFactory(new PropertyValueFactory<MarksData,String>("mark2"));



                    marksTABLE.setItems(null);
                    marksTABLE.setItems(data1);

                }

            }
        });

        this.selectmarkBOX.setItems(FXCollections.observableArrayList(Marks.values()));
    }

    @FXML
    public void selectMark(){




    }

    @FXML
    private void addMark(){

        StudentData cellectstudenttable = studentTABLE.getSelectionModel().getSelectedItem();
        String value =  cellectstudenttable.getID();


        String sql = "INSERT INTO 'stmarks' ('id', 'mark1', 'mark2')  ";

        try {
            Connection conn = dbConnection.getConnection();


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void selectedStudent(ActionEvent event){
        if(studentTABLE.getSelectionModel().getSelectedItem()!=null){

            try{

                Connection conn = dbConnection.getConnection();

                StudentData slcstudent = studentTABLE.getSelectionModel().getSelectedItem();
                String value = slcstudent.getID();

                String sqlselected = "SELECT id,fname,lname,email,DOB FROM students WHERE id = '" + value + "' ;";
                System.out.println(sqlselected);

                Statement stmt3 = null;
                stmt3 = conn.createStatement();
                stmt3.executeUpdate(sqlselected);




                conn.close();

            }catch (SQLException e) {
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
    private void addStudent(ActionEvent event)
    {
        String sql = "INSERT INTO `students`( `fname`, `lname`, `email`, `DOB`) VALUES ( ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, this.firstname.getText());
            stmt.setString(2, this.lastname.getText());
            stmt.setString(3, this.email.getText());
            stmt.setString(4, this.dob.getEditor().getText());

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

                studenttable.getItems().removeAll(selecterow);
                System.out.println(cell);

                conn.close();

            } catch (SQLException e) {
                System.out.println("Nie moge usunąć danych " + e.getMessage());
            }
        }else{
            System.out.println("Wybierz Studenta!");
        }


    }


}