package org.structure.dao;

import org.structure.model.Commande;

import java.util.List;

public interface CommandeDAO {
    public int addCommande(Commande commande);
//    public boolean updateCommande(Commande commande);
//    public boolean deleteCommande(Commande commande);
    public Commande findCommandeById(int id);
    public List<Commande> selectAll();
    public List<Commande> selectCommandeByClient(int idClient);
//    public List<Commande> selectCommandeByDate(String date);
//    public List<Commande> selectCommandeByProduitAndClient(int idProduit, int idClient);
//    public List<Commande> selectCommandeByProduitAndDate(int idProduit, String date);
}
