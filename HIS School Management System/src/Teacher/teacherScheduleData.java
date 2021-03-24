package Teacher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * data type of teacher's schedule, takes in lecture's name and subject
 * and return the schedule
 * @author Pham Viet Hoang
 */
public class teacherScheduleData {
	/**
	 * Teacher's First name
	 */
	  private final StringProperty firstName;
	  /**
		 * Teacher's Last name
		 */
	  private final StringProperty lastName;
	  /**
		 * Teacher's email
		 */
	  private final StringProperty email;
	  /**
		 * Teacher's subject
		 */
	  private final StringProperty subject;
	  /**
		 * Teacher's Teaching Date
		 */
	  private final StringProperty teachingDate;	
	  /**
		 * Teacher's class's start time
		 */
	  private final StringProperty start;
	  /**
		 * Teacher's class's end time
		 */
	  private final StringProperty end;	
	  /**
	   * Initialize the data of the teacher's schedule
	   * @param firstname - First Name
	   * @param lastname - Last Name
	   * @param email - Email
	   * @param subject - Subject
	   * @param teachingdate - Date
	   * @param start - Start time
	   * @param end - End time
	   */
	  public teacherScheduleData(String firstname, String lastname, String email, String subject, String teachingdate, String start, String end) {
		  this.firstName = new SimpleStringProperty(firstname);
		  this.lastName = new SimpleStringProperty(lastname);
		  this.email = new SimpleStringProperty(email);
		  this.subject = new SimpleStringProperty(subject);
		  this.teachingDate = new SimpleStringProperty(teachingdate);
		  this.start = new SimpleStringProperty(start);
		  this.end = new SimpleStringProperty(end);
	  }
	  /**
	   * Return teacher's first name
	   */
	  public String getFirstName()
	  {
	    return (String)this.firstName.get();
	  }
	  /**
	   * Return teacher's last name
	   */
	  public String getLastName()
	  {
	    return (String)this.lastName.get();
	  }
	  /**
	   * Return teacher's first name
	   */
	  public String getEmail()
	  {
	    return (String)this.email.get();
	  }
	  /**
	   * Return teacher's subject
	   */
	  public String getSubject()
	  {
	    return (String)this.subject.get();
	  }
	  /**
	   * Return teacher's classes date
	   */
	  public String getTeachingDate()
	  {
	    return (String)this.teachingDate.get();
	  }
	  /**
	   * Return teacher's class's start time
	   */
	  public String getStart()
	  {
	    return (String)this.start.get();
	  }
	  /**
	   * Return teacher's class's end time
	   */
	  public String getEnd()
	  {
	    return (String)this.end.get();
	  }
	  
}
