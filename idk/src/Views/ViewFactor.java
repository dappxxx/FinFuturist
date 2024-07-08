package Views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactor {

    public void showLoginScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Login.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showLoginScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDaftarAkunScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/DaftarAkun.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showDaftarAkunScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showPenulisScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Penulis.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showPenulisScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showUploaderScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Uploader.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showUploaderScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showMainPageScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/MainPage.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showMainPageScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showHapusUploaderScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/HapusUploader.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showHapusUploaderScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showProfileScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Profile.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showProfileScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showPencarianScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Pencarian.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showPencarianScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDisukaiScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Disukai.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showPencarianScene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDisimpanScene(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Disimpan.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in showPencarianScene: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
