package br.ifsp.edu.lp1p2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/main.fxml"));
        Screen screen = Screen.getPrimary();
        Scene scene = new Scene(fxmlLoader.load(),screen.getBounds().getWidth()-100 , screen.getBounds().getHeight()-100);
        stage.setTitle("Programming Language - Project 2 !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}