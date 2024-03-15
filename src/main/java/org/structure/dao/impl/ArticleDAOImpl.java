package org.structure.dao.impl;

import org.structure.dao.ArticleDAO;
import org.structure.dao.CategorieDAO;
import org.structure.model.Article;
import org.structure.model.Categorie;
import org.structure.model.Client;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO {
    public List<Article> findAll() {
        ConnectDB db = new ConnectDB();
        Connection connection = new ConnectDB().getConnection();
        String sql = "SELECT * FROM article";
        List<Article> articles = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            CategorieDAO categorieDAO = new CategorieDAOImpl();

            while (rs.next()) {
                Categorie categorie = categorieDAO.findById(rs.getInt("refCategorie"));
                articles.add(new Article(rs.getInt("codeArticle"),
                        rs.getString("designation"),
                        rs.getString(("auteur")),
                        rs.getString("titre"),
                        rs.getDouble("prix"),
                        rs.getInt("stock"),
                        categorie,
                        rs.getString("photo")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return articles;
    }

    public Article findById(int id) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT * FROM article WHERE id = ?";
        Article article = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                CategorieDAO categorieDAO = new CategorieDAOImpl();
                Categorie categorie = categorieDAO.findById(rs.getInt("refCategorie"));
                article = new Article(rs.getInt("codeArticle"),
                        rs.getString("designation"),
                        rs.getString(("auteur")),
                        rs.getString("titre"),
                        rs.getDouble("prix"),
                        rs.getInt("stock"),
                        categorie,
                        rs.getString("photo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return article;
    }

    public Article findByTitle(String titre) {
        ConnectDB db = new ConnectDB();
        Connection connection;
        String sql = "SELECT * FROM article WHERE titre = ?";
        Article article = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, titre);
            rs = statement.executeQuery();
            if (rs.next()) {
                CategorieDAO categorieDAO = new CategorieDAOImpl();
                Categorie categorie = categorieDAO.findById(rs.getInt("refCategorie"));

                article = new Article(rs.getInt("codeArticle"),
                        rs.getString("designation"),
                        rs.getString(("auteur")),
                        rs.getString("titre"),
                        rs.getDouble("prix"),
                        rs.getInt("stock"),
                        categorie,
                        rs.getString("photo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();

        return article;
    }
}
