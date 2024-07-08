package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Models.Article;

import java.io.File;

public class FileArtikelController {

    @FXML
    private Label judulArtikelLabel;

    @FXML
    private ImageView artikelImageView;

    public void displaySelectedArticle(Article article) {
        judulArtikelLabel.setText(article.getFilePathArtikel());

        // Load the image from file path
        File imageFile = new File(article.getFilePathGambar());
        if (imageFile.exists()) {
            Image image = new Image(imageFile.toURI().toString());
            artikelImageView.setImage(image);
        } else {
            System.out.println("Image file not found: " + article.getFilePathGambar());
        }
    }
}
