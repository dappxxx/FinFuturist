// package Controllers;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.BorderPane;

// import java.time.LocalDate;
// import java.util.List;

// import Models.Article;
// import Models.ArticleModels;

// public class UploaderController {

//     @FXML
//     private TableColumn<Article, String> NamaPengirimTabel;

//     @FXML
//     private TableColumn<Article, String> FileGambar;

//     @FXML
//     private TableColumn<Article, String> FileArtikel;

//     @FXML
//     private TableColumn<Article, String> KategoriartikelTabel;

//     @FXML
//     private TableColumn<Article, LocalDate> TanggalTabel;

//     @FXML
//     private ImageView ImageViewUserUploader;

//     @FXML
//     private Button KirimArtikel;

//     @FXML
//     private BorderPane MainBorder;

//     @FXML
//     private Button Pindahpagehapusartikel;

//     @FXML
//     private Button TolakArtikel;

//     @FXML
//     private Button keluarakun;

//     @FXML
//     private AnchorPane mainanchor;

//     @FXML
//     private Button pindahpageuploadartikel;

//     @FXML
//     private TableView<Article> tableView;

//     private ObservableList<Article> articles = FXCollections.observableArrayList();

//     @FXML
//     public void initialize() {
//         // Initialize table columns
//         System.out.println("Initializing table columns...");
//         NamaPengirimTabel.setCellValueFactory(new PropertyValueFactory<>("namaPengirim"));
//         FileGambar.setCellValueFactory(new PropertyValueFactory<>("fileGambar"));
//         FileArtikel.setCellValueFactory(new PropertyValueFactory<>("fileArtikel"));
//         KategoriartikelTabel.setCellValueFactory(new PropertyValueFactory<>("kategoriArtikel"));
//         TanggalTabel.setCellValueFactory(new PropertyValueFactory<>("tanggalKirim"));

//         // Load data to table view
//         loadArticles();
//         tableView.setItems(articles);
//         System.out.println("TableView items set.");
//     }

//     private void loadArticles() {
//         List<Article> allArticles = ArticleModels.getAllArticles();
//         if (allArticles.isEmpty()) {
//             System.out.println("No articles found.");
//         } else {
//             articles.addAll(allArticles);
//             System.out.println("Articles added to the ObservableList: " + articles.size());
//         }
//     }

//     @FXML
//     void handleTolak() {
//         Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
//         if (selectedArticle != null) {
//             articles.remove(selectedArticle);
//             System.out.println("Artikel berhasil ditolak.");
//         } else {
//             System.out.println("Pilih artikel yang ingin ditolak.");
//         }
//     }

//     @FXML
//     void handleUpload() {
//         Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
//         if (selectedArticle != null) {
//             System.out.println("Artikel berhasil diupload ke halaman utama.");
//         } else {
//             System.out.println("Pilih artikel yang ingin diupload.");
//         }
//     }

//     @FXML
//     void handleKeluarAkun() {
//         // Implement handleKeluarAkun here
//     }

//     @FXML
//     void handleHapusArtikel() {
//         // Implement handleHapusArtikel here
//     }

//     @FXML
//     private void changeScene(String fxmlFile) {
//         // Implement changeScene here
//     }
// }

package Controllers;

import Models.Article;
import Models.UploadArticleModels;
import Models.UserDatabaseModels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Service.config;

public class UploaderController {

    @FXML
    private TableView<Article> tableView;

    @FXML
    private TableColumn<Article, String> NamaPengirimTabel;

    @FXML
    private TableColumn<Article, String> FileGambar;

    @FXML
    private TableColumn<Article, String> FileArtikel;

    @FXML
    private TableColumn<Article, String> KategoriartikelTabel;

    @FXML
    private TableColumn<Article, String> TanggalTabel;

    @FXML
    private ImageView ImageViewUserUploader;

    @FXML
    private Button KirimArtikel;

    @FXML
    private Button Pindahpagehapusartikel;

    @FXML
    private Button TolakArtikel;

    @FXML
    private Button keluarakun;

    @FXML
    private AnchorPane mainanchor;

    @FXML
    private Button pindahpageuploadartikel;

    private ObservableList<Article> articles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        NamaPengirimTabel.setCellValueFactory(new PropertyValueFactory<>("namaPengirim"));
        FileGambar.setCellValueFactory(new PropertyValueFactory<>("fileGambar"));
        FileArtikel.setCellValueFactory(new PropertyValueFactory<>("fileArtikel"));
        KategoriartikelTabel.setCellValueFactory(new PropertyValueFactory<>("kategoriArtikel"));
        TanggalTabel.setCellValueFactory(new PropertyValueFactory<>("tanggalKirim"));

        // Load articles from database
        loadArticles();
        tableView.setItems(articles);
    }

    private void loadArticles() {
        List<String> usernames = UserDatabaseModels.getAllUsernames();
        for (String username : usernames) {
            List<Article> userArticles = UserDatabaseModels.getArticlesForUser(username);
            articles.addAll(userArticles);
        }
    }

    @FXML
    void handleTolak(ActionEvent event) {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            boolean isDeleted = UserDatabaseModels.deleteArticle(selectedArticle.getNamaPengirim(), selectedArticle.getFileArtikel());
            if (isDeleted) {
                articles.remove(selectedArticle);
                System.out.println("Artikel berhasil ditolak dan dihapus dari database.");
            } else {
                System.out.println("Gagal menghapus artikel dari database.");
            }
        } else {
            System.out.println("Pilih artikel yang ingin ditolak.");
        }
    }

    @FXML
    void handleUpload(ActionEvent event) {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
        if (selectedArticle != null) {
            // Ambil informasi dari artikel yang dipilih
            String namaPengguna = selectedArticle.getNamaPengirim();
            String fileArtikel = selectedArticle.getFileArtikel();
            String fileGambar = selectedArticle.getFileGambar();
            String kategoriArtikel = selectedArticle.getKategoriArtikel();
            LocalDate tanggalKirim = selectedArticle.getTanggalKirim();

            // Inisialisasi DatabaseService
            DatabaseService dbService = new DatabaseService();

            // Ambil informasi file dari database
            Map<String, Object> fileData = dbService.getFileData(namaPengguna, fileArtikel, tanggalKirim);

            if (fileData != null && !fileData.isEmpty()) {
                String fileNameArtikel = (String) fileData.get("file_NameArtikel");
                long fileSizeArtikel = (Long) fileData.get("file_sizeArtikel");
                String filePathArtikel = (String) fileData.get("file_pathArtikel");

                String fileNameGambar = (String) fileData.get("file_NameGambar");
                long fileSizeGambar = (Long) fileData.get("file_sizeGambar");
                String filePathGambar = (String) fileData.get("file_pathGambar");

                // Proses upload
                boolean isInserted = UploadArticleModels.CariDataUpload(
                    namaPengguna,
                    fileArtikel,
                    fileGambar,
                    kategoriArtikel,
                    tanggalKirim,
                    fileNameArtikel,
                    fileSizeArtikel,
                    filePathArtikel,
                    fileNameGambar,
                    fileSizeGambar,
                    filePathGambar
                );

                if (isInserted) {
                    System.out.println("Artikel berhasil diupload ke database.");
                    // Add to the UploadArtikel table
                    UploadArticleModels.insertUploadArtikel(selectedArticle);

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Artikel.fxml"));
                        Parent root = loader.load();

                        FileArtikelController fileArtikelController = loader.getController();
                        if (fileArtikelController != null) {
                            fileArtikelController.displaySelectedArticle(selectedArticle);
                        } else {
                            System.out.println("FileArtikelController is null. Check FXML and controller setup.");
                            return;
                        }

                        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("/Resource/fxml/MainPage.fxml"));
                        Parent mainPageRoot = mainPageLoader.load();
                        MainPageController mainPageController = mainPageLoader.getController();
                        mainPageController.setHBoxContent(root);
                        mainPageController.loadUploadedArticles(); // Ensure this method is public

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(mainPageRoot);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Gagal mengupload artikel ke database.");
                }
            } else {
                System.out.println("Gagal mendapatkan data file dari database.");
            }
        } else {
            System.out.println("Pilih artikel yang ingin diupload.");
        }
    }

    // Implementasi method untuk mengambil data file dari database
    class DatabaseService {
        public Map<String, Object> getFileData(String namaPengguna, String fileArtikel, LocalDate tanggalKirim) {
            Map<String, Object> fileData = new HashMap<>();
            String query = "SELECT file_NameArtikel, file_sizeArtikel, file_pathArtikel, file_NameGambar, file_sizeGambar, file_pathGambar " +
                           "FROM " + namaPengguna + "_Penulis WHERE JudulFileArtikel = ? AND Tanggal = ?";

            try (Connection conn = config.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, fileArtikel);
                pstmt.setDate(2, java.sql.Date.valueOf(tanggalKirim));
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    fileData.put("file_NameArtikel", resultSet.getString("file_NameArtikel"));
                    fileData.put("file_sizeArtikel", resultSet.getLong("file_sizeArtikel"));
                    fileData.put("file_pathArtikel", resultSet.getString("file_pathArtikel"));
                    fileData.put("file_NameGambar", resultSet.getString("file_NameGambar"));
                    fileData.put("file_sizeGambar", resultSet.getLong("file_sizeGambar"));
                    fileData.put("file_pathGambar", resultSet.getString("file_pathGambar"));
                }

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return fileData;
        }
    }

    private void loadMainPage(ActionEvent event) {
        try {
            FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("/Resource/fxml/MainPage.fxml"));
            Parent mainPageRoot = mainPageLoader.load();

            MainPageController mainPageController = mainPageLoader.getController();
            mainPageController.loadUploadedArticles(); // Ensure this method is public

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(mainPageRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleKeluarAkun(ActionEvent event) {
        Stage stage = (Stage) keluarakun.getScene().getWindow();
        stage.close(); // Close the application window
    }

    @FXML
    void handlePindahPageUploadArtikel(ActionEvent event) {
        // Implement navigation logic to upload article page
    }

    @FXML
    void handlePindahPageHapusArtikel(ActionEvent event) {
        // Implement navigation logic to delete article page
    }

    @FXML
    private void handleHapusArtikel(ActionEvent event) {
        // Handle delete article action here
    }
}





