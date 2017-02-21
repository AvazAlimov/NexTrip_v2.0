package Activities;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuideActivity implements Initializable {
    public HBox container;
    public Label firstText;
    public Label firstTextLabel;
    public Label secondText;
    public Label secondTextLabel;
    public Label thirdText;
    public Label thirdTextLabel;
    public JFXComboBox<String> languageBox;
    public HBox pageContainer;
    public AnchorPane parent;
    public JFXButton skip_text;
    private double xOffset;
    private double yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        KeyValue value = new KeyValue(parent.opacityProperty(), 1.0);
        KeyFrame frame = new KeyFrame(new Duration(1000), value);
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(1);
        timeline.play();

        languageBox.getItems().add("english");
        languageBox.getItems().add("uzbek");
        languageBox.getItems().add("russian");

        setTexts();
    }

    private void setTexts() {
        firstTextLabel.setText(Main.Language.getTranslation("firstGuideLabel"));
        firstText.setText(Main.Language.getTranslation("firstGuide"));
        secondTextLabel.setText(Main.Language.getTranslation("secondGuideLabel"));
        secondText.setText(Main.Language.getTranslation("secondGuide"));
        thirdTextLabel.setText(Main.Language.getTranslation("thirdGuideLabel"));
        thirdText.setText(Main.Language.getTranslation("thirdGuide"));
        skip_text.setText(Main.Language.getTranslation("skip"));

    }

    public void transformContainer(MouseEvent actionEvent) {
        for (int i = 0; i < pageContainer.getChildren().size(); i++)
            pageContainer.getChildren().get(i).setStyle("-fx-fill: #4AF0F1; -fx-cursor: hand;");
        ((Circle) actionEvent.getSource()).setStyle("-fx-fill: #00BCD4; -fx-cursor: hand;");

        int value = Integer.parseInt(((Circle) actionEvent.getSource()).getId());
        int firstFadeObject = 0;
        int secondFadeObject = 0;
        int showObject = 0;


        switch (value) {
            case 0:
                showObject = 1;
                firstFadeObject = 2;
                secondFadeObject = 3;
                break;
            case -896:
                showObject = 2;
                firstFadeObject = 1;
                secondFadeObject = 3;
                break;
            case -1792:
                showObject = 3;
                firstFadeObject = 2;
                secondFadeObject = 1;
                break;
            default:
                break;
        }

        KeyValue first_fade = new KeyValue(container.getChildren().get(firstFadeObject).opacityProperty(), 0.0);
        KeyValue second_fade = new KeyValue(container.getChildren().get(secondFadeObject).opacityProperty(), 0.0);
        KeyValue show = new KeyValue(container.getChildren().get(showObject).opacityProperty(), 1.0);

        KeyValue first_ScaleX = new KeyValue(container.getChildren().get(firstFadeObject).scaleXProperty(), 0.0);
        KeyValue first_ScaleY = new KeyValue(container.getChildren().get(firstFadeObject).scaleYProperty(), 0.0);

        KeyValue second_ScaleX = new KeyValue(container.getChildren().get(secondFadeObject).scaleXProperty(), 0.0);
        KeyValue second_ScaleY = new KeyValue(container.getChildren().get(secondFadeObject).scaleYProperty(), 0.0);

        KeyValue show_ScaleX = new KeyValue(container.getChildren().get(showObject).scaleXProperty(), 1.0);
        KeyValue show_ScaleY = new KeyValue(container.getChildren().get(showObject).scaleYProperty(), 1.0);

        KeyValue keyValue = new KeyValue(container.translateXProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(600), keyValue, first_fade, second_fade, show, first_ScaleX, first_ScaleY, second_ScaleX, second_ScaleY, show_ScaleX, show_ScaleY);
        Timeline timeline = new Timeline(keyFrame);

        timeline.setCycleCount(1);
        timeline.play();
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

    public void changeLanguage() {
        Main.Language.setLanguage(languageBox.getSelectionModel().getSelectedItem());
        setTexts();
    }

    public void nextWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        Main.stage.getIcons().add(new Image("Resources/icon.png"));
        Main.stage.setTitle("NexTrip");
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
