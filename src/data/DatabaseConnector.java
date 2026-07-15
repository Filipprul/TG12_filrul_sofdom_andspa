package data;

import java.sql.*;

public class DatabaseConnector {
    // URL für MySQL: jdbc:mysql://[Host]:[Port]/[Datenbankname]
    private final String url = "jdbc:mysql://localhost:3306/snake_game";
    private final String user = "root";
    private final String password = "IamFilipp&Rul";

    public DatabaseConnector() {
        try {
            // 1. MySQL-Treiber laden
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL-Treiber erfolgreich geladen.");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler: MySQL JDBC Treiber nicht gefunden: " + e.getMessage());
        }

        // 2. Initialisierung der Tabelle
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Hinweis: 'AUTO_INCREMENT' ist MySQL-Syntax
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL UNIQUE," +
                    "passwort VARCHAR(255) NOT NULL);";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Fehler bei der Initialisierung der MySQL-DB: " + e.getMessage());
        }
    }

    public boolean registerUser(String username, String passwort) {
        String sql = "INSERT INTO users(username, passwort) VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, passwort);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Registrierung fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }

    public boolean checkLogin(String username, String passwort){
        String sql = "SELECT * FROM users WHERE username = ? AND passwort = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, passwort);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e){
            return false;
        }
    }
}