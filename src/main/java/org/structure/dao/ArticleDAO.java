package org.structure.dao;

import org.structure.model.Article;

import java.util.List;

public interface ArticleDAO {
//    int save(Article article);
//    boolean delete(Article article);
//    boolean edit(Article article);
    List<Article> findAll();
    Article findById(int id);
    Article findByTitle(String titre);
//    boolean isTitleUnique(String title);
}
