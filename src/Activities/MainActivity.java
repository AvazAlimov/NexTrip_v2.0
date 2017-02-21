package Activities;

import Classes.Entertaining;
import Classes.Hotel;
import Classes.Restaurant;
import Classes.ThingsToDo;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class MainActivity implements Initializable {
    public VBox container;
    public HBox datePane;
    public ComboBox<String> searchText;
    public DatePicker startDate;
    public DatePicker endDate;
    private String choosenType = "Hotels";
    private double xOffset;
    private double yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void switchTabs(ActionEvent event) {
        Button btn = (Button) event.getSource();
        HBox box = (HBox) btn.getParent();
        for (Node item : box.getChildren())
            item.setStyle("-fx-background-color: #FFC107;");
        choosenType = btn.getText();
        container.getChildren().clear();
        switch (btn.getText()) {
            case "Hotels":
                datePane.setVisible(false);
                break;
            case "Restaurants":
                datePane.setVisible(false);
                break;
            case "Entertaining":
                datePane.setVisible(false);
                break;
            case "Things To Do":
                datePane.setVisible(true);
                break;
            default:
                break;
        }
        btn.setStyle("-fx-background-color: #FFA000;");
    }

    public void searchObject() {
        String text = searchText.getEditor().getText();
        if (text.isEmpty())
            return;

        searchText.getItems().clear();

        switch (choosenType) {
            case "Hotels":
                Main.hotels.stream().filter(hotel -> Main.contains(hotel.getLocation().toLowerCase(), text.toLowerCase())).forEach(hotel -> searchText.getItems().add(hotel.getLocation()));
                break;
            case "Entertaining":
                Main.entertainings.stream().filter(entertaining -> Main.contains(entertaining.getLocation().toLowerCase(), text.toLowerCase())).forEach(entertaining -> searchText.getItems().add(entertaining.getLocation()));
                break;
            case "Restaurants":
                Main.restaurants.stream().filter(restaurant -> Main.contains(restaurant.getLocation().toLowerCase(), text.toLowerCase())).forEach(restaurant -> searchText.getItems().add(restaurant.getLocation()));
                break;
            case "Things To Do":
                try {
                    Main.thingsToDos.stream().filter(thingsToDo -> Main.contains(thingsToDo.getLocation().toLowerCase(), text.toLowerCase()) && Main.checkDate(startDate, endDate, thingsToDo)).forEach(thingsToDo -> searchText.getItems().add(thingsToDo.getLocation()));

                } catch (Exception ignored) {
                }
                break;
            default:
                break;
        }
        searchText.show();
    }

    public void addObjects() {
        Runnable runnable = () -> {
            switch (choosenType) {
                case "Hotels":
                    addHotels();
                    break;
                case "Restaurants":
                    addRestaurants();
                    break;
                case "Entertaining":
                    addEntertaining();
                    break;
                case "Things To Do":
                    try {
                        addThingsToDo();
                    } catch (Exception ignored) {
                    }
                    break;
                default:
                    break;
            }
        };
        Platform.runLater(runnable);
    }

    private void addHotels() {
        ArrayList<Hotel> hotels = Main.findHotel(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Hotel hotel : hotels) {
            JFXButton button = new JFXButton();
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-fit-to-width: true;");
            GridPane pane = fillHotelItem(hotel);
            button.setGraphic(pane);
            pane.setScaleX(0.0);
            pane.setScaleY(0.0);
            button.setOnMouseClicked(pane.getOnMouseClicked());
            container.getChildren().add(button);
            KeyValue value = new KeyValue(pane.scaleXProperty(), 1.0);
            KeyValue value1 = new KeyValue(pane.scaleYProperty(), 1.0);
            KeyFrame frame = new KeyFrame(new Duration(400), value, value1);
            Timeline anim = new Timeline(frame);
            anim.setCycleCount(1);
            anim.play();
        }
    }

    private void addRestaurants() {
        ArrayList<Restaurant> restaurants = Main.findRestaurants(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Restaurant restaurant : restaurants) {
            JFXButton button = new JFXButton();
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-fit-to-width: true;");
            GridPane pane = fillRestaurantItem(restaurant);
            button.setGraphic(pane);
            pane.setScaleX(0.0);
            pane.setScaleY(0.0);
            button.setOnMouseClicked(pane.getOnMouseClicked());
            container.getChildren().add(button);
            KeyValue value = new KeyValue(pane.scaleXProperty(), 1.0);
            KeyValue value1 = new KeyValue(pane.scaleYProperty(), 1.0);
            KeyFrame frame = new KeyFrame(new Duration(400), value, value1);
            Timeline anim = new Timeline(frame);
            anim.setCycleCount(1);
            anim.play();
        }
    }

    private void addThingsToDo() {
        ArrayList<ThingsToDo> thingsToDos = Main.findThingsToDo(searchText.getEditor().getText(), startDate, endDate);
        container.getChildren().clear();
        for (ThingsToDo thingsToDo : thingsToDos) {
            JFXButton button = new JFXButton();
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-fit-to-width: true;");
            GridPane pane = fillThingsToDoItem(thingsToDo);
            button.setGraphic(pane);
            pane.setScaleX(0.0);
            pane.setScaleY(0.0);
            button.setOnMouseClicked(pane.getOnMouseClicked());
            container.getChildren().add(button);
            KeyValue value = new KeyValue(pane.scaleXProperty(), 1.0);
            KeyValue value1 = new KeyValue(pane.scaleYProperty(), 1.0);
            KeyFrame frame = new KeyFrame(new Duration(400), value, value1);
            Timeline anim = new Timeline(frame);
            anim.setCycleCount(1);
            anim.play();
        }
    }

    private void addEntertaining() {
        ArrayList<Entertaining> entertainings = Main.findEntertainings(searchText.getEditor().getText());
        container.getChildren().clear();
        for (Entertaining entertaining : entertainings) {
            JFXButton button = new JFXButton();
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-fit-to-width: true;");
            GridPane pane = fillEntertainingItem(entertaining);
            button.setGraphic(pane);
            pane.setScaleX(0.0);
            pane.setScaleY(0.0);
            button.setOnMouseClicked(pane.getOnMouseClicked());
            container.getChildren().add(button);
            KeyValue value = new KeyValue(pane.scaleXProperty(), 1.0);
            KeyValue value1 = new KeyValue(pane.scaleYProperty(), 1.0);
            KeyFrame frame = new KeyFrame(new Duration(400), value, value1);
            Timeline anim = new Timeline(frame);
            anim.setCycleCount(1);
            anim.play();
        }
    }

    private GridPane fillHotelItem(Hotel hotel) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 4;");

        Image value = null;
        if (hotel.getPhotos().size() > 0)
            try {
                value = loadImage(hotel.getPhotos().get(0));
            } catch (IOException ignored) {
            }

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + hotel.getName() + "\nPrice: " + hotel.getStartingPrice() + "$ - " + hotel.getEndingPrice() + "$\nLocation: " + hotel.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < hotel.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.hotel = hotel;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/HotelWindow.fxml"));
                    Scene scene = new Scene(parent, 1280, 720);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                }
            }
        });
        return item;
    }

    private GridPane fillRestaurantItem(Restaurant restaurant) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 4;");

        Image value = null;
        try {
            value = loadImage(restaurant.getPhotos().get(0));
        } catch (IOException ignored) {
        }

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + restaurant.getName() + "\nType: " + restaurant.getType().get(0) + "\t Number of seats: " + restaurant.getNumberOfSeats() + "\nLocation: " + restaurant.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < restaurant.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.restaurant = restaurant;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/RestaurantWindow.fxml"));
                    Scene scene = new Scene(parent, 1280, 720);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return item;
    }

    private GridPane fillThingsToDoItem(ThingsToDo thingsToDo) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 4;");

        Image value = null;
        try {
            value = loadImage(thingsToDo.getPhotos().get(0));
        } catch (IOException ignored) {
        }

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + thingsToDo.getName() + "\nPrice: " + thingsToDo.getPrice() + " $\t" + "\nLocation: " + thingsToDo.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < thingsToDo.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.thingsToDo = thingsToDo;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/ThingsToDoWindow.fxml"));
                    Scene scene = new Scene(parent, 1280, 720);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return item;
    }

    private GridPane fillEntertainingItem(Entertaining entertaining) {
        GridPane item = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints();
        item.getColumnConstraints().addAll(col1, col2, col3);
        item.setHgap(10);
        item.setStyle("-fx-padding: 10; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 4;");

        Image value = null;
        try {
            value = loadImage(entertaining.getPhotos().get(0));
        } catch (IOException ignored) {
        }

        ImageView image = new ImageView(value);
        Circle circle = new Circle(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);
        image.setClip(circle);
        item.add(image, 0, 0);

        Label info = new Label("Name: " + entertaining.getName() + "\nPrice: " + entertaining.getPrice() + " $\t" + "\nLocation: " + entertaining.getLocation());
        info.setStyle("-fx-font-size: 24; -fx-alignment: center-left;");
        item.add(info, 1, 0);

        HBox ratingBox = new HBox(5.0);
        ratingBox.setStyle("-fx-alignment: center;");

        int maxRate = 5;
        for (int i = 0; i < entertaining.getRating(); i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.filledStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
            maxRate--;
        }
        for (int i = 0; i < maxRate; i++) {
            Button star = new Button();
            star.setPrefSize(40.0, 40.0);
            star.setStyle("-fx-shape: " + Main.emptyStar);
            star.setDisable(true);
            ratingBox.getChildren().add(star);
        }
        item.add(ratingBox, 2, 0);

        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.entertaining = entertaining;
                    Parent parent = FXMLLoader.load(getClass().getResource("../FXML/EntertainmentWindow.fxml"));
                    Scene scene = new Scene(parent, 1280, 720);
                    Main.stage.hide();
                    Main.stage.setScene(scene);
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return item;
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

    private Image loadImage(String path) throws IOException {
        Socket socket = new Socket(Main.serverHost, 2332);
        BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
        byte[] query = ("I" + path).getBytes();
        wr.write(query, 0, query.length);
        wr.close();

        ServerSocket serverSocket = new ServerSocket(2333);
        Socket accept = serverSocket.accept();

        BufferedInputStream stream = new BufferedInputStream(accept.getInputStream());

        File file = File.createTempFile("temp", ".nxtp");
        FileOutputStream fil2 = new FileOutputStream(file);

        byte[] buf = new byte[1024];
        int read;

        while ((read = stream.read(buf)) != -1)
            fil2.write(buf, 0, read);

        Image image = new Image(String.valueOf(file.toURI().toURL()), 100.0, 100.0, false, true);

        fil2.close();
        file.deleteOnExit();
        stream.close();
        accept.close();
        serverSocket.close();

        return image;
    }
}