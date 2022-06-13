package com.example.sae_gestion_etudiants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //On charge la vue principale
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/connection.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image(MainApplication.class.getResource("Images/icon.jpg").toExternalForm());
        stage.getIcons().add(icon);

        scene.getStylesheets().add(getClass().getResource("Style/style.css").toExternalForm());
        stage.setTitle("Gestion d'étudiants");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}