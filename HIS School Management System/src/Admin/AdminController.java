package Admin;

import dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * Controller for the admin page
 * @author Hoang Cao Duy
 */
public class AdminController
  implements Initializable
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
  private TextField deleteID;
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
  private Button loadbutton;
  @FXML
  private TableColumn<StudentData, String> algebracolumn;
  @FXML
  private TableColumn<StudentData, String> calculuscolumn;
  @FXML
  private TableColumn<StudentData, String> databasecolumn;

  private ObservableList<StudentData> data;
  public void initialize(URL url, ResourceBundle rb)
  {
    new dbConnection();
  }
  /**
   * Load student's data
   * @author Hoang Cao Duy
   * @param event
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
@FXML
  private void loadStudentData(ActionEvent event)
  {
    try
    {
    	//Connect to database
      Connection conn = dbConnection.getConnection();
      this.data = FXCollections.observableArrayList();
      // Preparing and querrying student's data
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
      while (rs.next()) {
        this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
      }
    }
    catch (SQLException e)
    {
      System.err.println("Error " + e);
    }
    //output the data to the collumn
    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("DOB"));
    this.algebracolumn.setCellValueFactory(new PropertyValueFactory("Algebra"));
    this.calculuscolumn.setCellValueFactory(new PropertyValueFactory("Calculus"));
    this.databasecolumn.setCellValueFactory(new PropertyValueFactory("Database"));
    this.studenttable.setItems(null);
    this.studenttable.setItems(this.data);
  }
  /**
   * Adding another student into the database and the login database.
   * @author Hoang Cao Duy
   */
  @FXML
  private void addStudent(ActionEvent event)
  {
	  // preparing querry to add data into the database
    String sql = "INSERT INTO `students`(`id`, `fname`, `lname`, `email`, `DOB`,`Algebra`,`Calculus`,`Database`) VALUES (? , ?, ?, ?, ?,\"0\",\"0\",\"0\")";
    String sql2 = "INSERT INTO `login`(`username`, `password`,`division`) VALUES (? , ?,\"Student\")";
    try
    {
    	//adding student's data to the student database and login 
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      PreparedStatement stmt2 = conn.prepareStatement(sql2);
      stmt.setString(1, this.id.getText());
      stmt.setString(2, this.firstname.getText());
      stmt.setString(3, this.lastname.getText());
      stmt.setString(4, this.email.getText());
      stmt.setString(5, this.dob.getEditor().getText());
      stmt2.setString(1, this.id.getText());
      stmt2.setString(2, this.firstname.getText());
      stmt.execute();
      stmt2.execute();
      conn.close();
    }
    catch (SQLException e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
  /**
   * Clear the fields that used to fill in data
   * @author Nguyen Thanh Huy
   */
  @FXML
  private void clearFields(ActionEvent event)
  {
    this.id.setText("");
    this.firstname.setText("");
    this.lastname.setText("");
    this.email.setText("");
    this.deleteID.setText("");
    this.dob.setValue(null);
  }
  /**
   * Delete the student using their ID
   * @author Pham Viet Hoang
   */
  @FXML
  private void deleteID(ActionEvent event) {
  String sql = "DELETE FROM students WHERE id = ?";
  String sql2 = "DELETE FROM login WHERE username = ?";
  try
  {
    Connection conn = dbConnection.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql);
    PreparedStatement stmt2 = conn.prepareStatement(sql2);
    stmt.setString(1, this.deleteID.getText());
    stmt2.setString(1, this.deleteID.getText());
    stmt2.execute();
    stmt.execute();
    conn.close();
  }
  catch (SQLException e)
  {
    System.err.println("Got an exception!");
    System.err.println(e.getMessage());
  } 
}
 
}
