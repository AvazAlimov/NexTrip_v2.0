package Activities;

import com.jfoenix.controls.JFXButton;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantActivity implements Initializable {
    public ImageView imageView;
    public JFXButton prevImage;
    public JFXButton nextImage;
    private String[] paths;
    private int index;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paths = new String[]{String.valueOf(getClass().getClassLoader().getResource("Resources/Pictures/bs.jpg")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/Pictures/c.jpg")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/Pictures/e.jpg")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/Pictures/m.jpg")),
                String.valueOf(getClass().getClassLoader().getResource("Resources/Pictures/f.jpg"))};
        index = 0;
        imageView.setImage(new Image(paths[index]));


    }
    public void switchPrevImage() {
        if (index == 0)
            index = 5;
        index--;
        imageView.setImage(new Image(paths[index]));

    }

    public void switchNextImage() {
        if (index == 4)
            index = -1;
        index++;
        imageView.setImage(new Image(paths[index]));

    }

    public void previousWindow(ActionEvent event) {

    }
    public void closeWindow() {
        Main.closeWindow();
    }
}