package home.controllers;

import home.OO.Resultat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultatDAO {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TextField eq1txtf;

    @FXML
    private TextField eq2txtf;

    @FXML
    private TextField s1txtf;

    @FXML
    private TextField s2txtf;




    public static int insert(Resultat m) {
        int res = 0;

        String req ="insert into resultat values(null, ? ,? ,? ,?);";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, m.getIdMatch());
            statement.setInt(2, m.getScore1());
            statement.setInt(3, m.getScore2());
            statement.setString(4, m.getDetails());
            res = statement.executeUpdate();
            ClassementDAO.update(m);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }



    public static int delete (Resultat m) {
        int res = 0;
        String req = "delete from resultat where id_match = ?";
        try {
            ClassementDAO.delete(m);
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, m.getIdMatch());
            res = statement.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        return res;
    }

    public static ResultSet getAll() {
        ResultSet res = null;
        String req = "select * from resultat";
        try {
            Statement statement = MyConnection.conn.createStatement();
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static ResultSet getById(int id) {
        ResultSet res = null;
        String req = "select * from resultat where id_match = ?";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, id);
            res = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }





    @FXML
    void Ajouter(javafx.event.ActionEvent actionEvent) {

    }

    @FXML
    void Retourner(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Dashboard.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Supprimer(javafx.event.ActionEvent actionEvent) {

    }

}
