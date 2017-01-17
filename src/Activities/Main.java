package Activities;

import au.com.bytecode.opencsv.CSVReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;

public class Main extends Application {
    static Stage stage;

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
        stage.hide();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unused")
    private static class Language {
        private static HashMap<String, String> currentTranslation;
        private static HashMap<String, HashMap<String, String>> translations;

        public static void init(Reader stream) throws IOException {
            // open a CSV reader
            CSVReader reader = new CSVReader(stream);

            // list of translation HashMaps
            translations = new HashMap<>();
            HashMap<Integer, String> indices = new HashMap<>();

            // set up columns (each language HashMap)
            String[] next = reader.readNext();
            if (next != null) {
                for (int n = 1; n < next.length; n++) {
                    translations.put(next[n], new HashMap<>());
                    indices.put(n, next[n]);
                }
            }

            // add definitions (cell values into proper translation HashMaps)
            while ((next = reader.readNext()) != null) {
                for (int n = 1; n < next.length; n++)
                    translations.get(indices.get(n)).put(next[0], next[n]);
            }

            // close the CSV reader
            reader.close();
        }

        public static void setLanguage(String languageName) throws NullPointerException {
            if (!translations.containsKey(languageName))
                throw new NullPointerException(String.format("Language %s not found!", languageName));

            currentTranslation = translations.get(languageName);
        }

        public static String getTranslation(String key) throws NullPointerException {
            if (currentTranslation == null)
                throw new NullPointerException("Please, call setLanguage(String) before getTranslation()!");
            return currentTranslation.get(key);
        }
    }
}