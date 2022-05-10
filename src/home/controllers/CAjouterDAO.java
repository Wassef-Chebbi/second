package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CAjouterDAO {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnreturn;

    @FXML
    private DatePicker datepick;

    @FXML
    private TextField eq1txtf;

    @FXML
    private TextField eq2txtf;

    @FXML
    private TextField lieutxtf;



    @FXML
    void Ajouter(ActionEvent event) {

    }

    @FXML
    void retourner(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home/fxml/Calendrier.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}