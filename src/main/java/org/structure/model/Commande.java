package org.structure.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="commande")
public class Commande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int numcommande;
    private int codeClient;
    private Date datecommande;
    protected StatusCommande status;
    @Transient
    private List<LigneCommande> commandes;
    @Transient
    private Client client;
    private double total;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCommande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<LigneCommande> commandes) {
        this.commandes = commandes;
    }

    public Commande(int id, int numcommande, int codeClient, Date datecommande,double total) {
        this.id = id;
        this.numcommande = numcommande;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status.Normal;
        this.total=total;
    }
    public Commande() {
        super();
    }

    public Commande(int codeClient) {
        this.codeClient = codeClient;
        this.status = status.Normal;
    }

    public Commande(int numcommande, int codeClient, Date datecommande, StatusCommande status) {
        this.id = 0;
        this.numcommande = numcommande;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status;
        this.total = 0;
    }

    public Commande(int numcommande, int codeClient, Date datecommande, StatusCommande status, List<LigneCommande> commandes, Client client, double total) {
        this.id = 0;
        this.numcommande = numcommande;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status;
        this.commandes = commandes;
        this.client = client;
        this.total = total;
    }

    public Commande(int codeClient, Date datecommande) {
        this.numcommande = 0;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status.Normal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumcommande() {
        return numcommande;
    }

    public void setNumcommande(int numcommande) {
        this.numcommande = numcommande;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public StatusCommande getStatus() {
        return status;
    }

    public void setStatus(StatusCommande status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
