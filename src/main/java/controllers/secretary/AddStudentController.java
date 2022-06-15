package controllers.secretary;

import dao.GroupDAO;
import dao.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Group;
import models.Student;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddStudentController extends SecretaryController implements Initializable{
    @FXML
    private Button cancelButton, confirmButton;

    @FXML
    private TextField idField,nicknameField,nameField,passwordField;

    @FXML
    private TextArea descField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> promotionComboBox,TDComboBox,TPComboBox;

    @FXML
    private ImageView image;

    @FXML
    private Label errorMessage;

    public void onCancelClicked(ActionEvent actionEvent) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onConfirmClicked(ActionEvent actionEvent) throws IOException {

        if(!idField.getText().isEmpty() && !nicknameField.getText().isEmpty() && !nameField.getText().isEmpty() && datePicker.getValue() != null && !descField.getText().isEmpty() && promotionComboBox.getSelectionModel().getSelectedItem() != null && TDComboBox.getSelectionModel().getSelectedItem() != null && TPComboBox.getSelectionModel().getSelectedItem() != null){
            Student student = new Student();
            StudentDAO studentDAO = new StudentDAO();

            student.setNum(idField.getText());
            student.setNickname(nicknameField.getText());
            student.setName(nameField.getText());

            java.util.Date date = java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            student.setBirthdate(sqlDate);

            Group TPgroup = new Group();
            TPgroup.setType("TP");
            TPgroup.setId(TPComboBox.getValue());
            Group TDgroup = new Group();
            TDgroup.setType("TD");
            TDgroup.setId(TDComboBox.getValue());
            Group promotion = new Group();
            promotion.setType("PROMOTION");
            promotion.setId(promotionComboBox.getValue());
            promotion.setParent(null);
            TDgroup.setParent(promotion);
            TPgroup.setParent(TDgroup);
            student.setPromotion(promotion);
            student.setTDGroup(TDgroup);
            student.setTPGroup(TPgroup);

            student.setDescription(descField.getText());

            student.setPhotoUrl(image.getImage().getUrl());

            student.setPassword(passwordField.getText());

            student.setRegistrationYear(Calendar.getInstance().get(Calendar.YEAR));

            student.setHasRepeated(false);

            student.setHasResign(false);

            try {
                studentDAO.save(student);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            errorMessage.setVisible(true);
        }
    }

    public void onImportAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        image.setImage(new Image(selectedFile.getPath()));
    }

    @Override
    public void init() {

        GroupDAO groupDAO = new GroupDAO();

        try {
            for(Group group : groupDAO.getAllPromotions()){
                promotionComboBox.getItems().add(group.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        promotionComboBox.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue observableValue, String oldValue, String newValue) {
                try {
                    TDComboBox.getItems().clear();
                    for(Group group : groupDAO.getById(promotionComboBox.getValue()).getChilrens()){
                        System.out.println(group.getId());
                        TDComboBox.getItems().add(group.getId());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TDComboBox.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue observableValue, String oldValue, String newValue) {
                try {
                    TPComboBox.getItems().clear();
                    for(Group group : groupDAO.getById(TDComboBox.getValue()).getChilrens()){
                        TPComboBox.getItems().add(group.getId());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
}
