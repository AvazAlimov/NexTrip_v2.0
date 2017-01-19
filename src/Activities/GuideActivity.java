package Activities;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
    private double xOffset;
    private double yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstTextLabel.setText(Main.Language.getTranslation("firstGuideLabel"));
        firstText.setText(Main.Language.getTranslation("firstGuide"));
    }

    public void transformContainer(MouseEvent actionEvent) {
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
            case -768:
                showObject = 2;
                firstFadeObject = 1;
                secondFadeObject = 3;
                break;
            case -1536:
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

        KeyValue keyValue = new KeyValue(container.translateXProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(600), keyValue, first_fade, second_fade, show);
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
}
