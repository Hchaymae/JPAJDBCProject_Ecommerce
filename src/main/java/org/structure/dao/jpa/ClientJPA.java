package org.structure.dao.jpa;

import jakarta.persistence.*;
import org.structure.dao.ClientDao;
import org.structure.model.Client;

import java.util.List;

public class ClientJPA implements ClientDao {
    @PersistenceContext(unitName = "Eclipselink")
    private EntityManager entityManager;

    @Override
    public int addClient(Client client) {
        if(client == null) {
            return -1;
        }else {
            entityManager.persist(client);
            return 0;
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
        String sql = "SELECT * FROM client WHERE email = ?1 AND mdp = ?2";
        Query query = entityManager.createNativeQuery(sql, Client.class);
        query.setParameter(1, email);
        query.setParameter(2, mdp);
        try {
            Client client = (Client) query.getSingleResult();
            return client;
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
            Client client = (Client) query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

}
