// package Models;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import Service.config;

// public class ArticleModels {

    

//     public static List<Article> getAllArticles() {
//         List<Article> articles = new ArrayList<>();
//         List<String> userTables = getUserTables();

//         for (String table : userTables) {
//             String query = "SELECT Nama, JudulFileArtikel, JudulFileGambar, Kategori, Tanggal FROM " + username + "_Penulis";
//             try (Connection conn = config.getConnection();
//                  PreparedStatement pstmt = conn.prepareStatement(query);
//                  ResultSet rs = pstmt.executeQuery()) {

//                 while (rs.next()) {
//                     String namaPengirim = rs.getString("Nama");
//                     String fileArtikel = rs.getString("JudulFileArtikel");
//                     String fileGambar = rs.getString("JudulFileGambar");
//                     String kategoriArtikel = rs.getString("Kategori");
//                     LocalDate tanggalKirim = rs.getDate("Tanggal").toLocalDate();

//                     Article article = new Article(namaPengirim, fileGambar, fileArtikel, kategoriArtikel, tanggalKirim);
//                     articles.add(article);
//                 }
//             } catch (SQLException e) {
//                 System.err.println("Error fetching articles from " + table + ": " + e.getMessage());
//                 e.printStackTrace();
//             }
//         }
//         System.out.println("Total articles loaded: " + articles.size());
//         return articles;
//     }

//     private static List<String> getUserTables() {
//         List<String> userTables = new ArrayList<>();
//         String query = "SELECT table_name FROM information_schema.tables WHERE table_schema='your_database_schema' AND table_name LIKE '%_Penulis'";

//         try (Connection conn = config.getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(query);
//              ResultSet rs = pstmt.executeQuery()) {

//             while (rs.next()) {
//                 String tableName = rs.getString("table_name");
//                 userTables.add(tableName);
//             }
//         } catch (SQLException e) {
//             System.err.println("Error fetching user tables: " + e.getMessage());
//             e.printStackTrace();
//         }
//         return userTables;
//     }
// }
