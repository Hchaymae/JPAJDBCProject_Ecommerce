package org.structure.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.structure.dao.CommandeDAO;
import org.structure.model.Client;
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

public class CommandeDAOImpl implements CommandeDAO {
    @Override
    public int addCommande(Commande commande,int clientid) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        PreparedStatement statement = null;
        String sql =null;

        int numcommand = findnumcommandeByClientId(clientid);
        int result = 0;

        try {
            if(numcommand>0){
                String totalSql = "SELECT SUM(l.qteCde * a.prix) as total FROM lignecommande l JOIN article a ON l.codearticle = a.codearticle WHERE l.numcommande = ?";
                PreparedStatement totalStatement = connection.prepareStatement(totalSql);
                totalStatement.setInt(1, numcommand);
                ResultSet rs = totalStatement.executeQuery();
                double total = 0;
                if (rs.next()) {
                    total = rs.getDouble("total");
                }

                sql = "UPDATE commande SET datecommande = NOW(), total = ? WHERE numcommande = ? AND codeclient = ?";
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, total);
                statement.setInt(2, numcommand);
                statement.setInt(3, clientid);
                result = statement.executeUpdate();
                return result > 0 ? 1 : 0;
            }else{
                numcommand=numcommand+1;
                sql = "INSERT INTO commande (numcommande,codeclient,datecommande,status) VALUES(?,?, NOW(),?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1,numcommand);
                statement.setInt(2, clientid);
                statement.setString(3, commande.getStatus().toString());
                result = statement.executeUpdate();
                return result > 0 ? -1 : 0;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return 0;
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
    @Override
    public Commande selectCommandeByClientid(int idClient){
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM commande WHERE codeclient = ?";
        Commande commande = new Commande();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idClient );

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

    public int findnumcommandeByClientId(int clientid){
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT numcommande FROM commande WHERE codeclient = ?";
        int numcommande = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, clientid);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                numcommande = rs.getInt("numcommande");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return numcommande;
    }
    public String findStatusByClientId(int clientid){
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT status FROM commande WHERE codeclient = ?";
        String status = "";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, clientid);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                status = rs.getString("status");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return status;
    }

    public double calculateTotalAmount(int numcommand) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        String sql = null;
        double total = 0;

        try {
            sql = "SELECT SUM(l.qteCde * a.prix) as total FROM lignecommande l JOIN article a ON l.codearticle = a.codearticle WHERE l.numcommande = ?";
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setInt(1, numcommand);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
                sql = "UPDATE commande SET total = ? WHERE numcommande = ?";
                PreparedStatement updateStatement = connection.prepareStatement(sql);
                updateStatement.setDouble(1, total);
                updateStatement.setInt(2, numcommand);
                updateStatement.executeUpdate();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return total;
    }


}
