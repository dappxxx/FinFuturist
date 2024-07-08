package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Service.config;

public class UploadArticleModels {

    // Method untuk memasukkan artikel ke tabel pengguna
    public static boolean CariDataUpload(String namaPengguna, String fileArtikel, String fileGambar, String kategoriArtikel, LocalDate tanggalKirim, String fileNameArtikel, long fileSizeArtikel, String filePathArtikel, String fileNameGambar, long fileSizeGambar, String filePathGambar) {
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

    // Method untuk memasukkan artikel ke tabel UploadArtikel
    public static boolean insertUploadArtikel(Article article) {
        String query = "INSERT INTO UploadArtikel (Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal, file_NameArtikel, file_sizeArtikel, file_pathArtikel, file_NameGambar, file_sizeGambar, file_pathGambar) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, article.getNamaPengirim());
            pstmt.setString(2, article.getFileArtikel());
            pstmt.setString(3, article.getFileGambar());
            pstmt.setString(4, article.getKategoriArtikel());
            pstmt.setDate(5, java.sql.Date.valueOf(article.getTanggalKirim()));
            pstmt.setString(6, article.getFileArtikel());
            pstmt.setLong(7, article.getFileSizeArtikel());
            pstmt.setString(8, article.getFilePathArtikel());
            pstmt.setString(9, article.getFileGambar());
            pstmt.setLong(10, article.getFileSizeGambar());
            pstmt.setString(11, article.getFilePathGambar());

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted into UploadArtikel.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting article into UploadArtikel: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Method untuk mengambil semua artikel dari tabel UploadArtikel
    public static List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT id, Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal, file_pathGambar, file_pathArtikel, file_sizeGambar, file_sizeArtikel FROM UploadArtikel";

        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaPengirim = resultSet.getString("Nama");
                String fileArtikel = resultSet.getString("JudulFileArtikel");
                String fileGambar = resultSet.getString("JudulFileGambar");
                String kategoriArtikel = resultSet.getString("Kategori");
                LocalDate tanggalKirim = resultSet.getDate("Tanggal").toLocalDate();
                String filePathGambar = resultSet.getString("file_pathGambar");
                String filePathArtikel = resultSet.getString("file_pathArtikel");
                long fileSizeGambar = resultSet.getLong("file_sizeGambar");
                long fileSizeArtikel = resultSet.getLong("file_sizeArtikel");

                Article article = new Article(id, namaPengirim, fileGambar, fileArtikel, kategoriArtikel, tanggalKirim, filePathGambar, filePathArtikel, fileSizeGambar, fileSizeArtikel);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
