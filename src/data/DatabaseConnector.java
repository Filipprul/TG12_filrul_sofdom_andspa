package data;

import game.objects.Player;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import game.core.Constants;

// Verwaltet die gesamte Kommunikation zwischen der Anwendung und der MySQL-Datenbank
public class DatabaseConnector {
    // Erstellt eine neue Verbindung zur Datenbank basierend auf den Einstellungen in Constants
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);
    }

    // Konstruktor: Stellt sicher, dass die Tabelle existiert, sobald der Connector erstellt wird
    public DatabaseConnector() {
        try (Connection conn = getConnection()) {
            // SQL-Befehl zum Erstellen der Tabelle, falls sie noch nicht vorhanden ist
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL UNIQUE," +
                    "passwort_hash VARCHAR(255) NOT NULL," +
                    "highscore INT DEFAULT 0);";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            System.err.println("DB Initialisierungsfehler: " + e.getMessage());
        }
    }

    // Wandelt ein Klartext-Passwort in einen SHA-256 Hash um. Niemals dach echte Passwort speichern
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            // Wandelt die Bytes in eine lesbare Hex-Zeichenkette um
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // Fallback, falls SHA-256 nicht unterstützt wird
        }
    }

    // Registriert einen neuen Benutzer in der Datenbank
    // true bei Erfolg, false falls z.B. der Benutzername schon existiert
    public boolean registerUser(String username, String passwort) {
        String sql = "INSERT INTO users(username, passwort_hash, highscore) VALUES(?,?,0)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            // Passwort wird vor dem Speichern gehasht
            pstmt.setString(2, hashPassword(passwort));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Überprüft, ob die Anmeldedaten korrekt sind
    public boolean checkLogin(String username, String password) {
        String sql = "SELECT passwort_hash FROM users WHERE username = ? AND passwort_hash = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password)); //Benutzereingabe muss ebenfalls gehasht werden
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Wenn rs.next() true ist, ist es ein treffer
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lädt alle Daten für einen bestimmten Spieler aus der DB
    // Ein Player-Objekt oder null, wenn der User nicht gefunden wurde
    public Player getPlayer(String username) {
        String sql = "SELECT id, highscore FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Erstellt aus den DB Werten ein neues Player Objekt für das spiel
                return new Player(rs.getInt("id"), username, "", rs.getInt("highscore"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // Aktualisiert den Highscore eines Spielers in der Datenbank
    public void updateHighscore(int playerId, int newScore) {
        String query = "UPDATE users SET highscore = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, newScore); //Neuer Score
            pstmt.setInt(2, playerId); // ID des Spielers dessen Zeile aktualisiert wird
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}