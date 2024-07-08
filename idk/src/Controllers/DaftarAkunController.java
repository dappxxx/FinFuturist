package Controllers;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.DaftarAkunModels;
import Models.User;
import Models.UserManager;
import Views.ViewFactor;
import Models.  DaftarAkunModels;

public class DaftarAkunController {

    @FXML
    private TextField DaftarNama;

    @FXML
    private TextField DaftarEmail;

    @FXML
    private PasswordField DaftarKataSandi;

    @FXML
    private DatePicker DaftarTanggalLahir;

    private UserManager userManager;

    public DaftarAkunController() {
        this.userManager = UserManager.getInstance(); // Using singleton pattern
    }

    // @FXML
    // public void handleButtonDaftar(ActionEvent event) {
    //     // Save the entered account data
    //     String nama = DaftarNama.getText();
    //     String email = DaftarEmail.getText();
    //     String password = DaftarKataSandi.getText();
    //     LocalDate tanggalLahir = DaftarTanggalLahir.getValue();

    //     // Check if any fields are empty or the date of birth is not selected
    //     if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || tanggalLahir == null) {
    //         System.out.println("Data yang diinputkan tidak lengkap");
    //         return;
    //     }

    //     // Create a new User object and add it to the UserManager
    //     User newUser = new User(nama, password); // Assuming User constructor takes name and password
    //     userManager.addUser(newUser);

    //     // Display success message or navigate to the login page
    //     System.out.println("Registrasi berhasil untuk pengguna dengan nama: " + nama);

    //     // Navigate to the login scene using ViewFactor
    //     ViewFactor viewFactor = new ViewFactor();
    //     viewFactor.showLoginScene(DaftarNama.getScene().getRoot()); // Pass the root node of current scene
    // }

     @FXML
    void handleButtonDaftar(ActionEvent event) {
        String NamaPengguna = DaftarNama.getText();
        String Email = DaftarEmail.getText();
        String Password = DaftarKataSandi.getText();
        LocalDate TanggalLahir = DaftarTanggalLahir.getValue();
        
        if (DaftarAkunModels.insertData(0, NamaPengguna, Email, Password, TanggalLahir)) { 
            System.out.println("Registration Successful");
        } else {
            System.out.println("Registration Failed");
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resource/fxml/Login.fxml"));
            Stage stage = (Stage) DaftarEmail.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


    // @FXML
    // public void handleButtonDaftar(ActionEvent event) {
    //     // Save the entered account data
    //     String nama = DaftarNama.getText();
    //     String email = DaftarEmail.getText();
    //     String password = DaftarKataSandi.getText();
    //     LocalDate tanggalLahir = DaftarTanggalLahir.getValue();

    //     // Check if any fields are empty or the date of birth is not selected
    //     if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || tanggalLahir == null) {
    //         System.out.println("Data yang diinputkan tidak lengkap");
    //         return;
    //     }

    //     // Create a new User object and add it to the UserManager
    //     User newUser = new User(nama, password); // Assuming User constructor takes name and password
    //     userManager.addUser(newUser);

    //     // Display success message or navigate to the login page
    //     System.out.println("Registrasi berhasil untuk pengguna dengan nama: " + nama);

    //     // Navigate to the login scene using ViewFactor
    //     ViewFactor viewFactor = new ViewFactor();
    //     viewFactor.showLoginScene(DaftarNama.getScene().getRoot()); // Pass the root node of current scene
    // }
