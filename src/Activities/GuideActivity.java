package Activities;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GuideActivity implements Initializable {

    public HBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void transformContainer(MouseEvent actionEvent) {
        int value = Integer.parseInt(((Circle) actionEvent.getSource()).getId());

        KeyValue keyValue = new KeyValue(container.translateXProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(2000), keyValue);
        Timeline timeline = new Timeline(keyFrame);

        timeline.setCycleCount(1);
        timeline.play();
    }
}
