package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.structure.dao.ClientDAO;
import org.structure.model.Client;

import java.util.List;

public class ClientJPA implements ClientDAO {

    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;
    public ClientJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public int addClient(Client client) {
        if(client == null) {
            return 0;
        }else{
            String hashedPassword = DigestUtils.sha256Hex(client.getMdp());
            client.setMdp(hashedPassword);
            entityManager.persist(client);
            entityManager.getTransaction().commit();
            return 1;
        }
    }
    @Override
    public boolean updateClient(Client client) {
        if(client == null) {
            return false;
        }else{
            entityManager.merge(client);
            return true;
        }
    }
    @Override
    public boolean deleteClient(Client client) {
        entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
        return true;
    }
    @Override
    public Client findClientById(int id) {
        return entityManager.find(Client.class, id);
    }
    @Override
    public List<Client> selectAll() {
        return entityManager.createNativeQuery("SELECT * FROM client", Client.class).getResultList();
    }
     @Override
    public Client findClient(String email, String mdp) {
        String hashedPassword = DigestUtils.sha256Hex(mdp);
        String sql = "SELECT c FROM Client c WHERE c.email = :email AND c.mdp = :mdp";
        TypedQuery<Client> query = entityManager.createQuery(sql, Client.class);
        query.setParameter("email", email);
        query.setParameter("mdp", hashedPassword);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public boolean isEmailUnique(String email) {
        String sql = "SELECT * FROM client WHERE email = ?1";
        Query query = entityManager.createNativeQuery(sql, Client.class);
        query.setParameter(1, email);
        try {
            query.getSingleResult();
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }

}
