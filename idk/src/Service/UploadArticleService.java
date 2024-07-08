package Service;

import Models.UploadArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UploadArticleService {

    public static List<UploadArticle> getAllUploadedArticles() {
        List<UploadArticle> articles = new ArrayList<>();
        String query = "SELECT * FROM uploadartikel"; // Update the table name accordingly

        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String namaPengirim = rs.getString("Nama");
                String fileGambar = rs.getString("JudulFileGambar");
                String fileArtikel = rs.getString("JudulFileArtikel");
                String kategoriArtikel = rs.getString("Kategori");
                LocalDate tanggalKirim = rs.getDate("Tanggal").toLocalDate();
                String filePathGambar = rs.getString("file_pathGambar");

                UploadArticle article = new UploadArticle(namaPengirim, fileGambar, fileArtikel, kategoriArtikel, tanggalKirim, filePathGambar);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
