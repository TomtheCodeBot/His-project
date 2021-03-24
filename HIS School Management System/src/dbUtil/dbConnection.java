package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Information for establishing connection between the application and the database
 * @author Hoang Cao Duy
 *
 */
public class dbConnection
{
  public static Connection getConnection()
    throws SQLException
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
      return DriverManager.getConnection("jdbc:sqlite:schoolsystem.sqlite");
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
}
