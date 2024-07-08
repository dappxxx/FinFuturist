package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Service.config;

public class UserManager {

    private static UserManager instance;
    private Map<String, User> users;
    private User currentUser;

    private UserManager() {
        users = new HashMap<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = getUserFromDatabase(username);
        if (user != null && user.getPassword().equals(password)) {
            setCurrentUser(user);
            return true;
        }
        return false;
    }

    public String getUserRole(String username) {
        if (username.endsWith(".penulis") || username.endsWith("_penulis")) {
            return "penulis";
        } else if (username.endsWith("_uploader")) {
            return "uploader";
        } else {
            return "pengguna";
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void loadUser(String username) {
        User user = getUserFromDatabase(username);
        if (user != null) {
            setCurrentUser(user);
        }
    }

    private User getUserFromDatabase(String username) {
        String query = "SELECT NamaPengguna, Email, Password, TanggalLahir FROM daftarakun WHERE NamaPengguna = ?";
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                LocalDate tanggalLahir = rs.getDate("TanggalLahir").toLocalDate();
                return new User(username, password, email, tanggalLahir);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user data: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCurrentUser(User user) {
        return DaftarAkunModels.updateUser(user);
    }

    private boolean updateUserInDatabase(User user) {
        String query = "UPDATE daftarakun SET Email = ?, Password = ?, TanggalLahir = ? WHERE NamaPengguna = ?";
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setDate(3, java.sql.Date.valueOf(user.getTanggalLahir()));
            pstmt.setString(4, user.getUsername());
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user data: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
