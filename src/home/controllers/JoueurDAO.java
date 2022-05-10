package home.controllers;


import home.OO.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class JoueurDAO {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnRetourner;

    @FXML
    private TableView<?> table;




    public static ResultSet getAll() {
        ResultSet res = null;
        String req = "select * from joueur order by post";
        try {
            Statement st = MyConnection.conn.createStatement();
            res = st.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return res;
    }
    public static ResultSet getAllByEquipe() {
        ResultSet res = null;
        String req = "select * from joueur order by equipe";
        try {
            Statement st = MyConnection.conn.createStatement();
            res = st.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return res;
    }

    public static ResultSet getById(int id) {
        ResultSet res = null;
        String req = "select * from joueur where id = ?";

        try {
            PreparedStatement st = MyConnection.conn.prepareStatement(req);
            st.setInt(1, id);
            res = st.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }
    public static void insert(Joueur j) {
        String req = "insert into joueur values(null ,? , ? ,?, ? , ? , ? )";
        int aux = 0;
        if(j.isCertif()) {
            aux = 1;
        }

        Date date_naissance = Convert_date.utilDate_to_sqlDate(j.getDate_naissance());
        System.out.println(date_naissance);
        try {

            PreparedStatement st = MyConnection.conn.prepareStatement(req);

            st.setString(1, j.getNom());
            st.setString(2, j.getPrenom());
            st.setString(3, j.getPoste());
            st.setDate(4,date_naissance);
            st.setInt(5, aux);
            st.setInt(6, j.getEquipe());
            st.executeUpdate();
        }

        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void delete(int id) {
        String req = "delete from joueur where id = ?";
        try {
            PreparedStatement st = MyConnection.conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }




    @FXML
    void Ajouter(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/J-Ajouter.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}