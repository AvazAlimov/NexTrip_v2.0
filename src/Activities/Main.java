package Activities;

import au.com.bytecode.opencsv.CSVReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class Main extends Application {
    static Stage stage;
    public static int count = 0;
    static  ImageView[] slides;
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
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    static void closeWindow() {
        stage.close();
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
}