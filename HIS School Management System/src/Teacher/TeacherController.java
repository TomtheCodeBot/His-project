package Teacher;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import java.sql.PreparedStatement;
/**
 * Teacher's application when signed in and their tools
 * @author Hoang Cao Duy
 *
 */
public class TeacherController implements Initializable{
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
	private TableColumn<StudentData, String> scorecolumn;
	@FXML
	private TextField id;
	@FXML
	private TextField changescore;
	@FXML
	private TextField SearchID;
	@FXML
	private TextField teacherFirstName;
	@FXML
	private TextField teacherLastName;
	@FXML
	private TextField teacherEmail;
	@FXML
	private TextField teacherSubject;
	@FXML
	private BarChart<String, Number> histogramChart;
	@FXML
	private TableView<StudentData> studenttable1;
	@FXML
	private TextField teacherFirstName1;
	@FXML
	private TextField teacherLastName1;
	@FXML
	private TextField teacherEmail1;
	@FXML
	private TextField teacherSubject1;
	@FXML
	private TableColumn<StudentData, String> idcolumn1;
	@FXML
	private TableColumn<StudentData, String> firstnamecolumn1;
	@FXML
	private TableColumn<StudentData, String> lastnamecolumn1;
	@FXML
	private TableColumn<StudentData, String> emailcolumn1;
	@FXML
	private TableColumn<StudentData, String> dobcolumn1;
	
	// Schedule tab
    @FXML
    private TableView<teacherScheduleData> scheduletable;
    @FXML
    private TableColumn<teacherScheduleData, String> fname;
    @FXML
    private TableColumn<teacherScheduleData, String> lname;
    @FXML
    private TableColumn<teacherScheduleData, String> subject;
    @FXML
    private TableColumn<teacherScheduleData, String> teacheremail;
    @FXML
    private TableColumn<teacherScheduleData, String> teachingdate;
    @FXML
    private TableColumn<teacherScheduleData, String> start;
    @FXML
    private TableColumn<teacherScheduleData, String> end;
	@FXML
	private DatePicker schedule;
	@FXML
	private TextField Start_time;
	@FXML
	private TextField End_time;
	private ObservableList<StudentData> data;
	private ObservableList<teacherScheduleData> data1;
	private String userSubject;
	private String subjectName;
	private String email;
	private String Firstname;
	private String Lastname;
	
	/**
	 * Filled information of teacher from database
	 * on top left corner of window after successfully login
	 * @author Nguyen Thanh Huy - 1321131
	 * @param username	username of teacher
	 */
	public void fillInformation(String username) {
		schedule.setConverter(new StringConverter<LocalDate>() {
			 String pattern = "yyyy-MM-dd";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			 {
			     schedule.setPromptText(pattern.toLowerCase());
			 }

			 @Override public String toString(LocalDate date) {
			     if (date != null) {
			         return dateFormatter.format(date);
			     } else {
			         return "";
			     }
			 }

			 @Override public LocalDate fromString(String string) {
			     if (string != null && !string.isEmpty()) {
			         return LocalDate.parse(string, dateFormatter);
			     } else {
			         return null;
			     }
			 }
			});
		 
			try
			  {
				subjectName=username;
			    Connection conn = dbConnection.getConnection();
			    ResultSet rs = null;
			    rs = conn.createStatement().executeQuery("SELECT * FROM teacher where FirstName = \""+username+"\"");
				this.teacherFirstName.setText(rs.getString(1));
				Firstname=rs.getString(1);
				this.teacherLastName.setText(rs.getString(2));
				Lastname=rs.getString(2);
				this.teacherEmail.setText(rs.getString(3));
				email=rs.getString(3);
				this.teacherSubject.setText(rs.getString(4));
				this.teacherFirstName1.setText(rs.getString(1));
				this.teacherLastName1.setText(rs.getString(2));
				this.teacherEmail1.setText(rs.getString(3));
				this.teacherSubject1.setText(rs.getString(4));
				userSubject=rs.getString(4);  // Algebra if Jimmy
				conn.close();
			      rs.close();
			  }
			  catch (SQLException e)
			  {
			    System.err.println("Error " + e);
			  }
		}
	/**
	 * Load all students data and their scores in teacher's subject
	 * @author Nguyen Thanh Huy - 1321131
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadStudentData() // (DONE) Successfully load data
		  {
		    try
		    {
		      Connection conn = dbConnection.getConnection();
		      this.data = FXCollections.observableArrayList();
		      
		      // Load all info of students to "this.data" from db
		      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
		      while (rs.next()) {
		        this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
		      }
		      conn.close();
		      rs.close();
		    }
		    catch (SQLException e)
		    {
		      System.err.println("Error " + e);
		    }
		    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
		    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
		    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
		    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
		    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("DOB"));
		    this.scorecolumn.setCellValueFactory(new PropertyValueFactory(userSubject));
		    this.studenttable.setItems(null);
		    this.studenttable.setItems(this.data); // Upload student's data on fxml window 
		    
		  }
	/**
	 * Load all students that attending the teacher's lecture
	 * @author Lam Xuan Bach
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadStudentAttend() // (DONE) Successfully load data
	  {
	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data = FXCollections.observableArrayList();
	      
	      // Load all info of students to "this.data" from db  (FIXED)
	      ResultSet rs = conn.createStatement().executeQuery("SELECT id, fname, lname, email, DOB, Algebra, Calculus, Database FROM students ,(SELECT stuFName from Attend where subject=\""+userSubject+"\" )where fname=stuFName");
	      while (rs.next()) {
	        this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
	      }
	      conn.close();
	      rs.close();
	    }
	    catch (SQLException e)
	    {
	      System.err.println("Error " + e);
	    }
	    this.idcolumn1.setCellValueFactory(new PropertyValueFactory("ID"));
	    this.firstnamecolumn1.setCellValueFactory(new PropertyValueFactory("firstName"));
	    this.lastnamecolumn1.setCellValueFactory(new PropertyValueFactory("lastName"));
	    this.emailcolumn1.setCellValueFactory(new PropertyValueFactory("email"));
	    this.dobcolumn1.setCellValueFactory(new PropertyValueFactory("DOB"));
	    this.studenttable1.setItems(null);
	    this.studenttable1.setItems(this.data); // Upload student's data on fxml window 
	    
	  }
	
	/**
	 * Load all of the teacher's classes
	 * @author Pham Viet Hoang
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadTeacherSchedule()
	  {
	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data1 = FXCollections.observableArrayList();
	      
	      // Load all info of students to "this.data1" from db  (FIXED)
	      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teacher_schedule1 where FirstName = \""+subjectName+"\"");
	      while (rs.next()) {
	        this.data1.add(new teacherScheduleData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));
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
	    this.teacheremail.setCellValueFactory(new PropertyValueFactory("email"));
	    this.subject.setCellValueFactory(new PropertyValueFactory("subject"));
	    this.teachingdate.setCellValueFactory(new PropertyValueFactory("teachingDate"));
	    this.start.setCellValueFactory(new PropertyValueFactory("start"));
	    this.end.setCellValueFactory(new PropertyValueFactory("end"));
	    this.scheduletable.setItems(null);
	    this.scheduletable.setItems(this.data1); // Upload student's data on fxml window 
	    
	  }
	
	/**
	 * refresh student's score list
	 * @author Hoang Cao Duy
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void refreshList(ActionEvent event)  { // "event": push "refresh table" button
		 {
			    try
			    {
			      Connection conn = dbConnection.getConnection();
			      this.data = FXCollections.observableArrayList();
			      
			      // "rs": result after execute SQL query
			      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students where "+userSubject+" <> \"0\"");
			      while (rs.next()) {
			        this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			      }
			      conn.close();
		          rs.close();
			    }
			    catch (SQLException e)
			    {
			      System.err.println("Error " + e);
			    }
			    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
			    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
			    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
			    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
			    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("DOB"));
			    this.scorecolumn.setCellValueFactory(new PropertyValueFactory(userSubject));
			    this.studenttable.setItems(null);
			    this.studenttable.setItems(this.data);
			  }
			    
	  }
	/**
	 * rewrite student's score using student's id
	 * @author Hoang Cao Duy
	 */
	@FXML
	public void changeScore(ActionEvent event)  { // Push "Update Scores" button (NOT DONE)
		// "this.changescore": Action of typing new score in "Change Student's score" field
		String sql = "UPDATE students " + 
					 "SET "+ userSubject +" =  \""+ this.changescore.getText() + 
					 "\" WHERE id = \""+this.id.getText()+"\"";
		  try
		  {
		    Connection conn = dbConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.execute();
		    conn.close();
		  }
		  catch (SQLException e)
		  {
		    System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		  }
	}
	/**
	 * refresh text fields
	 * @author Nguyen Thanh Huy
	 */
	@FXML
	private void clearFields(ActionEvent event) // (NOT DONE)
	  {
		this.id.setText("");
	    this.SearchID.setText("");
	    this.changescore.setText("");
	  }
	/**
	 * Search student using their ID
	 * @author Hoang Cao Duy
	 * @param event
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	private void searchStudents(ActionEvent event)
	{
	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data = FXCollections.observableArrayList();
	      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students WHERE id=\""+this.SearchID.getText()+"\"");
	      while (rs.next()) {
	        this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
	      }
	      conn.close();
          rs.close();
	    }
	    catch (SQLException e)
	    {
	      System.err.println("Error " + e);
	    }
	    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
	    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
	    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
	    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
	    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("DOB"));
	    this.scorecolumn.setCellValueFactory(new PropertyValueFactory(userSubject));
	    this.studenttable.setItems(null);
	    this.studenttable.setItems(this.data);
	  }
	    
	
	public void initialize(URL url, ResourceBundle rb)
	  {
	    new dbConnection();
	  }
	/**
	 * draw histogram table of student's score
	 * @author Hoang Cao Duy
	 */
	 @SuppressWarnings("unchecked")
	@FXML
	  private void showchart(ActionEvent event) throws InterruptedException {

		  histogramChart.getData().clear();
		  TimeUnit.MILLISECONDS.sleep(660);
		  ArrayList<Integer> list= new ArrayList<Integer>();
		  for(int i=0;i<10;i++) {
			  list.add(0);
		  }
		  try {
		  Connection conn = dbConnection.getConnection();
		  ResultSet rs = conn.createStatement().executeQuery("SELECT "+userSubject+" FROM students");
	      while (rs.next()) {
	    	  list.set((rs.getInt(1)-1)/10,  list.get((rs.getInt(1)-1)/10)+1);
	      }
	      conn.close();
          rs.close();
		  }
		  catch (SQLException e)
		  {
		    System.err.println("Error " + e);
		  }
		  XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
		  series1.setName("0-10"); 
		  series1.getData().add(new XYChart.Data<>(userSubject,list.get(0)));
		  XYChart.Series<String, Number> series2 = new XYChart.Series<>(); 
		  series2.setName("11-20");
		  series2.getData().add(new XYChart.Data<>(userSubject,list.get(1)));
		  XYChart.Series<String, Number> series3 = new XYChart.Series<>(); 
		  series3.setName("21-30");
		  series3.getData().add(new XYChart.Data<>(userSubject,list.get(2)));
		  XYChart.Series<String, Number> series4 = new XYChart.Series<>(); 
		  series4.setName("31-40");
		  series4.getData().add(new XYChart.Data<>(userSubject,list.get(3)));
		  XYChart.Series<String, Number> series5 = new XYChart.Series<>(); 
		  series5.setName("41-50");
		  series5.getData().add(new XYChart.Data<>(userSubject,list.get(4)));
		  XYChart.Series<String, Number> series6 = new XYChart.Series<>(); 
		  series6.setName("51-60");
		  series6.getData().add(new XYChart.Data<>(userSubject,list.get(5)));
		  XYChart.Series<String, Number> series7 = new XYChart.Series<>(); 
		  series7.setName("61-70");
		  series7.getData().add(new XYChart.Data<>(userSubject,list.get(6)));
		  XYChart.Series<String, Number> series8 = new XYChart.Series<>(); 
		  series8.setName("71-80");
		  series8.getData().add(new XYChart.Data<>(userSubject,list.get(7)));
		  XYChart.Series<String, Number> series9 = new XYChart.Series<>(); 
		  series9.setName("81-90");
		  series9.getData().add(new XYChart.Data<>(userSubject,list.get(8)));
		  XYChart.Series<String, Number> series10 = new XYChart.Series<>(); 
		  series10.setName("91-100");
		  series10.getData().add(new XYChart.Data<>(userSubject,list.get(9)));
		  histogramChart.setTitle("Score plot of subject: "+userSubject);
		  histogramChart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8,series9,series10);
	  }
	@FXML
	public void addSchedule() {
		{
		    try
		    {
		      Connection conn = dbConnection.getConnection();
		      this.data = FXCollections.observableArrayList();
		      
		      // Load all info of students to "this.data" from db
		      conn.createStatement().executeQuery("INSERT INTO teacher_schedule1 VALUES(\""+Firstname+"\",\""+Lastname+"\",\""+
		      email+"\",\""+userSubject+"\",\""+schedule.getEditor().getText()+"\",\""+Start_time.getText()+"\",\""+End_time.getText()+"\")");
		      conn.close();
		    }
		    catch (SQLException e)
		    {
		      System.err.println("Error " + e);
		    }
	}
}}

