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

        Group group = new Group();
        GroupDAO groupDAO = new GroupDAO();

        group.setId(groupName.getText());

        String currentType = groupType(getCurrentGroup().getType());
        group.setType(currentType);

        group.setParent(getCurrentGroup());

        try {
            groupDAO.save(group);
        } catch (SQLException e) {
            System.out.println("Impossible d'insérer le groupe "+group.getId());
        }
    }

    @Override
    public void init() {

    }

    public String groupType(String parentType){
        if(parentType.equals("PROMOTION")){
            return "TD";
        }
        else if(parentType.equals("TD")){
            return "TP";
        }
        return "PROMOTION";
    }
}
