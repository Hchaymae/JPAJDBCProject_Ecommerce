package org.structure.service;

import org.structure.dao.ArticleDAO;
import org.structure.dao.ClientDAO;
import org.structure.dao.impl.ArticleDAOImpl;
import org.structure.dao.jpa.ArticleJPA;
import org.structure.dao.jpa.ClientJPA;
import org.structure.model.Article;
import org.structure.model.Client;

import java.util.List;

public class ArticleService {
    ArticleDAO article;

    public ArticleService() {
        this.article = new ArticleDAOImpl();
//        this.article = new ArticleJPA();
    }

    public List<Article> findAllService() {
        return article.findAll();
    }

    public Article findByIdService(int id) {
        return article.findById(id);
    }

    public Article findByTitleService(String titre) {
        return article.findByTitle(titre);
    }
}
