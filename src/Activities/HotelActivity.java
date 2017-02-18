package Activities;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HotelActivity implements Initializable {
    public ImageView image;
    public Label rate;
    public VBox right_part;
    public Label rate_text;
    public Label rate_number;
    public Label name;
    //    public ImageView imageView;
//    public JFXButton prevImage;
//    public JFXButton nextImage;
//    public Button freeWiFi;
//    public Button freeParking;
//    public Button freeYard;
//    public TextArea infoText;
//    public Label commentText;
//    public Button CommentButton;
//    public HBox contactContainer;
//    public Button mail_icon;
//    public Button phone_icon;
//    public Button facebook_icon;
//    public Button telegram_icon;
//    public Button web_icon;
    private double xOffset;
    private double yOffset;
    public VBox main_image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeWindow() {
        Main.closeWindow();
    }

    public void mousePressed(MouseEvent mouseEvent) {
        xOffset = Main.stage.getX() - mouseEvent.getScreenX();
        yOffset = Main.stage.getY() - mouseEvent.getScreenY();
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        Main.stage.setX(mouseEvent.getScreenX() + xOffset);
        Main.stage.setY(mouseEvent.getScreenY() + yOffset);
    }

    public void minimize() {
        Main.minimizeWindow();
    }

    public void maximize() {
        Main.maximizeWindow();
    }
}
