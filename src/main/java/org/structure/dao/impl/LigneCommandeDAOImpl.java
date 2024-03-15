package org.structure.dao.impl;

import org.structure.dao.LigneCommandeDAO;
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

        int result = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ligneCommande.getNumcommande());
            statement.setInt(2, ligneCommande.getCodearticle());
            statement.setInt(2, ligneCommande.getQtecde());
            result = statement.executeUpdate();

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
        String sql = "SELECT * FROM commande WHERE numcommande = ?";
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
        String sql = "SELECT * FROM commande";
        List<LigneCommande> lignecommandes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lignecommandes.add(new LigneCommande(rs.getInt("id"),
                        rs.getInt("numcommande"),
                        rs.getInt("codearticle"),
                        rs.getInt("qteCde")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return lignecommandes;
    }
}
