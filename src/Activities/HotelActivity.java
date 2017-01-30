package Activities;

import com.sun.scenario.effect.impl.prism.ps.PPSDrawable;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static Activities.Main.slides;

public class HotelActivity  implements Initializable {


    public ImageView imageView;
    private SequentialTransition root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switch (Main.count) {
                    case 0:
                        imageView.setImage(new Image("/Resources/Pictures/bs.jpg"));
                        break;
                    case 1:
                        imageView.setImage(new Image("/Resources/Pictures/c.jpg"));
                        break;
                    case 2:
                        imageView.setImage(new Image("/Resources/Pictures/e.jpg"));
                        break;
                    case 3:
                        imageView.setImage(new Image("/Resources/Pictures/m.jpg"));
                        break;
                    case 4:
                        imageView.setImage(new Image("/Resources/Pictures/f.jpg"));
                        break;
                }
                Main.count++;
                if (Main.count > 4) {
                    Main.count = 0;
                }
            }
        }, 0, 5000);

    }

    public void closeWindow() {
        Main.closeWindow();
    }


    public void previousWindow() throws IOException {

    }

   /* public void start() {

        SequentialTransition slideshow = new SequentialTransition();

        for (ImageView slide : slides) {

            SequentialTransition sequentialTransition = new SequentialTransition();

            FadeTransition fadeIn = Transition.getFadeTransition(slide, 0.0, 1.0, 2000);
            FadeTransition stayOn = Transition.getFadeTransition(slide, 1.0, 1.0, 2000);
            FadeTransition fadeOut = Transition.getFadeTransition(slide, 1.0, 0.0, 2000);

            sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
            this.root.getChildren().add(slide);
            slideshow.getChildren().add(sequentialTransition);

        }
        slideshow.play();
    }*/

}
