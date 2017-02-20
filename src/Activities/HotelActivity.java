package Activities;

import Classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class HotelActivity implements Initializable {
    public ImageView image;
    public Label rate;
    public VBox right_part;
    public Label rate_text;
    public Label rate_number;
    public Label name;
    public Label location;
    public HBox stars;
    public Label your_rate;
    public Label price;
    public JFXButton info_button;
    public Label overall_rating;
    public Label excellent_text;
    public Label excellent_number;
    public Label bad_number;
    public Label bad_text;
    public Label normal_number;
    public Label normal_text;
    public Label good_number;
    public Label good_text;
    public Label fantastic_text;
    public Label fantastic_number;
    public Label amenities_text;
    public HBox amenity_container;
    public HBox contact_container;
    public Label contact_text;
    public GridPane main_layout;
    public GridPane contact_layout;
    public VBox contact_source;
    public Label contact_source_type;
    public ImageView contact_source_image;
    public Hyperlink contact_source_text;
    public GridPane info_layout;
    public Label info_text;
    public Label information;
    public VBox comments_container;
    public JFXTextField comment_text;
    public Label comment_header;
    private double xOffset;
    private double yOffset;
    public VBox main_image;
    private Hotel hotel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotel = Main.hotel;
        loadRating();
        name.setText(hotel.getName());
        this.location.setText(hotel.getLocation());
        price.setText(hotel.getStartingPrice() + " - " + hotel.getEndingPrice());
        info_text.setText(hotel.getInfo());
        countRates();
        addAmenities();
        addContacts();
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
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.filledStar + "; -fx-background-color: #FFC107; -fx-cursor: hand;");

        for (int i = 4; i >= limit; i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.emptyStar + "; -fx-background-color: #FFC107; -fx-cursor: hand;");
    }

    private void loadRating() {
        countRates();
        float sum = 0;
        float rating = 0;
        for (int i = 0; i < hotel.getRatings().size(); i++)
            sum += hotel.getRatings().get(i);

        if (hotel.getRatings().size() > 0)
            rating = (sum / hotel.getRatings().size()) - 1.0f;

        for (int i = 0; i < hotel.getRating(); i++)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.filledStar + "; -fx-background-color: #FFC107; -fx-cursor: hand;");
        for (int i = 4; i >= hotel.getRating(); i--)
            stars.getChildren().get(i).setStyle("-fx-shape: " + Main.emptyStar + "; -fx-background-color: #FFC107; -fx-cursor: hand;");
        rate_number.setText("based on " + hotel.getRatings().size() + " reviews");
        rate.setText(String.format("%.01f", (rating == 0.0f ? 0.0f : (rating + 1.0f))));
        rate_text.setText(Main.Rating[(int) (rating > -1 ? rating : 0)]);
    }

    public void restoreStars() {
        loadRating();
    }

    public void rateHotel(ActionEvent event) throws IOException {
        int rating = Integer.parseInt(((Button) event.getSource()).getId());
        hotel.addRating(rating);
        your_rate.setText("You Rated");
        stars.setDisable(true);
        Socket socket = new Socket(Main.serverHost, 2332);
        BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
        byte[] query = ("OH" + hotel.getId() + "/" + rating).getBytes();
        wr.write(query, 0, query.length);
        wr.close();
        socket.close();
    }

    private void countRates() {
        int[] ratings = new int[]{0, 0, 0, 0, 0};
        for (int rating : hotel.getRatings())
            ratings[rating - 1]++;
        bad_number.setText(ratings[0] + "");
        normal_number.setText(ratings[1] + "");
        good_number.setText(ratings[2] + "");
        excellent_number.setText(ratings[3] + "");
        fantastic_number.setText(ratings[4] + "");
    }

    private void addAmenities() {
        for (String amenity : hotel.getAmenties()) {
            HBox box = new HBox(20);
            box.setStyle("-fx-padding: 5; -fx-spacing: 10;");
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("../Resources/Icons/checked.png"))));
            imageView.setFitHeight(32);
            imageView.setFitWidth(32);
            Label label = new Label(amenity);
            box.getChildren().add(imageView);
            box.getChildren().add(label);
            amenity_container.getChildren().add(box);
        }
    }

    private void addContacts() {
        for (Contact contact : hotel.getContacts()) {
            System.out.println(contact.getType());
            JFXButton button = new JFXButton();
            button.setStyle("-fx-padding: 5; -fx-background-color: transparent; -fx-shape: 'M255 0C114.75 0 0 114.75 0 255s114.75 255 255 255s255-114.75 255-255S395.25 0 255 0z';");
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("../Resources/Icons/" + contact.getType() + ".png"))));
            imageView.setFitWidth(56);
            imageView.setFitHeight(56);
            button.setGraphic(imageView);
            button.setId(contact.getType() + "☼" + contact.getSource());
            button.setOnAction(event -> {
                contact_source_type.setText(button.getId().substring(0, button.getId().indexOf("☼")));
                contact_source_image.setImage(((ImageView) button.getGraphic()).getImage());
                contact_source_text.setText(button.getId().substring(button.getId().indexOf("☼") + 1, button.getId().length()));
                contact_layout.setVisible(true);
                contact_layout.setScaleX(0.0);
                contact_layout.setScaleY(0.0);
                contact_layout.setOpacity(0.0);
                KeyValue opacity = new KeyValue(contact_layout.opacityProperty(), 1.0);
                KeyValue valueX = new KeyValue(contact_layout.scaleXProperty(), 1.0);
                KeyValue valueY = new KeyValue(contact_layout.scaleYProperty(), 1.0);
                Timeline timeline = new Timeline(new KeyFrame(new Duration(300), valueX, valueY, opacity));
                timeline.setCycleCount(1);
                timeline.play();
            });
            contact_container.getChildren().add(button);
        }
    }

    public void showMainLayout() {
        KeyValue opacity = new KeyValue(contact_layout.opacityProperty(), 0.0);
        KeyValue valueX = new KeyValue(contact_layout.scaleXProperty(), 0.0);
        KeyValue valueY = new KeyValue(contact_layout.scaleYProperty(), 0.0);
        Timeline timeline = new Timeline(new KeyFrame(new Duration(300), valueX, valueY, opacity));
        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(event -> contact_layout.setVisible(false));
        contact_layout.setScaleX(1.0);
        contact_layout.setScaleY(1.0);
    }

    public void openBrowser() {
        Main.hostServices.showDocument(contact_source_text.getText());
    }

    public void showInfo() {
        info_layout.setVisible(true);
        info_layout.setScaleX(0.0);
        info_layout.setScaleY(0.0);
        info_layout.setOpacity(0.0);
        KeyValue valueX = new KeyValue(info_layout.scaleXProperty(), 1.0);
        KeyValue valueY = new KeyValue(info_layout.scaleYProperty(), 1.0);
        KeyValue opacity = new KeyValue(info_layout.opacityProperty(), 1.0);
        Timeline timeline = new Timeline(new KeyFrame(new Duration(300), valueX, valueY, opacity));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void closeInfo() {
        KeyValue valueX = new KeyValue(info_layout.scaleXProperty(), 0.0);
        KeyValue valueY = new KeyValue(info_layout.scaleYProperty(), 0.0);
        KeyValue opacity = new KeyValue(info_layout.opacityProperty(), 0.0);
        Timeline timeline = new Timeline(new KeyFrame(new Duration(300), valueX, valueY, opacity));
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> info_layout.setVisible(false));
        timeline.play();
        info_layout.setScaleX(0.0);
        info_layout.setScaleY(0.0);
        info_layout.setOpacity(0.0);
    }

    public void backToMenu() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Main.stage.getIcons().add(new Image("Resources/icon.png"));
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    public void addComment() {
        if (comment_text.getText().isEmpty())
            return;
        Comment comment = new Comment();
        comment.setGuest(new Guest("", "", "Guest"));
        comment.setWrittenDate(new Date(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear()));
        comment.setComment(comment_text.getText());
        addCommentItem(comment);
        hotel.addComment(comment);
    }

    private void addCommentItem(Comment comment) {
        VBox item = new VBox();
        Label username = new Label(comment.getGuest().getUsername());
        username.setStyle("-fx-font-size: 20; -fx-font-weight: bolder; -fx-text-fill: #FFC107;");
        Label source = new Label(comment.getComment());
        source.setStyle("-fx-font-size: 20;");
        Label date = new Label(comment.getWrittenDate().toString());
        date.setStyle("-fx-font-size: 18; -fx-alignment: bottom-right;");
        item.getChildren().add(username);
        item.getChildren().add(source);
        item.getChildren().add(date);
        item.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-padding: 10;");
        comments_container.getChildren().add(item);
        comment_text.setText("");
    }
}
