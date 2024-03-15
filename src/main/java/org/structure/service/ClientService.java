package org.structure.service;


import jakarta.servlet.http.HttpServletRequest;
import org.structure.dao.ClientDAO;
import org.structure.dao.impl.ClientDAOImpl;
import org.structure.dao.jpa.ClientJPA;
import org.structure.model.Client;

import java.util.List;

public class ClientService {
    ClientDAO clientDao;

    public ClientService() {
        this.clientDao = new ClientJPA();
    }

    public boolean AddService(HttpServletRequest request,Client client) {
        boolean isUnique = clientDao.isEmailUnique(client.getEmail());
        if (isUnique) {
            int result = clientDao.addClient(client);
            if (result > 0) {
                request.setAttribute("SUCCESS","Client ajouté avec succès !");
                return true;
            } else {
                request.setAttribute("ERROR","Erreur lors de l'ajout du client.");
                return false;
            }
        } else {
            request.setAttribute("WARNING","L'adresse email spécifiée existe déjà.");
            return false;
        }
    }

    public void UpdateService(HttpServletRequest request,Client client) {
        boolean isUnique = clientDao.isEmailUnique(client.getEmail());
        if (isUnique) {
            boolean result = clientDao.updateClient(client);
            if (result) {
                request.setAttribute("SUCCESS", "Client modifié avec succès !");
            } else {
                request.setAttribute("ERROR","Erreur lors de la modification du client.");
            }
        }else{
            request.setAttribute("WARNING","L'adresse email spécifiée existe déjà.");
        }
    }

    public void DeleteService(HttpServletRequest request,Client client) {
        boolean result = clientDao.deleteClient(client);
        if (result) {
            request.setAttribute("SUCCESS", "Client supprimé avec succès !");
        } else {
            request.setAttribute("ERROR","Erreur lors de la suppression du client.");
        }
    }

    public Client FindService(int id) {
        return clientDao.findClientById(id);
    }

    public Client FindByEmailMdpService(String email, String mdp) {
        return clientDao.findClient(email, mdp);
    }

    public boolean isEmailUniqueService(String email) {
        return clientDao.isEmailUnique(email);
    }

    public List<Client> SelectAllService() {
        return clientDao.selectAll();
    }

}
