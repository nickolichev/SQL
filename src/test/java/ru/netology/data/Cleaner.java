package ru.netology.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Cleaner {

    public static void cleanupDataBase() {
      String url = "jdbc:mysql://localhost:3306/app";
      String user = "app";
      String password = "pass";

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "DELETE FROM cards";
        try (Statement stmt = conn.createStatement()) {
          stmt.executeUpdate(sql);
          System.out.println("Data cleanup complete");
        }
      } catch (SQLException e) {
        System.err.println("Error during data cleanup: " + e.getMessage());
      }

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "DELETE FROM auth_codes";
        try (Statement stmt = conn.createStatement()) {
          stmt.executeUpdate(sql);
          System.out.println("Data cleanup complete");
        }
      } catch (SQLException e) {
        System.err.println("Error during data cleanup: " + e.getMessage());
      }

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "DELETE FROM card_transactions";
        try (Statement stmt = conn.createStatement()) {
          stmt.executeUpdate(sql);
          System.out.println("Data cleanup complete");
        }
      } catch (SQLException e) {
        System.err.println("Error during data cleanup: " + e.getMessage());
      }

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "DELETE FROM users";
        try (Statement stmt = conn.createStatement()) {
          stmt.executeUpdate(sql);
          System.out.println("Data cleanup complete");
        }
      } catch (SQLException e) {
        System.err.println("Error during data cleanup: " + e.getMessage());
      }
    }
  }
