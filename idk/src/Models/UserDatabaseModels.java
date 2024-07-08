package Models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Service.config;

public class UserDatabaseModels {

    // untuk ambil semua username dari tabel `daftarakun`.
    public static List<String> getAllUsernames() {
        // Membuat list untuk menyimpan semua username.
        List<String> usernames = new ArrayList<>();
        // memilih semua username dari tabel `daftarakun`.
        String query = "SELECT NamaPengguna FROM daftarakun";

        // Memulai blok try-with-resources untuk koneksi dan statement.
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterasi melalui hasil query dan menambahkan username ke list.
            while (rs.next()) {
                String username = rs.getString("NamaPengguna");
                usernames.add(username);
            }
        // Menangani potensi SQLException dan mencetak pesan kesalahan.
        } catch (SQLException e) {
            System.err.println("Error fetching usernames: " + e.getMessage());
            e.printStackTrace();
        }
        // Mengembalikan list username.
        return usernames;
    }

    // untuk mendapatkan artikel-artikel yang dikirim oleh user tertentu
    public static List<Article> getArticlesForUser(String username) {
        // Membuat list untuk menyimpan artikel-artikel.
        List<Article> articles = new ArrayList<>();
        // memilih artikel-artikel dari tabel user penulis tertentu.
        String query = "SELECT Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal, file_pathGambar, file_pathArtikel, file_sizeGambar, file_sizeArtikel " +
                       "FROM " + username + "_Penulis";
    
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
    
            // membuat objek Article, lalu menambahkannya ke list
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
    
                
                Article article = new Article(namaPengirim, fileGambar, fileArtikel, kategoriArtikel, tanggalKirim, filePathGambar, filePathArtikel, fileSizeGambar, fileSizeArtikel);
                articles.add(article);
            }
        
        } catch (SQLException e) {
            System.err.println("Error fetching articles for username " + username + ": " + e.getMessage());
            e.printStackTrace();
        }
        // Mengembalikan list artikel.
        return articles;
    }
    
    // menghapus artikel 
    public static boolean deleteArticle(String username, String articleTitle) {
        // menghapus artikel berdasarkan judulnya dari tabel user penulis tertentu.
        String query = "DELETE FROM " + username + "_Penulis WHERE JudulFileArtikel = ?";

        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Mengatur parameter dengan judul artikel yang akan dihapus.
            pstmt.setString(1, articleTitle);

            // Mengeksekusi update dan mendapatkan jumlah baris 
            int affectedRows = pstmt.executeUpdate();
            // artinya penghapusan berhasil
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting article: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
