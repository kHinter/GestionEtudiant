package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import controllers.secretary.SecretaryController;
import dao.GroupDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Group;
import models.Staff;

import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.GroupPrincipal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SecretaryHomeController extends SecretaryController implements Initializable
{
    @FXML
    private Text titleText;

    @FXML
    private ScrollPane groupListScrollPane;

    @FXML
    private Text secretaryNameText;

    public void onAddGroupClicked(MouseEvent mouseEvent) {
        //Changement vers la fenêtre d'ajout de groupe
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/addNewGroupForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nouveau Groupe");
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

    public void onAddStudentClicked(MouseEvent mouseEvent) {
        //Changement vers la fenêtre d'ajout de groupe
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/addStudent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nouvel Étudiant");
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

    @Override
    public void init()
    {
        Staff secretary = getConnectedStaff();
        secretaryNameText.setText(secretary.getPrenom() + secretary.getNom().toUpperCase());
        // Ajout des groupes d'étudiants sur la page d'accueil
        List<Group> groupList = null;
        GroupDAO groupDAO = new GroupDAO();

        if(getCurrentGroup() == null)
        {
            try
            {
                groupList = groupDAO.getAllPromotions();
            }
            catch (SQLException e)
            {
                System.out.println("Erreur lors d'une requête :" + e.getMessage());
            }
        }
        else if(getCurrentGroup().getType() == "PROMOTION")
        {
            titleText.setText("Sous-groupe " + getCurrentGroup().getId());
            groupList = getCurrentGroup().getChilrens();
        }
        else if(getCurrentGroup().getType() == "TD")
        {
            titleText.setText("Sous-groupe " + getCurrentGroup().getType() + getCurrentGroup().getId());
            groupList = getCurrentGroup().getChilrens();
        }

        for(Group group : groupList)
        {
            VBox groupFrame = new VBox();
            Insets insets = new Insets(5, 0, 0, 5);
            groupFrame.setPrefWidth(228);
            groupFrame.setPrefHeight(254);
            groupFrame.setBackground(new Background(new BackgroundFill(Color.valueOf("#808791"), new CornerRadii(15), Insets.EMPTY)));
            groupFrame.setSpacing(5);

            AnchorPane header = new AnchorPane();
            header.setPrefHeight(67);
            header.setPrefWidth(Region.USE_COMPUTED_SIZE);
            header.setBackground(new Background(new BackgroundFill(Color.valueOf("#E6007C"), new CornerRadii(15), Insets.EMPTY)));

            //Icone déroulage du menu
            ImageView arrowDownIcon = new ImageView(new Image(MainApplication.class.getResource("Images/arrow_down.png").toExternalForm()));
            arrowDownIcon.setFitHeight(34);
            arrowDownIcon.setFitWidth(29);
            arrowDownIcon.setLayoutX(14);
            arrowDownIcon.setLayoutY(22);

            TextFlow textFlow = new TextFlow();
            textFlow.setTextAlignment(TextAlignment.CENTER);
            textFlow.setPrefWidth(66);
            textFlow.setPrefHeight(16);

            textFlow.setLayoutX(81);
            textFlow.setLayoutY(22);

            Text groupName = new Text();
            groupName.setFill(Color.WHITE);
            groupName.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18));

            if(getCurrentGroup() == null)
            {
                groupName.setText(group.getId());
            }
            else
            {
                groupName.setText(group.getType() + group.getId());
            }

            textFlow.getChildren().add(groupName);

            //Icone de la poubelle (suppression du groupe)
            ImageView binIcon = new ImageView(new Image(MainApplication.class.getResource("Images/bin_icon.png").toExternalForm()));
            binIcon.setFitWidth(29);
            binIcon.setFitHeight(29);
            binIcon.setLayoutX(185);
            binIcon.setLayoutY(19);

            header.getChildren().add(arrowDownIcon);
            header.getChildren().add(textFlow);
            header.getChildren().add(binIcon);

            header.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {

                }
            });

            groupFrame.getChildren().add(header);

            VBox buttonsFrame = new VBox();
            buttonsFrame.setSpacing(4);
            VBox.setMargin(buttonsFrame, new Insets(0, 5, 0, 5));

            //Bouton qui redirige vers le trombinoscope
            HBox trombinoscopeHBox = new HBox();
            trombinoscopeHBox.setSpacing(15);
            trombinoscopeHBox.setPrefWidth(218);
            trombinoscopeHBox.setPrefHeight(55);
            trombinoscopeHBox.setAlignment(Pos.CENTER_LEFT);

            //Icone du bouton
            ImageView trombinoscopeIcon = new ImageView(new Image(MainApplication.class.getResource("Images/photo_icon.png").toExternalForm()));
            HBox.setMargin(trombinoscopeIcon, new Insets(0, 0, 0, 8));
            trombinoscopeIcon.setFitWidth(38);
            trombinoscopeIcon.setFitHeight(38);

            //Texte du bouton
            Label trombinoscopeLabel = new Label("Trombinoscope");
            trombinoscopeLabel.setTextFill(Color.WHITE);
            trombinoscopeLabel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));

            trombinoscopeHBox.getChildren().add(trombinoscopeIcon);
            trombinoscopeHBox.getChildren().add(trombinoscopeLabel);
            trombinoscopeHBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#263A7A"), new CornerRadii(15), Insets.EMPTY)));

            buttonsFrame.getChildren().add(trombinoscopeHBox);

            //Bouton qui redirige vers la liste des étudiants
            HBox studentListHBox = new HBox();
            studentListHBox.setAlignment(Pos.CENTER_LEFT);
            studentListHBox.setSpacing(15);
            studentListHBox.setPrefWidth(218);
            studentListHBox.setPrefHeight(55);

            //Icone du bouton
            ImageView studentListIcon = new ImageView(new Image(MainApplication.class.getResource("Images/multiple_users.png").toExternalForm()));
            HBox.setMargin(studentListIcon, new Insets(0, 0, 0, 8));
            studentListIcon.setFitWidth(38);
            studentListIcon.setFitHeight(38);

            //Texte du bouton
            Label studentListLabel = new Label("Liste des étudiants");
            studentListLabel.setTextFill(Color.WHITE);
            studentListLabel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));

            studentListHBox.getChildren().add(studentListIcon);
            studentListHBox.getChildren().add(studentListLabel);

            studentListHBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#263A7A"), new CornerRadii(15), Insets.EMPTY)));

            buttonsFrame.getChildren().add(studentListHBox);

            //Bouton qui redirige vers les sous-groupes
            if(group.getType() != "TP")
            {
                HBox subgroupHBox = new HBox();
                subgroupHBox.setAlignment(Pos.CENTER_LEFT);
                subgroupHBox.setSpacing(15);
                subgroupHBox.setPrefWidth(218);
                subgroupHBox.setPrefHeight(55);

                //Icone du bouton
                ImageView subgroupIcon = new ImageView(new Image(MainApplication.class.getResource("Images/hierarchy.png").toExternalForm()));
                HBox.setMargin(subgroupIcon, new Insets(0, 0, 0, 8));
                subgroupIcon.setFitWidth(38);
                subgroupIcon.setFitHeight(38);

                //Texte du bouton
                Label subgroupLabel = new Label("Voir sous-groupes");
                subgroupLabel.setTextFill(Color.WHITE);
                subgroupLabel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));

                subgroupHBox.getChildren().add(subgroupIcon);
                subgroupHBox.getChildren().add(subgroupLabel);

                subgroupHBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#263A7A"), new CornerRadii(15), Insets.EMPTY)));

                buttonsFrame.getChildren().add(subgroupHBox);
            }

            groupFrame.getChildren().add(buttonsFrame);

            //Ajout du groupe à l'interface
            ((HBox) groupListScrollPane.getContent()).getChildren().add(groupFrame);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        groupListScrollPane.setContent(hbox);
    }
}
