package home.controllers;

import home.OO.Joueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class DemandeDAO implements Initializable {

    static int nonValider(Joueur j) {
        int res = 0;

        String req = "delete from demande where nom =? and prenom = ? and equipe = ?";
        Date date = Convert_date.utilDate_to_sqlDate(j.getDate_naissance());
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setString(1, j.getNom());
            statement.setString(2, j.getPrenom());
            statement.setInt(3, j.getEquipe());
            res = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;

    }

    public static ResultSet getAll() {
        ResultSet res = null;
        String req = "select * from demande ";
        try {
            Statement statement = MyConnection.conn.createStatement();
            res = statement.executeQuery(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;

    }


    public static int valider(Joueur j) {
        int res = 0;
        JoueurDAO.insert(j);
        res = nonValider(j);
        return res;
    }


    @FXML
    private Button btnRefuser;

    @FXML
    private Button btnRetourner;

    @FXML
    private Button btnValider;

    @FXML
    private TableView<Joueur> table;
    @FXML
    private TableColumn<Joueur, String> certif;

    @FXML
    private TableColumn<Joueur, java.util.Date> date;

    @FXML
    private TableColumn<Joueur, Integer> equipe;

    @FXML
    private TableColumn<Joueur, Integer> id;

    @FXML
    private TableColumn<Joueur, String> nom;

    @FXML
    private TableColumn<Joueur, String> post;

    @FXML
    private TableColumn<Joueur, String> prenom;


    @FXML
    void valider(javafx.event.ActionEvent actionEvent) {
        Joueur j = table.getSelectionModel().getSelectedItem();
        int res = valider(j);


    }

    @FXML
    void nonValider(javafx.event.ActionEvent actionEvent) {
        Joueur j = table.getSelectionModel().getSelectedItem();
        int res = nonValider(j);
        System.out.println("ok");


    }

    @FXML
    void Retourner(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Dashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<Joueur, java.util.Date>("date"));
        post.setCellValueFactory(new PropertyValueFactory<>("post"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        certif.setCellValueFactory(new PropertyValueFactory<>("certif"));
        table.getColumns().clear();
        table.getColumns().addAll(id, nom, prenom, date, post, equipe, certif);

        ObservableList<Joueur>data = FXCollections.observableArrayList();
        ResultSet res = getAll();
        boolean valide;
        try {
            while (res.next()){
                int id = res.getInt("id");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String poste = res.getString("post");
                int equipe = res.getInt("equipe");
                java.util.Date date = Convert_date.sqlDate_to_utilDate(res.getDate("date_naissance"));
                int certif = res.getInt("certif");
                if (certif == 1){
                    valide = true;

                }
                else {
                    valide = false;
                }
                data.add(new Joueur(id,nom,prenom,poste, date, equipe,valide));

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        table.setItems(data);

   }
    }







