package org.structure.dao.impl;

import org.structure.dao.CategorieDAO;
import org.structure.model.Article;
import org.structure.model.Categorie;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieDAOImpl implements CategorieDAO {
    @Override
    public Categorie findById(int ref) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT * FROM categorie WHERE refCat = ?";
        Categorie categorie = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ref);
            rs = statement.executeQuery();
            if (rs.next()) {
                categorie = new Categorie(rs.getInt("refCat"),
                        rs.getString("Cat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return categorie;
    }
}
