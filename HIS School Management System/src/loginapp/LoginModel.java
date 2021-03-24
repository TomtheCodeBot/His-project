package loginapp;

import dbUtil.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The class for logging into the system
 * @author Hoang Cao Duy
 *
 */
public class LoginModel
{
  Connection connection;
  /**
   * The system attemps to connect to the database
   * @author Hoang Cao Duy
   */
  public LoginModel()
  {
    try
    {
      this.connection = dbConnection.getConnection();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (this.connection == null) {
      System.exit(1);
    }
  }
  /**
   * Check whether or not the system is connected
   * @return Status of the connection
   */
  public boolean isDatabaseConnected()
  {
    return this.connection != null;
  }
  /**
   * Checking the username, password and priority to check for validity
   * @param user - Username of the user
   * @param pass - Passwword of the user
   * @param opt - Rank of the user
   * @return true if the login information is correct, false otherwise
   * @throws SQLException
   * @author Hoang Cao Duy
   */
  public boolean isLogin(String user, String pass, String opt)
    throws SQLException
  {
    PreparedStatement pr = null;
    ResultSet rs = null;
    
    String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";
    try
    {
      pr = this.connection.prepareStatement(sql);
      pr.setString(1, user);
      pr.setString(2, pass);
      pr.setString(3, opt);
      
      rs = pr.executeQuery();
      if (rs.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      pr.close();
      rs.close();
    }
  }
}
