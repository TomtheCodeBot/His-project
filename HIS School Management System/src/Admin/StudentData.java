package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Student type that stores all student's data
 * @author Hoang Cao Duy
 *
 */
public class StudentData
{
  /**
   * Student's ID
   */
  private final StringProperty ID;
  /**
   * Student's First Name
   */
  private final StringProperty firstName;
  /**
   * Student's Last Name
   */
  private final StringProperty lastName;
  /**
   * Student's eMail
   */
  private final StringProperty email;
  /**
   * Student's Date Of Birth
   */
  private final StringProperty DOB;
  /**
   * Calculus score
   */
  private final StringProperty Calculus;
  /**
   * Algebra score
   */
  private final StringProperty Algebra;
  /**
   * Database Score
   */
  private final StringProperty Database;
  
public StringProperty calculusProperty() {
	return Calculus;
}

public StringProperty algebraProperty() {
	return Algebra;
}
public String getCalculus() {
	return (String)Calculus.get();
}
public StringProperty databaseProperty() {
	return Database;
}
public String getDatabase() {
	return (String)Database.get();
}
public String getAlgebra() {
	return (String)Algebra.get();
}
/**
 * initialize student's data
 * @param id - Student's ID
 * @param firstname - Student's First name
 * @param lastname - Student's Last name
 * @param email - Student's Email
 * @param dob - Date of birth of student
 * @param algebra - Scores in algebra
 * @param calculus - Scores in calculus
 * @param database - Scores in database
 */
public StudentData(String id, String firstname, String lastname, String email, String dob,String algebra, String calculus, String database)
  {
    this.ID = new SimpleStringProperty(id);
    this.firstName = new SimpleStringProperty(firstname);
    this.lastName = new SimpleStringProperty(lastname);
    this.email = new SimpleStringProperty(email);
    this.DOB = new SimpleStringProperty(dob);
    this.Algebra = new SimpleStringProperty(algebra);
    this.Calculus = new SimpleStringProperty(calculus);
    this.Database = new SimpleStringProperty(database);
  }
  
  public String getID()
  {
    return (String)this.ID.get();
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
  
  public String getDOB()
  {
    return (String)this.DOB.get();
  }
  
  public void setID(String value)
  {
    this.ID.set(value);
  }
  
  public void setFirstName(String value)
  {
    this.firstName.set(value);
  }
  
  public void setLastName(String value)
  {
    this.lastName.set(value);
  }
  
  public void setEmail(String value)
  {
    this.email.set(value);
  }
  
  public void setDOB(String value)
  {
    this.DOB.set(value);
  }
  
  public StringProperty idProperty()
  {
    return this.ID;
  }
  
  public StringProperty firstNameProperty()
  {
    return this.firstName;
  }
  
  public StringProperty lastNameProperty()
  {
    return this.lastName;
  }
  
  public StringProperty emailProperty()
  {
    return this.email;
  }
  
  public StringProperty dobProperty()
  {
    return this.DOB;
  }
}
