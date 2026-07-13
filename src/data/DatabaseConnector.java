package data;

import java.sql.*;

public class DatabaseConnector {
    // URL für SQLite
    private final String url = "jdbc:sqlite:game.db";

    public DatabaseConnector() {
        // Verbindungsversuch mit der Datenbankdatei
        try (Connection conn = DriverManager.getConnection(url)) {
            // Erstellt die Tabelle 'users', falls sie noch nicht existiert
            String sql = "CREATE TABLE IF NOT EXISTS users (\"" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL);";
            // Statement-Objekt wird erstellt, um SQL an die DB zu senden
            Statement stmt = conn.createStatement();
            // Führt den Erstellungsbefehl aus
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Fehler bei der Initialisierung der DB: " + e.getMessage());
        }
    }

    public boolean registerUser(String username, String passwort) {
        // SQL-Befehl zum Einfügen eines neuen Benutzers mit Platzhaltern
        String sql = "INSERT INTO users(username, passwort) VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(url);
            // PreparedStatement verhindert SQL-Injection, indem Werte sicher gebunden werden
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
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, passwort);
            ResultSet rs = pstmt.executeQuery();
            // rs.next() gibt true zurück, wenn mindestens ein Eintrag gefunden wurde
            return rs.next();
        } catch (SQLException e){return false;}
    }
}

