package org.structure.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.structure.dao.LigneCommandeDAO;
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
        }else{
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
}
