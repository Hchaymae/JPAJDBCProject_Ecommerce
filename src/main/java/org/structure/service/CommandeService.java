package org.structure.service;

import jakarta.servlet.http.HttpServletRequest;
import org.structure.dao.ArticleDAO;
import org.structure.dao.CommandeDAO;
import org.structure.dao.impl.ArticleDAOImpl;
import org.structure.dao.impl.CommandeDAOImpl;
import org.structure.model.Commande;
import org.structure.model.LigneCommande;

import java.util.List;

public class CommandeService {
    CommandeDAO commandedao;

    public CommandeService() {
        this.commandedao = new CommandeDAOImpl();
//        this.commande = new CommandeJPA();
    }

    public int addCommande(HttpServletRequest request, Commande commande,int clientid) {
        int result = commandedao.addCommande(commande,clientid);
        if (result > 0) {
            request.setAttribute("SUCCESS","La commande validé avec succès !");
            return 1;
        } else if(result == -1){
            request.setAttribute("SUCCESS","La commande validé avec succès !");
            return -1;
        }else {
            request.setAttribute("ERROR","Erreur lors de la validation de la commande.");
            return 0;
        }
    }
    public double calculateTotalAmountService(int numcommand){
        return commandedao.calculateTotalAmount(numcommand);
    }

    public List<Commande> findAllCommandesService() {
        return commandedao.selectAll();
    }

    public Commande findService(int id) {
        return commandedao.findCommandeById(id);
    }

    public List<Commande> findByClientService(int idClient) {
        return commandedao.selectCommandeByClient(idClient);
    }

    public Commande findByClientIdService(int idClient) {
        return commandedao.selectCommandeByClientid(idClient);
    }

    public int findNumcommandService(int idclient){
        return commandedao.findnumcommandeByClientId(idclient);
    }
    public String findStatusService(int idclient){
        return commandedao.findStatusByClientId(idclient);
    }
}
