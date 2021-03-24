package loginapp;
/**
 * Options to login as a student, teacher or and admin
 * @author Hoang Cao Duy
 *
 */
public enum option
{
  Admin,  Student, Teacher ;
  
  private option() {}
  /**
   * Calling Enum's valuue
   * @return value of enum
   */
  public String value()
  {
    return name();
  }
  /**
   * Return Enum Value for string
   * @param v - String
   * @return Enum
   */
  public static option fromvalue(String v)
  {
    return valueOf(v);
  }
}
