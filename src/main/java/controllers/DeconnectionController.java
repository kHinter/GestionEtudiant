package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DeconnectionController {

    @FXML
    private Button cancelButton, confirmButton;

    public void onCancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onConfirmClicked(ActionEvent actionEvent) throws IOException {

        closeLastView();

        Stage stage = (Stage) confirmButton.getScene().getWindow();

        FXMLLoader fxmlConnection = new FXMLLoader(MainApplication.class.getResource("views/connection.fxml"));
        Scene scene = new Scene(fxmlConnection.load());
        stage.setScene(scene);
        stage.show();

    }

    public void closeLastView() {
        ObservableList<Stage> stages = FXCollections.observableArrayList();
        Window.getWindows().forEach(w -> {
            if (w instanceof Stage) stages.add((Stage) w);
        });
        stages.get(0).close();
    }

}
