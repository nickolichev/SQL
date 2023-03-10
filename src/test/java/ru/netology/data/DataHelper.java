package ru.netology.data;

import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
  private static final String jdbc = "jdbc:mysql://localhost:3306/app";
  private static final String db_user = "app";
  private static final String db_password = "pass";

  private DataHelper() {
  }

  @Value
  public static class AuthInfo {
    public static String login = "vasya";
    public static String password = "qwerty123";
    public static String invalidLogin = "elena";
    public static String invalidPassword = "poiuy456";
  }

  @Value
  public static class VerificationCode {
    public static String code = getVerificationCode();
    public static String invalidCode ="12345";
  }

  private static String getVerificationCode() {
    var authCodeSQL = "SELECT  code FROM auth_codes order by created desc limit 1;";
    try (
            var conn = DriverManager.getConnection(jdbc, db_user, db_password);
            var countStmt = conn.createStatement();
    ) {
      try (var rs = countStmt.executeQuery(authCodeSQL)) {
        if (rs.next()) {
          // выборка значения по индексу столбца (нумерация с 1)
          var code = rs.getString("code");
          return code;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
