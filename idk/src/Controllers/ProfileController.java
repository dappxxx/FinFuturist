package Controllers;


import java.io.IOException;

import Models.User;
import Models.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private AnchorPane AnchorMain;

    @FXML
    private Pane AnchorProfile2;

    @FXML
    private ImageView FotoProfile;

    @FXML
    private AnchorPane MainAnchor;

    @FXML
    private ComboBox<String> PilihKategori;

    @FXML
    private Button TombolDisimpan;

    @FXML
    private Button TombolDisukai;

    @FXML
    private Button TombolHalamanUtama;

    @FXML
    private Button TombolProfil;

    @FXML
    private Button TombolRiwayatBaca;

    @FXML
    private Button TombolSearch;

    @FXML
    private Button TombolSimpanPerubahan;

    @FXML
    private Button TombolUbahFotoProfile;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfKataSandi;

    @FXML
    private TextField tfNamaPengguna;

    @FXML
    private DatePicker dpTanggalLahir;

    private User currentUser;

    @FXML
    void initialize() {
        // Inisialisasi data pengguna
        UserManager userManager = UserManager.getInstance();
        currentUser = userManager.getCurrentUser();
        
        if (currentUser != null) {
            tfNamaPengguna.setText(currentUser.getUsername());
            tfEmail.setText(currentUser.getEmail());
            tfKataSandi.setText(currentUser.getPassword());
            dpTanggalLahir.setValue(currentUser.getTanggalLahir());
        }
    }

    @FXML
void handleSimpanPerubahan(ActionEvent event) {
    if (currentUser != null) {
        currentUser.setUsername(tfNamaPengguna.getText());
        currentUser.setEmail(tfEmail.getText());
        currentUser.setPassword(tfKataSandi.getText());
        currentUser.setTanggalLahir(dpTanggalLahir.getValue());

        boolean success = UserManager.getInstance().updateCurrentUser(currentUser);
        if (success) {
            System.out.println("Perubahan telah disimpan");
            updateProfileView(); // Metode baru untuk memperbarui tampilan
        } else {
            System.out.println("Gagal menyimpan perubahan");
        }
    } else {
        System.out.println("Tidak ada pengguna yang sedang masuk.");
    }
}

private void updateProfileView() {
    tfNamaPengguna.setText(currentUser.getUsername());
    tfEmail.setText(currentUser.getEmail());
    tfKataSandi.setText(currentUser.getPassword());
    dpTanggalLahir.setValue(currentUser.getTanggalLahir());
}

    @FXML
    void handleButtonSearch(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Pencarian.fxml"));
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
    void handleHalamanUtama(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/MainPage.fxml"));
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
    void handlePageDisukai(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Disukai.fxml"));
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
    void handlePindahDisimpan(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Disimpan.fxml"));
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
    void handlePindahProfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Profile.fxml"));
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
    void handlePindahRiwayatBaca(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/RiwayatBaca.fxml"));
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
    void handleUbahProfile(ActionEvent event) {
        
    }

    
    
}
