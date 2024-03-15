package org.structure.dao;

import org.structure.model.Client;

import java.util.List;

public interface ClientDAO {
    public int addClient(Client client);
    public boolean updateClient(Client client);
    public boolean deleteClient(Client client);
    public Client findClientById(int id);
    public List<Client> selectAll();
    public Client findClient(String email, String mdp);
    public boolean isEmailUnique(String email);
}
