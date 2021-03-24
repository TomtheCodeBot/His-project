package students;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * datatype of student's schedule, takes in student's name
 * and return the schedule
 * @author Pham Viet Hoang
 */
public class studentScheduleData
{
  private final StringProperty stuFName;
  private final StringProperty teacherFName;
  private final StringProperty subject1;
  private final StringProperty teacherEmail;
  private final StringProperty teachingDate;
  private final StringProperty start;
  private final StringProperty end;
  /**
   * Initialize the student's schedule
   * @param stufname - Sstudent's First name
   * @param teacherfname - Teacher's First Name
   * @param teacheremail - Teacher's Email
   * @param subject1 - Teacher's Subject
   * @param teachingdate - Teaching Date
   * @param start - Class's Start time
   * @param end - Class's End time 
   */
public studentScheduleData(String stufname, String teacherfname, String teacheremail, String subject1, String teachingdate, String start, String end)
  {
    this.stuFName = new SimpleStringProperty(stufname);
    this.teacherFName = new SimpleStringProperty(teacherfname);
    this.subject1 = new SimpleStringProperty(subject1);
    this.teacherEmail = new SimpleStringProperty(teacheremail);
    this.teachingDate = new SimpleStringProperty(teachingdate);
    this.start = new SimpleStringProperty(start);
    this.end = new SimpleStringProperty(end);
  }
/**
 * Return student's first name
 */
  public String getStuFName()
  {
    return (String)this.stuFName.get();
  }
  /**
   * Return teacher's first name
   */
  public String getTeacherFName()
  {
    return (String)this.teacherFName.get();
  }
  /**
   * Return teacher's subject name
   */
  public String getSubject1()
  {
    return (String)this.subject1.get();
  }
  /**
   * Return teacher's email
   */
  public String getTeacherEmail()
  {
    return (String)this.teacherEmail.get();
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
