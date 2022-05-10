package home.controllers;

import home.OO.Classement;
import home.OO.Resultat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassementDAO implements Initializable {


    @FXML
     void ruturnbuttonClicked(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Dashboard.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static int insert(int id_equipe) {
        System.out.println(id_equipe);
        int res = 0;
        String req = "insert into classement values(null ,? , 0 , 0 , 0, 0 , 0 , 0)";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, id_equipe);
            res = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static int delete(int id_equipe) {
        int res = 0;
        String req = "delete from classement where id_equipe = ?";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, id_equipe);
            res = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    public static void updateClassement(Classement c) {
        //System.err.println(c);
        String req = "update classement set match_joue = ? , gagnant = ? , perdu = ? , egalite = ?, buts = ? , points = ? where id_equipe = ?";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            statement.setInt(1, c.getMatch_joue());
            statement.setInt(2, c.getGagnant());
            statement.setInt(3, c.getPerdu());
            statement.setInt(4, c.getEgalite());
            statement.setInt(5, c.getButs());
            statement.setInt(6, c.getPoints());
            statement.setInt(7, c.getId_equipe());
            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ResultSet getAll() {
        ResultSet res = null;
        String req = "select * from classement order by points desc ";
        try {
            PreparedStatement statement = MyConnection.conn.prepareStatement(req);
            res = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public static ResultSet getById(int id) {
        ResultSet res = null;
        String req = "select * from classement where id_equipe = ?";
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

    public static int[] getInformation(Resultat res) {
        int equipe1 = 0 , equipe2 = 0 , score1 = 0 , score2 = 0 ,idmatch = 0 ;
        int [] tab= new int[5] ;
        idmatch = res.getIdMatch();
        ResultSet result = CalendrierDAO.getById(idmatch);
        ResultSet result1 = CalendrierDAO.getById(idmatch);
        try {

            while(result.next()) {
                equipe1 = result.getInt("equipe1");
                equipe2 = result.getInt("equipe2");
            }



            while(result1.next()) {
                score1 = result1.getInt("score1");
                score2 = result1.getInt("score2");
            }

            tab[0] = idmatch;
            tab[1] = equipe1;
            tab[2] = score1;
            tab[3] = equipe2;
            tab[4] = score2;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tab;
    }

    public static int[] getClassement(int id) {
        int [] tab= new int[6];
        ResultSet classement_equipe1 = ClassementDAO.getById(id);

        try {
            while(classement_equipe1.next()) {
                tab[0] = classement_equipe1.getInt("match_joue");
                tab[1]  = classement_equipe1.getInt("gagnant");
                tab[2] = classement_equipe1.getInt("perdu");
                tab[3] = classement_equipe1.getInt("egalite");
                tab[4] = classement_equipe1.getInt("buts");
                tab[5] = classement_equipe1.getInt("points");

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tab;
    }


    public static void update(Resultat res) {
        int[] cl1 = new int[6] , cl2 = new int[6];
        int [] tab= new int[6];
        tab = ClassementDAO.getInformation(res);

        int idmatch = tab[0];
        int equipe1 = tab[1];
        int score1 = tab[2];
        int equipe2 = tab[3];
        int score2 = tab[4];
        cl1 = getClassement(equipe1);
        cl2 = getClassement(equipe2);

        int match_joue1 = cl1[0] , gagnant1 = cl1[1] , perdu1 = cl1[2] , egalite1 = cl1[3] , buts1 = cl1[4] , points1= cl1[5];
        int match_joue2 = cl2[0] , gagnant2 = cl2[1]  , perdu2 = cl2[2] , egalite2 = cl2[3] , buts2 = cl2[4] , points2 = cl2[5];


        match_joue1 +=1;
        match_joue2+=1;
        if(score1 > score2) {
            gagnant1 +=1;
            buts1+=score1;
            buts2+=score2;
            points1 +=3 ;
            perdu2+=1;

        }
        else if(score1<score2) {
            gagnant2 +=1;
            buts2+=score1;
            buts2+=score2;
            points2 +=3 ;
            perdu1+=1;
        }
        else {
            buts2+=score1;
            buts2+=score2;
            egalite1+=1;
            egalite2+=1;
            points2 +=1 ;
            points1 +=1 ;

        }

        Classement clas1 = new Classement(equipe1, match_joue1, gagnant1, perdu1, egalite1, buts1, points1);
        ClassementDAO.updateClassement(clas1);

        Classement clas2 = new Classement(equipe2, match_joue2, gagnant2, perdu2, egalite2, buts2, points2);
        ClassementDAO.updateClassement(clas2);


    }

    public static void delete(Resultat res) {
        int[] cl1 = new int[6] , cl2 = new int[6];
        int [] tab= new int[6];
        tab = ClassementDAO.getInformation(res);


        int idmatch = tab[0];
        int equipe1 = tab[1];
        int score1 = tab[2];
        int equipe2 = tab[3];
        int score2 = tab[4];
        cl1 = getClassement(equipe1);
        cl2 = getClassement(equipe2);

        int match_joue1 = cl1[0] , gagnant1 = cl1[1] , perdu1 = cl1[2] , egalite1 = cl1[3] , buts1 = cl1[4] , points1= cl1[5];
        int match_joue2 = cl2[0] , gagnant2 = cl2[1]  , perdu2 = cl2[2] , egalite2 = cl2[3] , buts2 = cl2[4] , points2 = cl2[5];



        match_joue1 -=1;
        match_joue2-=1;
        if(score1 > score2) {
            gagnant1 -=1;
            buts1-=score1;
            buts2-=score2;
            points1 -=3 ;
            perdu2-=1;

        }
        else if(score1<score2) {
            gagnant2 -=1;
            buts2-=score1;
            buts2-=score2;
            points2 -=3 ;
            perdu1-=1;
        }
        else {
            buts2-=score1;
            buts2-=score2;
            egalite1-=1;
            egalite2-=1;
            points2 -=1 ;
            points1 -=1 ;

        }

        Classement clas1 = new Classement(equipe1, match_joue1, gagnant1, perdu1, egalite1, buts1, points1);
        ClassementDAO.updateClassement(clas1);
        Classement clas2 = new Classement(equipe2, match_joue2, gagnant2, perdu2, egalite2, buts2, points2);
        ClassementDAO.updateClassement(clas2);
    }























    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
