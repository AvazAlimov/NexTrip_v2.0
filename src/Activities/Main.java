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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
    private String serverHost = "192.168.17.237";
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
}