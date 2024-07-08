// package Controllers;

// import java.io.IOException;
// import java.util.List;

// import Models.Article;
// import Models.UploadArticleModels;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class MainPageController {

//     @FXML
//     private ComboBox<String> PilihKategori;

//     @FXML
//     private Button TombolDisukai;

//     @FXML
//     private Button TombolHalamanUtama;

//     @FXML
//     private Button TombolProfile;

//     @FXML
//     private Button TombolRiwayatBaca;

//     @FXML
//     private HBox hbox;

//     @FXML
//     private AnchorPane scrollArtikel;

//     @FXML
//     private ScrollPane scrollPane;

//     private ObservableList<Article> uploadedArticles = FXCollections.observableArrayList();

//     public void loadUploadedArticles() {
//         try {
//             List<Article> articles = UploadArticleModels.getAllArticles();
//             if (articles != null) {
//                 uploadedArticles.setAll(articles);
//                 if (hbox == null) {
//                     System.out.println("articleContainer is null");
//                 } else {
//                     hbox.getChildren().clear();
//                     for (Article article : uploadedArticles) {
//                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Artikel.fxml"));
//                         HBox articleBox = loader.load();
//                         FileArtikelController controller = loader.getController();
//                         controller.displaySelectedArticle(article);
//                         hbox.getChildren().add(articleBox);
//                     }
//                 }
//             } else {
//                 System.out.println("Tidak ada artikel yang diupload.");
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @FXML
//     public void initialize() {
//         loadUploadedArticles();
//     }

//     @FXML
//     void handlePindahPageDisimpan(ActionEvent event) {
//         // Implement page switch logic here
//     }

//     @FXML
//     void handlePindahPageDisukai(ActionEvent event) {
//         // Implement page switch logic here
//     }

//     @FXML
//     void handlePindahPageHalamanUtama(ActionEvent event) {
//         // Implement page switch logic here
//     }

//     @FXML
//     void handlePindahPageProfile(ActionEvent event) {
//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Profile2.fxml"));
//             Parent root = loader.load();
//             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//             Scene scene = new Scene(root);
//             stage.setScene(scene);
//             stage.show();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @FXML
//     void handlePindahPageRiwayatBaca(ActionEvent event) {
//         // Implement page switch logic here
//     }

//     public void setHBoxContent(Node content) {
//         hbox.getChildren().clear();
//         hbox.getChildren().add(content);
//     }
// }

package Controllers;

import Models.Article;
import Models.UploadArticleModels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    @FXML
    private HBox hbox;



public void initialize() {
    // Memanggil artikel yang telah diunggah.
    loadUploadedArticles();
}

//  artikel yang telah diunggah dari database.
public void loadUploadedArticles() {
    // list daftar artikel dari database
    List<Article> articles = UploadArticleModels.getAllArticles();

    // Menghapus konten di hbox 
    hbox.getChildren().clear();

    try {
        for (Article article : articles) {
            // Membuat FXMLLoader untuk memuat file FXML dari lokasi tertentu.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Artikel.fxml"));
            // Memuat node artikel dari file FXML.
            Node articleNode = loader.load();
            // Mendapatkan controller yang terkait dengan file FXML yang dimuat.
            FileArtikelController controller = loader.getController();
            // Memanggil metode displaySelectedArticle pada controller untuk menampilkan detail artikel.
            controller.displaySelectedArticle(article);
            // Menambahkan node artikel ke dalam hbox.
            hbox.getChildren().add(articleNode);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    void handlePindahPageDisimpan(ActionEvent event) {
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
    void handlePindahPageDisukai(ActionEvent event) {
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
    void handlePindahPageHalamanUtama(ActionEvent event) {
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
    void handlePindahPageProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/fxml/Profile2.fxml"));
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
    void handlePindahPageRiwayatBaca(ActionEvent event) {
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

    public void setHBoxContent(Node content) {
        hbox.getChildren().clear();
        hbox.getChildren().add(content);
    }

}

