package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Models.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PenulisController {

    @FXML
    private TextField NamaTextField;

    @FXML
    private DatePicker TanggalKirimArtikelTextField;

    @FXML
    private ComboBox<String> PilihkategoriArtikel;

    @FXML
    private TableView<Article> tabelview;

    @FXML
    private TableColumn<Article, String> NamaPengirimTabel;

    @FXML
    private TableColumn<Article, String> FileGambar;

    @FXML
    private TableColumn<Article, String> FileArtikel;

    @FXML
    private TableColumn<Article, String> KategoriartikelTabel;

    @FXML
    private TableColumn<Article, LocalDate> TanggalTabel;

    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private String selectedFileGambarPath = "";
    private String selectedFileArtikelPath = "";

    @FXML
    void handleUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.pdf"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            selectedFileArtikelPath = selectedFile.getAbsolutePath();
            showAlert("File artikel berhasil diunggah: " + selectedFileArtikelPath);
        }
    }

    @FXML
    void handleUploadGambar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            selectedFileGambarPath = selectedFile.getAbsolutePath();
            showAlert("File gambar berhasil diunggah: " + selectedFileGambarPath);
        }
    }

    @FXML
    void handleKeluarAkun(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        NamaPengirimTabel.setCellValueFactory(new PropertyValueFactory<>("namaPengirim"));
        FileGambar.setCellValueFactory(new PropertyValueFactory<>("fileGambar"));
        FileArtikel.setCellValueFactory(new PropertyValueFactory<>("fileArtikel"));
        KategoriartikelTabel.setCellValueFactory(new PropertyValueFactory<>("kategoriArtikel"));
        TanggalTabel.setCellValueFactory(new PropertyValueFactory<>("tanggalKirim"));

        tabelview.setItems(articles);

        PilihkategoriArtikel.getItems().addAll("Investasi Berkelanjutan", "Kategori 2", "Kategori 3");

        loadExistingArticles();
    }

    private void loadExistingArticles() {
        String username = Session.getInstance().getUsername();
        List<Article> existingArticles = DaftarAkunModels.getArticles(username);
        articles.addAll(existingArticles);
    }

    @FXML
void handleKirimArtikel(ActionEvent event) {
    String namaPengirim = Session.getInstance().getUsername();
    String kategoriArtikel = PilihkategoriArtikel.getValue();
    LocalDate tanggalKirim = TanggalKirimArtikelTextField.getValue();

    if (kategoriArtikel == null || tanggalKirim == null || selectedFileGambarPath.isEmpty() || selectedFileArtikelPath.isEmpty()) {
        showAlert("Semua kolom harus diisi!");
        return;
    }

    File artikelFile = new File(selectedFileArtikelPath);
    String fileNameArtikel = artikelFile.getName();
    long fileSizeArtikel = artikelFile.length();
    String filePathArtikel = artikelFile.getAbsolutePath();

    File gambarFile = new File(selectedFileGambarPath);
    String fileNameGambar = gambarFile.getName();
    long fileSizeGambar = gambarFile.length();
    String filePathGambar = gambarFile.getAbsolutePath();

    // Insert into writer's specific table
    boolean isSuccess = DaftarAkunModels.insertArtikel(namaPengirim, fileNameArtikel, fileNameGambar, kategoriArtikel, tanggalKirim, fileNameArtikel, fileSizeArtikel, filePathArtikel, fileNameGambar, fileSizeGambar, filePathGambar);
    
    // Insert into common uploader table
    boolean isUploadSuccess = UploadArticleModels.insertUploadArtikel(new Article(namaPengirim, fileNameArtikel, fileNameGambar, kategoriArtikel, tanggalKirim, filePathGambar, filePathArtikel, fileSizeGambar, fileSizeArtikel));

    if (isSuccess && isUploadSuccess) {
        Article newArticle = new Article(namaPengirim, fileNameGambar, fileNameArtikel, kategoriArtikel, tanggalKirim, filePathGambar, filePathArtikel, fileSizeGambar, fileSizeArtikel);
        articles.add(newArticle);
        clearFields();
    } else {
        showAlert("Gagal menyimpan artikel ke database.");
    }
}

    @FXML
    void handlehapusdata(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        PilihkategoriArtikel.getSelectionModel().clearSelection();
        TanggalKirimArtikelTextField.setValue(null);
        selectedFileGambarPath = "";
        selectedFileArtikelPath = "";
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
