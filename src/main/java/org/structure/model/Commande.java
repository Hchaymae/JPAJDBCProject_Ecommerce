package org.structure.model;


import java.util.Date;

public class Commande {
    private int numcommande;
    private int codeClient;
    private Date datecommande;
    protected StatusCommande status;

    public Commande(int numcommande, int codeClient, Date datecommande) {
        this.numcommande = numcommande;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status.Normal;
    }

    public Commande() {
    }

    public Commande(int codeClient) {
        this.codeClient = codeClient;
        this.status = status.Normal;
    }

    public Commande(int codeClient, Date datecommande) {
        this.numcommande = 0;
        this.codeClient = codeClient;
        this.datecommande = datecommande;
        this.status = status.Normal;
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

}
