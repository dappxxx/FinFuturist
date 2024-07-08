package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HapusUploaderController {

    @FXML
    private TableColumn<?, ?> ColumnFileArtikel;

    @FXML
    private TableColumn<?, ?> ColumnGambarArtikel;

    @FXML
    private TableColumn<?, ?> ColumnKategoriArtikel;

    @FXML
    private TableColumn<?, ?> ColumnNamaPengirim;

    @FXML
    private TableColumn<?, ?> ColumnTanggalArtikel;

    @FXML
    private AnchorPane MainAnchor;

    @FXML
    private Button PageHapusArtikel;

    @FXML
    private TextField SearchArtikel;

    @FXML
    private Button TolakArtikel;

    @FXML
    private Button TombolPindahPageUploadArtikel;

    @FXML
    private Button UploadArtikel;

    @FXML
    private Button TombolKeluarAkun;

    @FXML
    private TableView<?> tabelview;

    @FXML
    void handelPindahPageHapusArtikel(ActionEvent event) {

    }

    @FXML
    void handlePindahUploadArtikel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Uploader.fxml"));
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
    void handleTolakArtikel(ActionEvent event) {

    }

    @FXML
    void handleUploadArtikel(ActionEvent event) {

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
}


