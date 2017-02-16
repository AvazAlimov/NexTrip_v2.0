package Activities;

import Classes.Entertaining;
import Classes.Hotel;
import Classes.Restaurant;
import Classes.ThingsToDo;
import au.com.bytecode.opencsv.CSVReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {
    static ArrayList<Hotel>hotels = new ArrayList<>();
    static ArrayList<Restaurant> restaurants = new ArrayList<>();
    static ArrayList<Entertaining> entertainings = new ArrayList<>();
    static ArrayList<ThingsToDo> thingsToDos = new ArrayList<>();
    static Stage stage;
    private String serverHost = "127.0.0.1";
    static int count=0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/StartWindow.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Resources/icon.png"));
        primaryStage.show();

        ExecutorService service = Executors.newCachedThreadPool();
        Runnable runnable = () -> {
            try {
                loadHotels();
                loadRestaurants();
                loadEntertaining();
                loadThingsToDo();
                System.out.println("Data were downloaded");
                service.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        service.submit(runnable);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    static void closeWindow() {
        stage.hide();
    }

    public static void main(String[] args) {
        try {
            Language.init(new BufferedReader(new FileReader("src/Translations.csv")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Language.setLanguage("english");

        launch(args);
    }

    @SuppressWarnings("unused")
    static class Language {
        private static HashMap<String, String> currentTranslation;
        private static HashMap<String, HashMap<String, String>> translations;

        static void init(Reader stream) throws IOException {
            CSVReader reader = new CSVReader(stream);

            translations = new HashMap<>();
            HashMap<Integer, String> indices = new HashMap<>();

            String[] next = reader.readNext();
            if (next != null) {
                for (int n = 1; n < next.length; n++) {
                    translations.put(next[n], new HashMap<>());
                    indices.put(n, next[n]);
                }
            }

            while ((next = reader.readNext()) != null) {
                for (int n = 1; n < next.length; n++)
                    translations.get(indices.get(n)).put(next[0], next[n]);
            }

            reader.close();
        }

        static void setLanguage(String languageName) throws NullPointerException {
            if (!translations.containsKey(languageName))
                throw new NullPointerException(String.format("Language %s not found!", languageName));

            currentTranslation = translations.get(languageName);
        }

        static String getTranslation(String key) throws NullPointerException {
            if (currentTranslation == null)
                throw new NullPointerException("Please, call setLanguage(String) before getTranslation()!");
            return currentTranslation.get(key);
        }
    }

    private void loadHotels() throws IOException {
        try {
            Socket socket = new Socket(serverHost, 2332);
            BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
            byte[] query = "H".getBytes();
            wr.write(query, 0, query.length);
            wr.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServerSocket serverSocket = new ServerSocket(2333);
        Socket socket = serverSocket.accept();
        System.out.println("Data received");
        BufferedInputStream stream = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int read;

        while ((read = stream.read(buf)) != -1)
            outputStream.write(buf, 0, read);
        String data = outputStream.toString();
        int index = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '◍') {
                String content = data.substring(index, i);
                hotels.add(new Hotel(content));
                index = i + 1;
            }
        }

        outputStream.close();
        stream.close();
        serverSocket.close();
    }

    private void loadRestaurants() throws IOException {
        try {
            Socket socket = new Socket(serverHost, 2332);
            BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
            byte[] query = "R".getBytes();
            wr.write(query, 0, query.length);
            wr.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServerSocket serverSocket = new ServerSocket(2333);
        Socket socket = serverSocket.accept();

        BufferedInputStream stream = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int read;

        while ((read = stream.read(buf)) != -1)
            outputStream.write(buf, 0, read);
        String data = outputStream.toString();
        int index = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '◍') {
                String content = data.substring(index, i);
                restaurants.add(new Restaurant(content));
                index = i + 1;
            }
        }

        outputStream.close();
        stream.close();
        serverSocket.close();
    }

    private void loadEntertaining() throws IOException {
        try {
            Socket socket = new Socket(serverHost, 2332);
            BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
            byte[] query = "E".getBytes();
            wr.write(query, 0, query.length);
            wr.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServerSocket serverSocket = new ServerSocket(2333);
        Socket socket = serverSocket.accept();

        BufferedInputStream stream = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int read;

        while ((read = stream.read(buf)) != -1)
            outputStream.write(buf, 0, read);
        String data = outputStream.toString();
        int index = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '◍') {
                String content = data.substring(index, i);
                entertainings.add(new Entertaining(content));
                index = i + 1;
            }
        }

        outputStream.close();
        stream.close();
        serverSocket.close();
    }

    private void loadThingsToDo() throws IOException {
        try {
            Socket socket = new Socket(serverHost, 2332);
            BufferedOutputStream wr = new BufferedOutputStream(socket.getOutputStream());
            byte[] query = "T".getBytes();
            wr.write(query, 0, query.length);
            wr.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServerSocket serverSocket = new ServerSocket(2333);
        Socket socket = serverSocket.accept();

        BufferedInputStream stream = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int read;

        while ((read = stream.read(buf)) != -1)
            outputStream.write(buf, 0, read);
        String data = outputStream.toString();
        int index = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '◍') {
                String content = data.substring(index, i);
                thingsToDos.add(new ThingsToDo(content));
                index = i + 1;
            }
        }

        outputStream.close();
        stream.close();
        serverSocket.close();
    }

    //region Tools
    static String filledStar = "\"M121.215,44.212l-34.899-3.3c-2.2-0.2-4.101-1.6-5-3.7l-12.5-30.3c-2-5-9.101-5-11.101,0l-12.4,30.3 c-0.8,2.1-2.8,3.5-5,3.7l-34.9,3.3c-5.2,0.5-7.3,7-3.4,10.5l26.3,23.1c1.7,1.5,2.4,3.7,1.9,5.9l-7.9,32.399 c-1.2,5.101,4.3,9.3,8.9,6.601l29.1-17.101c1.9-1.1,4.2-1.1,6.1,0l29.101,17.101c4.6,2.699,10.1-1.4,8.899-6.601l-7.8-32.399 c-0.5-2.2,0.2-4.4,1.9-5.9l26.3-23.1C128.615,51.212,126.415,44.712,121.215,44.212z\"";
    static String emptyStar = "\"M453.443 160.214l-119.7-8.6c-2.5-0.2-4.7-1.7-5.6-4.1l-45.6-111c-6.3-15.3-20.5-24.8-37.1-24.8s-30.7 9.5-37.1 24.9   l-45.7 111.1c-1 2.3-3.1 3.9-5.6 4.1l-119.5 8.6c-16.2 0.9-30 11.5-35.3 27.1c-5.4 15.8-0.9 32.4 11.9 43.3l92.5 76.4   c1.9 1.6 2.8 4.2 2.2 6.6l-28.2 115.4c-3.3 12-1 24.4 6.3 34.1c7.5 9.8 19.2 15.7 31.5 15.7c7.6 0 15-2.3 21.1-6.4l101.1-62.9   c2.1-1.3 4.8-1.3 6.9 0l102.1 62.7c6.4 4.3 13.8 6.6 21.3 6.6c11.5 0 22.9-5.5 30.3-14.8c7.7-9.6 10.5-22.1 7.7-34.6l-28.3-115.7   c-0.6-2.5 0.3-5.1 2.2-6.7l93.9-76.6c12.6-10.8 17.1-27.4 11.8-43.2C483.343 171.814 469.443 161.114 453.443 160.214z    M461.143 211.714l-93.7 76.5c-9.2 7.5-13.4 19.9-10.5 31.5l28.3 115.5c1.5 6.8-1.2 11.5-3 13.7c-2.8 3.5-7 5.7-11.2 5.7   c-2.6 0-5.2-0.8-8-2.7l-102.5-63c-4.9-3-10.5-4.6-16.3-4.6s-11.5 1.6-16.4 4.7l-101.5 63.1c-6.5 4.3-15.2 2.4-19.8-3.6   c-1.9-2.5-3.9-6.8-2.1-13.1l28.3-115.8c2.8-11.5-1.3-23.8-10.4-31.3l-92.3-76.3c-7.3-6.3-5.3-14.4-4.5-16.7s4.1-10 13.7-10.5   l119.7-8.6c11.8-0.9 22-8.2 26.5-19.2l45.7-111.1c3.7-9 12-9.7 14.4-9.7c2.4 0 10.7 0.7 14.4 9.7l45.7 111.1   c4.5 11 14.7 18.3 26.5 19.2l119.9 8.7c9.4 0.5 12.7 8.2 13.5 10.5C466.143 197.514 468.143 205.614 461.143 211.714z\"";
    static Hotel hotel;
    static Restaurant restaurant;
    static ThingsToDo thingsToDo;
    static Entertaining entertaining;

    static boolean contains(String word, String part) {
        if (part.length() > word.length())
            return false;
        for (int i = 0; i < word.length(); i++) {
            if ((i + part.length() - 1) < word.length()) {
                boolean isPart = true;
                for (int j = 0; j < part.length(); j++)
                    if (word.charAt(i + j) != part.charAt(j))
                        isPart = false;
                if (isPart)
                    return true;
            }
        }
        return false;
    }

    static ArrayList<Hotel> findHotel(String location) {
        ArrayList<Hotel> returnHotels = new ArrayList<>();
        for (Hotel hotel : hotels)
            if (contains(hotel.getLocation().toLowerCase(), location.toLowerCase()))
                returnHotels.add(hotel);
        return returnHotels;
    }

    static ArrayList<Restaurant> findRestaurants(String location) {
        ArrayList<Restaurant> returnRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants)
            if (contains(restaurant.getLocation().toLowerCase(), location.toLowerCase()))
                returnRestaurants.add(restaurant);
        return returnRestaurants;
    }

    static ArrayList<Entertaining> findEntertainings(String location){
        ArrayList<Entertaining> returnEntertainings = new ArrayList<>();
        for (Entertaining entertaining : entertainings)
            if (contains(entertaining.getLocation().toLowerCase(), location.toLowerCase()))
                returnEntertainings.add(entertaining);
        return returnEntertainings;
    }

    static ArrayList<ThingsToDo> findThingsToDo(String location, DatePicker startDate, DatePicker endDate) {
        ArrayList<ThingsToDo> returnThingsToDo = new ArrayList<>();
        for (ThingsToDo thingsToDo : thingsToDos) {
            DatePicker start = new DatePicker();
            start.setValue(LocalDate.of(thingsToDo.getStartDate().getYear(), thingsToDo.getStartDate().getMonth(), thingsToDo.getStartDate().getDay()));
            DatePicker end = new DatePicker();
            end.setValue(LocalDate.of(thingsToDo.getEndDate().getYear(), thingsToDo.getEndDate().getMonth(), thingsToDo.getEndDate().getDay()));

            if (contains(thingsToDo.getLocation().toLowerCase(), location.toLowerCase()) && start.getValue().isBefore(startDate.getValue()) || start.getValue().isEqual(startDate.getValue()) && (end.getValue().isAfter(endDate.getValue()) || end.getValue().isEqual(endDate.getValue())))
                returnThingsToDo.add(thingsToDo);
        }
        return returnThingsToDo;
    }

    static boolean checkDate(DatePicker startDate, DatePicker endDate, ThingsToDo thingsToDo) {
        DatePicker start = new DatePicker();
        start.setValue(LocalDate.of(thingsToDo.getStartDate().getYear(), thingsToDo.getStartDate().getMonth(), thingsToDo.getStartDate().getDay()));
        DatePicker end = new DatePicker();
        end.setValue(LocalDate.of(thingsToDo.getEndDate().getYear(), thingsToDo.getEndDate().getMonth(), thingsToDo.getEndDate().getDay()));

        return (start.getValue().isBefore(startDate.getValue()) || start.getValue().isEqual(startDate.getValue())) && (end.getValue().isAfter(endDate.getValue()) || end.getValue().isEqual(endDate.getValue()));
    }
    //endregion
}