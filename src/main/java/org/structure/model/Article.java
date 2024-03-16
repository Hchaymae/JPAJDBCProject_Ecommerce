package org.structure.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codeArticle;
    private String designation;
    private String  auteur;
    private String  titre;
    private double  prix;
    private int  stock;
    @Transient
    private Categorie categorie;
    private String photo;

    @Transient
    private List<LigneCommande> commandes;

    public Article(int codeArticle, String designation, String auteur, String titre, double prix, int stock, Categorie categorie, String photo) {
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.auteur = auteur;
        this.titre = titre;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
        this.photo = photo;
    }
    public Article(int codeArticle, String designation,double prix, int stock) {
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.prix = prix;
        this.stock = stock;
    }
    public Article( String designation, String auteur, String titre, double prix, int stock, Categorie categorie, String photo) {
        this.codeArticle = 0;
        this.designation = designation;
        this.auteur = auteur;
        this.titre = titre;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
        this.photo = photo;
    }

    public List<LigneCommande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<LigneCommande> commandes) {
        this.commandes = commandes;
    }

    public Article() {
        super();
    }

    public int getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(int codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
