package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.structure.dao.CategorieDAO;
import org.structure.model.Categorie;

public class CategorieJPA implements CategorieDAO {
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;

    public CategorieJPA(){
        entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public Categorie findById(int ref) {

        String sql = "SELECT c FROM Categorie c WHERE c.Cat = :Cat";
        TypedQuery<Categorie> query = entityManager.createQuery(sql, Categorie.class);
        query.setParameter("Cat", ref);

        try {
            return query.getSingleResult();
        } catch (
                NoResultException ex) {
            return null;
        }
    }
}
