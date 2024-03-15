package org.structure.model;

public class LigneCommande {
    private int numcommande;
    private int codearticle;
    private int qtecde;

    public LigneCommande(int numcommande, int codearticle, int qtecde) {
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
