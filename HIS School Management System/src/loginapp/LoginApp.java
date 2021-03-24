package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The login application, from the login page
 * @author Hoang Cao Duy
 *
 */
public class LoginApp
  extends Application
{
	/**
	 * 
	 * Initialize the application main page
	 */
	public void start(Stage stage)
    throws Exception
  {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("LoginMain.fxml"));
    
    Scene scene = new Scene(root);
    
    stage.setScene(scene);
    stage.setTitle("School Management System");
    stage.show();
  }
  /**
   * Launches the app
   */
  public static void main(String[] args)
  {
    launch(args);
  }
}