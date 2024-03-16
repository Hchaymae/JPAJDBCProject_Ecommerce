package org.structure.dao.impl;

import org.structure.dao.LigneCommandeDAO;
import org.structure.model.Article;
import org.structure.model.Commande;
import org.structure.model.LigneCommande;
import org.structure.model.StatusCommande;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDAOImpl implements LigneCommandeDAO {
    @Override
    public int addLigneCommande(LigneCommande ligneCommande) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        String sql = "INSERT INTO lignecommande (numcommande,codearticle,qteCde) VALUES(?, ?,?)";
        String query = "UPDATE article SET stock = stock - ? WHERE codearticle=?";
        String checkStockQuery = "SELECT stock FROM article WHERE codearticle=?";
        PreparedStatement statement = null;
        int result = 0;
        int rowsAffectedStockUpdate = 0;

        try {
            statement = connection.prepareStatement(checkStockQuery);
            statement.setInt(1, ligneCommande.getCodearticle());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int stock = rs.getInt("stock");
                if (stock < ligneCommande.getQtecde()) {
                    return -1;
                }
            }

            statement = connection.prepareStatement(sql);
            statement.setInt(1, ligneCommande.getNumcommande());
            statement.setInt(2, ligneCommande.getCodearticle());
            statement.setInt(3, ligneCommande.getQtecde());
            result = statement.executeUpdate();

            if(result>0){
                statement = connection.prepareStatement(query);
                statement.setInt(1, ligneCommande.getQtecde());
                statement.setInt(2, ligneCommande.getCodearticle());
                rowsAffectedStockUpdate = statement.executeUpdate();

                if (rowsAffectedStockUpdate <= 0) {
                    return 0;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return result;
    }

    @Override
    public LigneCommande findLigneCommandeBynumcommande(int numcommande) {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM lignecommande WHERE numcommande = ?";
        LigneCommande lignecommande = new LigneCommande();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, numcommande);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lignecommande = new LigneCommande(rs.getInt("id"),
                        rs.getInt("numcommande"),
                        rs.getInt("codearticle"),
                        rs.getInt("qteCde")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return lignecommande;
    }

    @Override
    public List<LigneCommande> selectAll() {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT lc.id, lc.numcommande, lc.codearticle, lc.qteCde, " +
                "a.designation AS article_designation, a.prix AS article_prix, " +
                "a.stock AS article_stock, c.id AS commande_id, c.numcommande AS commande_numcommande,c.total AS commande_total " +
                "c.codeClient AS commande_codeClient, c.datecommande AS commande_datecommande " +
                "FROM lignecommande lc " +
                "INNER JOIN article a ON lc.codearticle = a.codearticle " +
                "INNER JOIN commande c ON lc.numcommande = c.numcommande";

        List<LigneCommande> lignecommandes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Article article = new Article(rs.getInt("codearticle"),
                        rs.getString("article_designation"),
                        rs.getDouble("article_prix"),
                        rs.getInt("article_stock"));

                Commande commande = new Commande(rs.getInt("commande_id"),
                        rs.getInt("commande_numcommande"),
                        rs.getInt("commande_codeClient"),
                        rs.getDate("commande_datecommande"),
                        rs.getDouble("commande_total"));

                lignecommandes.add(new LigneCommande(rs.getInt("id"),
                        rs.getInt("numcommande"),
                        article,
                        rs.getInt("qteCde"),
                        commande));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return lignecommandes;
    }

    public List<LigneCommande> selectAllByClientId(int codeclient){
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT lc.id, lc.numcommande, lc.codearticle, lc.qteCde, " +
                "a.designation AS article_designation, a.prix AS article_prix, " +
                "a.stock AS article_stock, c.id AS commande_id, c.numcommande AS commande_numcommande,c.total AS commande_total " +
                "c.codeClient AS commande_codeClient, c.datecommande AS commande_datecommande " +
                "FROM lignecommande lc " +
                "INNER JOIN article a ON lc.codearticle = a.codearticle " +
                "INNER JOIN commande c ON lc.numcommande = c.numcommande " +
                "WHERE c.codeClient = ?";

        List<LigneCommande> lignecommandes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, codeclient);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Article article = new Article(rs.getInt("codearticle"),
                        rs.getString("article_designation"),
                        rs.getDouble("article_prix"),
                        rs.getInt("article_stock"));

                Commande commande = new Commande(rs.getInt("commande_id"),
                        rs.getInt("commande_numcommande"),
                        rs.getInt("commande_codeClient"),
                        rs.getDate("commande_datecommande"),
                        rs.getDouble("commande_total"));

                lignecommandes.add(new LigneCommande(rs.getInt("id"),
                        rs.getInt("numcommande"),
                        article,
                        rs.getInt("qteCde"),
                        commande));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return lignecommandes;
    }
}
