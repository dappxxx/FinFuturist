import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import Service.config;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        config.getConnection();
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = Main.class.getResource("/Resource/fxml/Login.fxml");
            // URL fxmlLocation = Main.class.getResource("/Resource/fxml/FileArtikel.fxml");
            System.out.println("FXML Location: " + fxmlLocation);
            if (fxmlLocation == null) {
                throw new IOException("FXML file not found");
            }
            loader.setLocation(fxmlLocation);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


// import java.sql.SQLException;
// import java.time.LocalDate;
// import java.util.Scanner;
// import Models.DaftarAkunModels;
// import Models.LoginModels;
// import Service.config;

// public class Main {
//     public static void main(String[] args) throws SQLException {
//         config.getConnection();
//         Scanner scanner = new Scanner(System.in);
        
//         // Registration process
//         System.out.print("Enter ID: ");
//         int id = scanner.nextInt();
//         scanner.nextLine(); // Consume newline left-over

//         System.out.print("Enter Nama Lengkap: ");
//         String namaLengkap = scanner.nextLine();

//         System.out.print("Enter Username: ");
//         String username = scanner.nextLine();

//         System.out.print("Enter Email: ");
//         String email = scanner.nextLine();

//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();

//         System.out.print("Enter Tanggal Lahir (YYYY-MM-DD): ");
//         String tanggalLahirStr = scanner.nextLine();
//         LocalDate tanggalLahir = LocalDate.parse(tanggalLahirStr);

//         boolean isRegistered = DaftarAkunModels.insertData(id, username, email, password, tanggalLahir);
        
//         if (isRegistered) {
//             System.out.println("Data successfully inserted.");
//         } else {
//             System.out.println("Failed to insert data.");
//             return; // Exit if registration fails
//         }
        
//         // Login process
//         System.out.println("Please login to continue:");
//         System.out.print("Enter Username: ");
//         String loginUsername = scanner.nextLine();

//         System.out.print("Enter Password: ");
//         String loginPassword = scanner.nextLine();

//         boolean isAuthenticated = LoginModels.login(loginUsername, loginPassword);

//         if (isAuthenticated) {
//             System.out.println("Login successful.");
//             // You can add more functionality here after successful login
//         } else {
//             System.out.println("Login failed. Invalid username or password.");
//         }

//         scanner.close();
//     }
// }

