package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private HBox mainHBox;

    @FXML
    private VBox mainContentVBox;

    public void setHBoxContent(Parent content) {
        mainHBox.getChildren().clear();
        mainHBox.getChildren().add(content);
    }
    
    public void setMainContentVBox(Parent content) {
        mainContentVBox.getChildren().clear();
        mainContentVBox.getChildren().add(content);
    }
}