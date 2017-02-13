package Classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImageView extends ImageView {
    private String path;

    public CustomImageView(String path, String url){
        super(new Image(url, 128.0, 128.0, false, true));
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
