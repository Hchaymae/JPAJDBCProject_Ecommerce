package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.structure.dao.CommandeDAO;
import org.structure.model.Article;
import org.structure.model.Commande;

import java.util.List;

public class CommandeJPA implements CommandeDAO {
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;

    public CommandeJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @Override
    public int addCommande(Commande commande) {
        if(commande == null) {
            return 0;
        }else{
            entityManager.persist(commande);
            entityManager.getTransaction().commit();
            return 1;
        }
    }

    @Override
    public Commande findCommandeById(int id) {
        return entityManager.find(Commande.class, id);
    }

    @Override
    public List<Commande> selectAll() {
        return entityManager.createNativeQuery("SELECT * FROM commande", Commande.class).getResultList();
    }

    @Override
    public List<Commande> selectCommandeByClient(int idClient) {
        String sql = "SELECT c FROM Commande c WHERE c.codeClient = :idClient";
        TypedQuery<Commande> query = entityManager.createQuery(sql, Commande.class);
        query.setParameter("idClient", idClient);
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
