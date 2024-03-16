package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.structure.dao.LigneCommandeDAO;
import org.structure.model.Article;
import org.structure.model.Commande;
import org.structure.model.LigneCommande;
import org.structure.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LigneCommandeJPA implements LigneCommandeDAO {
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;

    public LigneCommandeJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public int addLigneCommande(LigneCommande ligneCommande) {
        if(ligneCommande == null) {
            return 0;
        } else {
            Article article = entityManager.find(Article.class, ligneCommande.getCodearticle());
            if(article.getStock() < ligneCommande.getQtecde()) {
                return -1;
            }
            entityManager.persist(ligneCommande);
            entityManager.getTransaction().commit();
            return 1;
        }
    }

    @Override
    public LigneCommande findLigneCommandeBynumcommande(int numcommande) {
        return entityManager.find(LigneCommande.class, numcommande);
    }

    @Override
    public List<LigneCommande> selectAll() {
        return entityManager.createNativeQuery("SELECT * FROM lignecommande", LigneCommande.class).getResultList();
    }

    @Override
    public List<LigneCommande> selectAllByClientId(int codeclient){
        String sql = "SELECT lc.* FROM lignecommande lc " +
                "INNER JOIN article a ON lc.codearticle = a.codearticle " +
                "INNER JOIN commande c ON lc.numcommande = c.numcommande " +
                "WHERE c.codeClient = ?1";

        Query query = entityManager.createNativeQuery(sql, LigneCommande.class);
        query.setParameter(1, codeclient);

        return query.getResultList();
    }
}
