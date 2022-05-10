package home.controllers;

import home.OO.Match;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CalendrierDAO implements Initializable {



    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<?> datecombo;

    @FXML
    private ComboBox<String> equipecombo;

    @FXML
    private TableView<?> table;








    @FXML
    public void Retourner(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Dashboard.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Ajouter(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/C-Ajouter.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void Rechercher(javafx.event.ActionEvent actionEvent) {
    }


    @FXML
    void Afficher(javafx.event.ActionEvent actionEvent) {

    }

    public static int insert(Match m) {
        int res = 0;

        String req ="insert into calendrier values(null , ? ,? ,? ,?);";
        Date date = Convert_date.utilDate_to_sqlDate(m.getDate());
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, m.getEquipe1());
            statement.setInt(2, m.getEquipe2());
            statement.setString(3, m.getLieu());
            statement.setDate(4, date);
            res = statement.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    public static int update(Match m) {
        int res = 0;
        String req = "update calendrier set equipe1 = ? , equipe2 = ? , lieu = ? , date = ? where id = ?";
        Date date = Convert_date.utilDate_to_sqlDate(m.getDate());
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, m.getEquipe1());
            statement.setInt(2, m.getEquipe2());
            statement.setString(3, m.getLieu());
            statement.setDate(4, date);
            statement.setInt(5, m.getId());
            res = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        return 0;
    }
    @FXML
    public static int delete (int id) {
        int res = 0;
        String req = "delete from calendrier where id = ?";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, id);
            res = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        return res;
    }

    public static ResultSet getAll() {
        ResultSet res = null;
        String req = "select * from calendrier order by Date";
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
        String req = "select * from calendrier where id = ? order by Date";
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



    // advanced selection


    public static ResultSet getAllWithDate(java.util.Date date) {
        ResultSet res = null;
        Date date2 = Convert_date.utilDate_to_sqlDate(date);
        String req = "select * from calendrier  where date = ? order by Date";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setDate(1, date2);
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static ResultSet getAllWithDateAndEquipe(Integer id , java.util.Date date) {
        ResultSet res = null;
        Date date2 = Convert_date.utilDate_to_sqlDate(date);
        String req = "select * from calendrier  where date = ? and equipe1 = ? or equipe2 = ? order by Date";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setDate(1, date2);
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static ResultSet getAllWithEquipe(int id) {
        ResultSet res = null;
        String req = "select * from calendrier  where equipe1 = ? or equipe2 = ? order by Date";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, id);
            statement.setInt(2, id);
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static ResultSet getAllDate() {
        ResultSet res = null;
        String req = "select date from calendrier order by Date";
        try {
            Statement statement = MyConnection.conn.createStatement();
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }









    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
