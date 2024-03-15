package org.structure.model;

import jakarta.persistence.*;

@Entity
@Table(name="lignecommande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int numcommande;
    private int codearticle;
    private int qtecde;

    public LigneCommande(int id, int numcommande, int codearticle, int qtecde) {
        this.id = id;
        this.numcommande = numcommande;
        this.codearticle = codearticle;
        this.qtecde = qtecde;
    }

    public LigneCommande(int numcommande, int codearticle, int qtecde) {
        this.id = 0;
        this.numcommande = numcommande;
        this.codearticle = codearticle;
        this.qtecde = qtecde;
    }

//    public LigneCommande( int codearticle, int qtecde) {
//        this.numcommande = 0;
//        this.codearticle = codearticle;
//        this.qtecde = qtecde;
//    }

    public LigneCommande() {
        super();
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

    public int getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(int codearticle) {
        this.codearticle = codearticle;
    }

    public int getQtecde() {
        return qtecde;
    }

    public void setQtecde(int qtecde) {
        this.qtecde = qtecde;
    }
}
