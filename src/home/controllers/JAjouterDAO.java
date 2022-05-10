package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class JAjouterDAO {

    @FXML
    private Button btnretour;

    @FXML
    private Button btnvalider;

    @FXML
    private TextField certiftxtf;

    @FXML
    private TextField ddntxtf;

    @FXML
    private TextField eqtxtf;

    @FXML
    private TextField nomtxtf;

    @FXML
    private TextField posttxtf;

    @FXML
    private TextField prenomtxtf;




    @FXML
    void Retourner(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Joueurs.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Valider(javafx.event.ActionEvent actionEvent) {

    }

}

