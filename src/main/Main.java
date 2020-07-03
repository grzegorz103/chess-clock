package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Chess timer");
        primaryStage.setScene(new Scene(root, 700, 405));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void reset() {
        Main.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main/view.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Main.stage = stage;
    }

}
