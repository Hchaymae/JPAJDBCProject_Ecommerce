package org.structure.dao;

import org.structure.model.Article;

import java.util.List;

public interface ArticleDAO {
//    int save(Article article);
//    boolean delete(Article article);
//    boolean edit(Article article);
    List<Article> findAll();
    Article findById(int id);
    Article FindArticleByDesignation(String designation);
//    boolean isTitleUnique(String title);
}
