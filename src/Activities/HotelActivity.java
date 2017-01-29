package Activities;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HotelActivity implements Initializable {


    public ImageView imageView;


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

}
