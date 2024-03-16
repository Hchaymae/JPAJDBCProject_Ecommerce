package org.structure.dao;

import org.structure.model.Commande;

import java.util.List;

public interface CommandeDAO {
    public int addCommande(Commande commande,int clientid);
//    public boolean updateCommande(Commande commande);
//    public boolean deleteCommande(Commande commande);
    public Commande findCommandeById(int id);
    public List<Commande> selectAll();
    public List<Commande> selectCommandeByClient(int idClient);
    public Commande selectCommandeByClientid(int idClient);
    public String findStatusByClientId(int clientid);
    public int findnumcommandeByClientId(int clientid);
    public double calculateTotalAmount(int numcommand);


}
