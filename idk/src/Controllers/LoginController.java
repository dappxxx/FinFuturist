package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import Models.UserManager;
import Models.Session;
import Views.ViewFactor;

public class LoginController {

    private ViewFactor viewFactor;

    public LoginController() {
        viewFactor = new ViewFactor(); // Inisialisasi viewFactor
    }

    @FXML
    private ImageView ImageViewLogo;

    @FXML
    private ImageView ImageViewUser;

    @FXML
    private ImageView ImageViewUsers;

    @FXML
    private AnchorPane Main2Anchor;

    @FXML
    private Pane MainPane;

    @FXML
    private AnchorPane Mainanchor;

    @FXML
    private PasswordField MasukKataSandi;

    @FXML
    private TextField MasukNama;

    @FXML
    private Button TombolDaftarAkun;

    @FXML
    private Button TombolMasuk;

    @FXML
    private ImageView ImageViewPassword;

    @FXML
    private Text masukAkunText;

    private boolean validateLogin(String username, String password) {
        return UserManager.getInstance().authenticateUser(username, password);
    }

    private void navigateToPage(String username) {
        try {
            String role = UserManager.getInstance().getUserRole(username);
            if (Mainanchor == null) {
                System.out.println("AnchorPane Mainanchor is null");
                return;
            }
            switch (role) {
                case "penulis":
                    viewFactor.showPenulisScene(Mainanchor);
                    break;
                case "uploader":
                    viewFactor.showUploaderScene(Mainanchor);
                    break;
                default:
                    viewFactor.showMainPageScene(Mainanchor);
                    break;
            }
            Stage loginStage = (Stage) TombolMasuk.getScene().getWindow();
            if (loginStage != null) {
                loginStage.close();
            } else {
                System.out.println("Login stage is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to navigate to the main page");
        }
    }

    @FXML
    void handleMasuk(ActionEvent event) {
        String username = MasukNama.getText();
        String password = MasukKataSandi.getText();

        if (validateLogin(username, password)) {
            Session.getInstance().setUsername(username);  // Simpan nama pengguna dalam sesi
            navigateToPage(username);
        } else {
            System.out.println("Login Failed");
        }
    }

    @FXML
    void handleDaftarAkun(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resource/fxml/DaftarAkun.fxml"));
            Stage stage = (Stage) MasukNama.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
