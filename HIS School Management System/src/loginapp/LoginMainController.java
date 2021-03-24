package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsFXMLController;
import Teacher.TeacherController;
/**
 * Controller of the login page, connects to all other controllers
 * ,and change to the corresponded FXML if requirements are fulfilled
 * @author Hoang Cao Duy
 */
public class LoginMainController
  implements Initializable
{
  LoginModel loginModel = new LoginModel();
  @FXML
  private Label dbstatus;
  @FXML
  private Button loginButton;
  @FXML
  private TextField username;
  @FXML
  private PasswordField password;
  @FXML
  private ComboBox<option> combobox;
  /**
   * Initialize the database
   */
  public void initialize(URL url, ResourceBundle rb)
  {
    if (this.loginModel.isDatabaseConnected()) {
      this.dbstatus.setText("Connected");
    } else {
      this.dbstatus.setText("Not Connected");
    }
    this.combobox.setItems(FXCollections.observableArrayList(option.values()));
  }
  /**
   * After pushing the Login button, call function in Login Model to 
   * check for the correct credentials
   */
  @FXML
  public void Login(ActionEvent event)
  {
    try
    {
      if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString()))
      {
        Stage stage = (Stage)this.loginButton.getScene().getWindow();
        stage.close();
        switch (((option)this.combobox.getValue()).toString())
        {
        case "Admin": 
          adminLogin();
          break;
        case "Teacher": 
        	teacherLogin();
            break;
        case "Student": 
          studentLogin();
        }
      }
      else
      {
        this.dbstatus.setText("Wrong Creditials");
      }
    }
    catch (Exception localException) {}
  }
  /**
   * Load student's page and pass control over to student's controller
   * @author Hoang Cao Duy
   */
  public void studentLogin()
  {
    try
    {
      Stage userStage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      Pane root = (Pane)loader.load(getClass().getResource("/students/StudentsFXML.fxml").openStream());
      StudentsFXMLController studentController = (StudentsFXMLController)loader.getController();
      studentController.getUsername(this.username.getText(),this.password.getText());
      Scene scene = new Scene(root);
      userStage.setScene(scene);
      userStage.setTitle("Student Dashboard");
      userStage.setResizable(false);
      userStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  /**
   * Load admin's page and pass control over to admin's controller
   * @author Hoang Cao Duy
   */
  public void adminLogin()
  {
    try
    {
      Stage adminStage = new Stage();
      FXMLLoader adminLoader = new FXMLLoader();
      Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
      
      
      Scene adminscene = new Scene(adminroot);
      
      adminStage.setScene(adminscene);
      adminStage.setTitle("Admin Dashboard");
      adminStage.setResizable(true);
      adminStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  /**
   * Load teacher's page and pass control over to teacher's controller
   * @author Hoang Cao Duy
   */
  public void teacherLogin()
  {
	  {
		    try
		    {
		      Stage adminStage = new Stage();
		      FXMLLoader adminLoader = new FXMLLoader();
		      Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Teacher/Teacher.fxml").openStream());
		      TeacherController admintController = (TeacherController)adminLoader.getController();
		      admintController.fillInformation(this.username.getText());
		      admintController.loadStudentData();
		      Scene adminscene = new Scene(adminroot);
		      adminStage.setScene(adminscene);
		      adminStage.setTitle("Teacher Dashboard");
		      adminStage.setResizable(true);
		      adminStage.show();
		    }
		    catch (IOException e)
		    {
		      e.printStackTrace();
		    }
		  }
  }
}
