package org.structure.model;

import jakarta.persistence.*;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private int codepostale;
    private String ville;
    private String tel;
    private String mdp;

    //Contructeurs
    public Client() {
        super();
    }

    public Client( String email, String nom, String prenom, String adresse, int codepostale, String ville, String tel, String mdp) {
        this.id = 0;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codepostale = codepostale;
        this.ville = ville;
        this.tel = tel;
        this.mdp = mdp;
    }


    public Client(int id, String email, String nom, String prenom, String adresse, int codepostale, String ville, String tel, String mdp) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codepostale = codepostale;
        this.ville = ville;
        this.tel = tel;
        this.mdp = mdp;
    }
    public Client(String email, String mdp){
        this.email=email;
        this.mdp = mdp;
    }

    //getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodepostale() {
        return codepostale;
    }

    public void setCodepostale(int codepostale) {
        this.codepostale = codepostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codepostale=" + codepostale +
                ", ville='" + ville + '\'' +
                ", tel='" + tel + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}