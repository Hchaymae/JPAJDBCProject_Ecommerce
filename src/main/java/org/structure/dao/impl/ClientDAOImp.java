package org.structure.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.structure.dao.ClientDao;
import org.structure.model.Client;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImp implements ClientDao {
    @Override
    public int addClient(Client client) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        String hashedPassword = DigestUtils.sha256Hex(client.getMdp());
        String sql = "INSERT INTO client (email,nom,  prenom,  adresse,  codepostale,  ville,  tel,  mdp) VALUES(?, ?, ?, ?, ?,?,?,?)";

        int result = 0;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, client.getEmail());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getPrenom());
            statement.setString(4, client.getAdresse());
            statement.setInt(5, client.getCodepostale());
            statement.setString(6, client.getVille());
            statement.setString(7, client.getTel());
            statement.setString(8, hashedPassword);

            result = statement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return result;
    }

    @Override
    public boolean updateClient(Client client) {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "UPDATE client SET email=?, nom=?, prenom=?, adresse=?, codepostale=?,ville=?,tel=?,mdp=? WHERE id=?";
        boolean result = false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, client.getEmail());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getPrenom());
            statement.setString(4, client.getAdresse());
            statement.setInt(5, client.getCodepostale());
            statement.setString(6, client.getVille());
            statement.setString(7, client.getTel());
            statement.setString(8, client.getMdp());
            statement.setInt(9, client.getId());

            result = statement.executeUpdate() > 0;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return result;
    }
    @Override
    public boolean deleteClient(Client client) {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "DELETE FROM client WHERE id=?";
        boolean result = false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getId());

            result = statement.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return result;
    }
    @Override
    public Client findClientById(int id) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT email,nom,  prenom,  adresse,  codepostale,  ville,  tel,  mdp FROM client WHERE id = ?";
        Client client = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString(("nom")),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getInt("codepostale"),
                        rs.getString("ville"),
                        rs.getString("tel"),
                        rs.getString("mdp")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return client;
    }
    @Override
    public List<Client> selectAll() {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM employee";
        List<Client> clients = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString(("nom")),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getInt("codepostale"),
                        rs.getString("ville"),
                        rs.getString("tel"),
                        rs.getString("mdp")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return clients;
    }
    @Override
    public Client findClient(String email, String mdp) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT email,nom,  prenom,  adresse,  codepostale,  ville,  tel,  mdp FROM client WHERE email = ? AND mdp = ?";
        Client client = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, mdp);
            rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString(("nom")),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getInt("codepostale"),
                        rs.getString("ville"),
                        rs.getString("tel"),
                        rs.getString("mdp")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return client;
    }

    @Override
    public boolean isEmailUnique(String email) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT * FROM client WHERE email LIKE ?";
        boolean unique = true;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                unique = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unique;
    }

}
