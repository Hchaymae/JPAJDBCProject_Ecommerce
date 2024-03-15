package org.structure.dao;

import org.structure.model.LigneCommande;

import java.util.List;

public interface LigneCommandeDAO {
    public int addLigneCommande(LigneCommande ligneCommande);
    public LigneCommande findLigneCommandeBynumcommande(int numcommande);
    public List<LigneCommande> selectAll();

    //    public boolean updateLigneCommande(LigneCommande ligneCommande);
//    public boolean deleteLigneCommande(LigneCommande ligneCommande);
}
