package org.structure.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.structure.dao.CommandeDAO;
import org.structure.model.Client;
import org.structure.model.Commande;
import org.structure.model.StatusCommande;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAOImpl implements CommandeDAO {
    @Override
    public int addCommande(Commande commande) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        String sql = "INSERT INTO commande (codeclient,datecommande,status) VALUES(?, NOW(),?)";

        int result = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, commande.getCodeClient());
            statement.setString(2, commande.getStatus().toString());
            result = statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return result;
    }

    public List<Commande> selectAll() {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM commande";
        List<Commande> commandes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                commandes.add(new Commande(rs.getInt("numcommande"),
                        rs.getInt("codeclient"),
                        rs.getDate("datecommande"),
                        StatusCommande.valueOf(rs.getString("status"))
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return commandes;
    }

    @Override
    public Commande findCommandeById(int id) {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM commande WHERE numcommande = ?";
        Commande commande = new Commande();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                commande = new Commande(rs.getInt("numcommande"),
                        rs.getInt("codeclient"),
                        rs.getDate("datecommande"),
                        StatusCommande.valueOf(rs.getString("status"))
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return commande;
    }

    @Override
    public List<Commande> selectCommandeByClient(int idClient) {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM commande WHERE codeclient = ?";
        List<Commande> commandes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idClient );

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                commandes.add(new Commande(rs.getInt("numcommande"),
                        rs.getInt("codeclient"),
                        rs.getDate("datecommande"),
                        StatusCommande.valueOf(rs.getString("status"))
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return commandes;
    }
}
