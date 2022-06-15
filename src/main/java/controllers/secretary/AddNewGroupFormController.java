package controllers.secretary;

import dao.GroupDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Group;

import java.io.IOException;
import java.sql.SQLException;

public class AddNewGroupFormController extends SecretaryController{

    @FXML
    private Button cancelButton, confirmButton;

    @FXML
    private TextField groupName;

    public void onCancelClicked(ActionEvent actionEvent) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onConfirmClicked(ActionEvent actionEvent) throws IOException {

        GroupDAO groupDAO = new GroupDAO();
        Group group = new Group();

        group.setType(getCurrentGroup().getType());

        group.setId(groupName.getText());

        if(getCurrentGroup() != null)
        {
            group.setParent(getCurrentGroup().getParent());
        }

        {
            group.setParent(null);
        }

        try
        {
            groupDAO.save(group);
        } catch (SQLException e)
        {
            System.out.println("Impossible d'insérer le groupe "+group.getId());
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void init() {

    }
}
