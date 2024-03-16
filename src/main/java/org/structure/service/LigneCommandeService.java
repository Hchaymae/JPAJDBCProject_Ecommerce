package org.structure.service;

import jakarta.servlet.http.HttpServletRequest;
import org.structure.dao.CommandeDAO;
import org.structure.dao.LigneCommandeDAO;
import org.structure.dao.impl.CommandeDAOImpl;
import org.structure.dao.impl.LigneCommandeDAOImpl;
import org.structure.model.Article;
import org.structure.model.Client;
import org.structure.model.LigneCommande;

import java.util.List;

public class LigneCommandeService {
    LigneCommandeDAO ligneCommandedao;

    public LigneCommandeService() {
        this.ligneCommandedao = new LigneCommandeDAOImpl();
//        this.ligneCommande = new LigneCommandeDAOImpl();
    }

    public boolean addLigneCommande(HttpServletRequest request, LigneCommande lignecommande) {
            int result = ligneCommandedao.addLigneCommande(lignecommande);
        if (result > 0 && result == -1) {
            request.setAttribute("SUCCESS","L'article ajouté au panier avec succès !");
            return true;
        }else if(result == 0 ){
            request.setAttribute("ERROR","Erreur lors de l'ajout de l'article au panier.");
            return false;
        }else {
            request.setAttribute("WARNING","Le stock n'est pas disponible.");
            return false;
        }
    }

    public List<LigneCommande> findAllLigneCommandesService() {
        return ligneCommandedao.selectAll();
    }

    public List<LigneCommande> findAllLiCmdeByClientIdService(int clientid) {
        return ligneCommandedao.selectAllByClientId(clientid);
    }

    public LigneCommande FindService(int numcommande) {
        return ligneCommandedao.findLigneCommandeBynumcommande(numcommande);
    }

}
