package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Service.config;

public class DaftarAkunModels {

    public static boolean insertData(int id, String namaPengguna, String email, String password, LocalDate tanggalLahir) {
        String insertQuery = "INSERT INTO daftarakun (id, NamaPengguna, Email, Password, TanggalLahir) VALUES (?, ?, ?, ?, ?)";
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + namaPengguna + "_Penulis (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "Nama VARCHAR(255), " +
                                  "JudulFileArtikel VARCHAR(255), " +
                                  "JudulFileGambar VARCHAR(255), " +
                                  "Kategori VARCHAR(255), " +
                                  "Tanggal DATE, " +
                                  "file_NameArtikel VARCHAR(255), " +
                                  "file_sizeArtikel BIGINT, " +
                                  "file_pathArtikel VARCHAR(255), " +
                                  "file_NameGambar VARCHAR(255), " +
                                  "file_sizeGambar BIGINT, " +
                                  "file_pathGambar VARCHAR(255)" +
                                  ")";
    
        try (Connection conn = config.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
             PreparedStatement createTableStmt = conn.prepareStatement(createTableQuery)) {
            
            // Insert user data
            insertStmt.setInt(1, id);
            insertStmt.setString(2, namaPengguna);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password);
            insertStmt.setDate(5, java.sql.Date.valueOf(tanggalLahir));
            int rowsAffected = insertStmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted into daftarakun.");
    
            // Create user-specific table
            createTableStmt.executeUpdate();
            System.out.println("Table " + namaPengguna + "_Penulis created.");
    
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting data or creating table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    

    public static boolean insertArtikel(String namaPengguna, String fileArtikel, String fileGambar, String kategoriArtikel, LocalDate tanggalKirim, String fileNameArtikel, long fileSizeArtikel, String filePathArtikel, String fileNameGambar, long fileSizeGambar, String filePathGambar) {
        String query = "INSERT INTO " + namaPengguna + "_Penulis (Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal, " +
                       "file_NameArtikel, file_sizeArtikel, file_pathArtikel, file_NameGambar, file_sizeGambar, file_pathGambar) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, namaPengguna);
            pstmt.setString(2, fileArtikel);
            pstmt.setString(3, fileGambar);
            pstmt.setString(4, kategoriArtikel);
            pstmt.setDate(5, java.sql.Date.valueOf(tanggalKirim));
            pstmt.setString(6, fileNameArtikel);
            pstmt.setLong(7, fileSizeArtikel);
            pstmt.setString(8, filePathArtikel);
            pstmt.setString(9, fileNameGambar);
            pstmt.setLong(10, fileSizeGambar);
            pstmt.setString(11, filePathGambar);

            System.out.println("Executing query: " + pstmt);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted into " + namaPengguna + "_Penulis.");

            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting article: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fungsi untuk mengambil semua nama pengguna yang terdaftar
    public static List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT NamaPengguna FROM daftarakun";

        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("NamaPengguna");
                usernames.add(username);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching usernames: " + e.getMessage());
            e.printStackTrace();
        }
        return usernames;
    }

    // Fungsi untuk mendapatkan artikel dari sebuah pengguna tertentu
    public static List<Article> getArticles(String username) {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal, file_pathGambar, file_pathArtikel, file_sizeGambar, file_sizeArtikel " +
                       "FROM " + username + "_Penulis";
    
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                String namaPengirim = rs.getString("Nama");
                String fileArtikel = rs.getString("JudulFileArtikel");
                String fileGambar = rs.getString("JudulFileGambar");
                String kategoriArtikel = rs.getString("Kategori");
                LocalDate tanggalKirim = rs.getDate("Tanggal").toLocalDate();
                String filePathGambar = rs.getString("file_pathGambar");
                String filePathArtikel = rs.getString("file_pathArtikel");
                long fileSizeGambar = rs.getLong("file_sizeGambar");
                long fileSizeArtikel = rs.getLong("file_sizeArtikel");
    
                Article article = new Article(namaPengirim, fileArtikel, fileGambar, kategoriArtikel, tanggalKirim, filePathGambar, filePathArtikel, fileSizeGambar, fileSizeArtikel);
                articles.add(article);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching articles for username " + username + ": " + e.getMessage());
            e.printStackTrace();
        }
        return articles;
    }
    

    public static User getUserByUsername(String username) {
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

    public static boolean updateUser(User user) {
        String query = "UPDATE daftarakun SET Email = ?, Password = ?, TanggalLahir = ? WHERE NamaPengguna = ?";
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setDate(3, java.sql.Date.valueOf(user.getTanggalLahir()));
            pstmt.setString(4, user.getUsername());
    
            System.out.println("Executing update: " + pstmt); // Debugging line
            int rowsAffected = pstmt.executeUpdate();
    
            System.out.println("Rows affected: " + rowsAffected); // Debugging line
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user data: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
