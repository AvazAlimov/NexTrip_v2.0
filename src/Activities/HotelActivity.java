package Activities;

import Classes.Hotel;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static Activities.Main.hotels;

public class HotelActivity implements Initializable {
    public ImageView image;
    public Label rate;
    public VBox right_part;
    public Label rate_text;
    public Label rate_number;
    public Label name;
    public Label location;
    public HBox stars;
    private double xOffset;
    private double yOffset;
    public VBox main_image;
    private Hotel hotel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotel = Main.hotel;
        loadRating();
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

    public void fillStars(MouseEvent mouseEvent) {
        int limit = Integer.parseInt(((Button) mouseEvent.getSource()).getId());

        for (int i = 0; i < limit; i++)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.filledStar + "; -fx-background-color: #FFC107;");

        for (int i = 4; i >= limit; i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.emptyStar + "; -fx-background-color: #FFC107;");
    }

    private void loadRating() {
        int sum = 0;
        double rating = 0;
        for (int i = 0; i < hotel.getRatings().size(); i++)
            sum += hotel.getRatings().get(i);

        if (hotel.getRatings().size() > 0)
            rating = (sum / hotel.getRatings().size()) - 1;

        for (int i = 0; i < hotel.getRating(); i++)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.filledStar + "; -fx-background-color: #FFC107;");
        for (int i = 4; i >= hotel.getRating(); i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.emptyStar + "; -fx-background-color: #FFC107;");
        rate_number.setText("based on " + hotel.getRatings().size() + " reviews");
        rate.setText((rating == 0 ? 0 : (rating + 1)) + "");
        rate_text.setText(Main.Rating[(int) (rating > -1 ? rating : 0)]);
    }

    public void restoreStars() {
        loadRating();
    }

    public void rateHotel(ActionEvent event) throws IOException {
        int rating = Integer.parseInt(((Button) event.getSource()).getId());
        hotel.addRating(rating);
        stars.setDisable(true);
        Socket socket = new Socket(Main.serverHost, 2332);
        BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
        byte[] query = ("OH" + hotel.getId() + "/" + rating).getBytes();
        wr.write(query, 0, query.length);
        wr.close();
        socket.close();
    }
}
