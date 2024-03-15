package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.structure.dao.ArticleDAO;
import org.structure.model.Article;
import org.structure.model.Client;

import java.util.List;

public class ArticleJPA implements ArticleDAO {
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;

    public ArticleJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public List<Article> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM article", Article.class).getResultList();
    }

    @Override
    public Article findById(int id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public Article FindArticleByDesignation(String designation) {
        String sql = "SELECT c FROM Article c WHERE c.designation = :designation";
        TypedQuery<Article> query = entityManager.createQuery(sql, Article.class);
        query.setParameter("designation", designation);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
