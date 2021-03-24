package Teacher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleDoubleProperty;

/**
 * datatype of teacher, takes in first name,last name email and subject to return a class
 * that is easier to work with
 * @author Hoang Cao Duy
 */
public class TeacherData {
	  private final StringProperty firstName;
	  private final StringProperty lastName;
	  private final StringProperty email;
	  private final StringProperty subject;	

	  public TeacherData(String firstname, String lastname, String email, String subject) {
		  this.firstName = new SimpleStringProperty(firstname);
		  this.lastName = new SimpleStringProperty(lastname);
		  this.email = new SimpleStringProperty(email);
		  this.subject = new SimpleStringProperty(subject);
	  }
	  public String getFirstName()
	  {
	    return (String)this.firstName.get();
	  }
	  
	  public String getLastName()
	  {
	    return (String)this.lastName.get();
	  }
	  
	  public String getEmail()
	  {
	    return (String)this.email.get();
	  }
	  public String getSubject()
	  {
	    return (String)this.subject.get();
	  }
}
