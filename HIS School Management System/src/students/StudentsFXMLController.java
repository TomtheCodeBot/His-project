package students;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Teacher.TeacherData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
/**
 * The controller for students
 * <p>
 * This class purpose's is to output student's information 
 * based on their id 
 * </p>
 * @author Hoang Cao Duy
 */
public class StudentsFXMLController
  implements Initializable
{
  @FXML
  private AnchorPane StudentPanel;
  @FXML
  private TextField idbox;
  @FXML
  private TextField firstnamebox;
  @FXML
  private TextField lastnamebox;
  @FXML
  private TextField dobbox;
  @FXML
  private TextField emailbox;
  @FXML
  private TextField idbox1;
  @FXML
  private TextField firstnamebox1;
  @FXML
  private TextField lastnamebox1;
  @FXML
  private TextField dobbox1;
  @FXML
  private TextField emailbox1;
  @FXML
  private TextField calculusbox;
  @FXML
  private TextField algebrabox;
  @FXML
  private TextField dbbox;
  @FXML
  private Button loadbutton;
  @FXML
  private TableView<TeacherData> teacherTable;
  @FXML
  private TableColumn<TeacherData, String> fname;
  @FXML
  private TableColumn<TeacherData, String> lname;
  @FXML 
  private TableColumn<TeacherData, String> subject;
  @FXML
  private TableColumn<TeacherData, String> emailcolumn;
  
  // Student schedule
  @FXML
  private TableColumn<studentScheduleData, String> stufname;
  @FXML
  private TableColumn<studentScheduleData, String> teacherfname;
  @FXML
  private TableColumn<studentScheduleData, String> teacheremail;
  @FXML
  private TableColumn<studentScheduleData, String> subject1;
  @FXML
  private TableColumn<studentScheduleData, String> teachingdate;
  @FXML
  private TableColumn<studentScheduleData, String> start;
  @FXML
  private TableColumn<studentScheduleData, String> end;
  @FXML
  private TableView<studentScheduleData> stuscheduletable;
  
  private ObservableList<TeacherData> data;
  private ObservableList<studentScheduleData> data1;
  private String inputlogin;
  public void gettext(String id) {
	  inputlogin = id;
  }
  
  /**
   * This method helps initialize a connection to the database 
   * @param url 
   * the URL requires to connect to the database
   * @param rb 
   * the resource bundle requires to connect to the database
  */
  public void initialize(URL url, ResourceBundle rb) {
	  new dbConnection();
  }
  /** 
   * The method that get the user name from the database 
   * and other info. 
   * By establishing a connection to the database 
   * and executes a search query.
   * @param student_id - the student identification's number
   * @param name - student's name
   * @author Deo Dang Quang
   */
  public void getUsername(String student_id,String name)  {
	  {
		    try
		    {
		    	Connection conn = dbConnection.getConnection();
		    	inputlogin=name;
		    	ResultSet rs = null;
		    	rs = conn.createStatement().executeQuery("SELECT * FROM students where id=\""+student_id+"\"");
		    	
		    	this.idbox.setText(rs.getString(1));
			    this.firstnamebox.setText(rs.getString(2));
			    this.lastnamebox.setText(rs.getString(3));
			    this.dobbox.setText(rs.getString(4));
			    this.emailbox.setText(rs.getString(5));
			    this.algebrabox.setText(rs.getString(6));
			    this.calculusbox.setText(rs.getString(7));
			    this.dbbox.setText(rs.getString(8));
			    this.idbox1.setText(rs.getString(1));
			    this.firstnamebox1.setText(rs.getString(2));
			    this.lastnamebox1.setText(rs.getString(3));
			    this.dobbox1.setText(rs.getString(4));
			    this.emailbox1.setText(rs.getString(5));
			    
			    conn.close();
			    rs.close();
		    }
		    catch (SQLException e)
		    {
		      System.err.println("Error " + e);
		    }
		    
		  }
  }
  /** 
   * The method that loads classes that students are attending
   * @author Lam Xuan Bach
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
public void loadSchedule()
  {
    try
    {
      Connection conn = dbConnection.getConnection();
      this.data = FXCollections.observableArrayList();
      //call all the classes that the student attend
      ResultSet rs = conn.createStatement().executeQuery("SELECT t.FirstName, t.LastName, t.Email, t.Subject FROM teacher t, (SELECT teacherFName from Attend where stuFName=\""+inputlogin+"\" ) as sam where t.FirstName=sam.teacherFName");
      while (rs.next()) {
        this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
      }
      
      if (rs.next() == false) {
    	  System.out.printf("Did not attend any class\n");
      }
      conn.close();
      rs.close();
    }
    catch (SQLException e)
    {
      System.err.println("Error " + e);
    }
    this.fname.setCellValueFactory(new PropertyValueFactory("firstName"));
    this.lname.setCellValueFactory(new PropertyValueFactory("lastName"));
    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
    this.subject.setCellValueFactory(new PropertyValueFactory("subject"));
    this.teacherTable.setItems(null);
    this.teacherTable.setItems(this.data);
  }
  
  
  /**
   * The methods takes the id of students and looks into the database
   * and gets the class the student is attending
   * @author Pham Viet Hoang
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
public void loadStudentSchedule()
  {
    try
    {
      Connection conn = dbConnection.getConnection();
      this.data1 = FXCollections.observableArrayList();
      
      // join attend and teacher schedule, then select the data needed for the database
      ResultSet rs = conn.createStatement().executeQuery("SELECT stuFname,FirstName,Email,s.Subject,teaching_date,start,end FROM Attend,teacher_schedule1 as s where stuFName = \""+inputlogin+"\"and FirstName=teacherFName ");
      if (rs.next() == false) {
    	  System.out.printf("Did not attend any class\n");
      }
      
      while (rs.next()) {
        this.data1.add(new studentScheduleData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));
      }
      conn.close();
      rs.close();
    }
    catch (SQLException e)
    {
      System.err.println("Error " + e);
    }
    this.stufname.setCellValueFactory(new PropertyValueFactory("stuFName"));
    this.teacherfname.setCellValueFactory(new PropertyValueFactory("teacherFName"));
    this.teacheremail.setCellValueFactory(new PropertyValueFactory("teacherEmail"));
    this.subject1.setCellValueFactory(new PropertyValueFactory("subject1"));
    this.teachingdate.setCellValueFactory(new PropertyValueFactory("teachingDate"));
    this.start.setCellValueFactory(new PropertyValueFactory("start"));
    this.end.setCellValueFactory(new PropertyValueFactory("end"));
    this.stuscheduletable.setItems(null);
    this.stuscheduletable.setItems(this.data1); // Upload student's data on fxml window 
    
  }
  
}