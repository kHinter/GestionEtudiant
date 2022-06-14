package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Group;
import models.Staff;
import models.Student;

import java.io.IOException;

public abstract class SecretaryController {

    private Staff connectedStaff;
    private Group currentGroup;

    public Group getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }

    public void setConnectedStaff(Staff staff)
    {
        this.connectedStaff = staff;
    }

    public Staff getConnectedStaff()
    {
        return this.connectedStaff;
    }

    public abstract void init();

    @FXML
    protected void onDeconnectionClicked(MouseEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/deconnection.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Déconnexion");
        stage.initModality(Modality.APPLICATION_MODAL);

        try
        {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        stage.show();
    }

}
