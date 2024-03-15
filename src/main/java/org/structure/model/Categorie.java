package org.structure.model;

import jakarta.persistence.*;

@Entity
@Table(name="categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int refCat;
    private String Cat;

    public Categorie(int refCat, String cat) {
        this.refCat = refCat;
        Cat = cat;
    }

    public Categorie() {
        super();
    }

    public int getRefCat() {
        return refCat;
    }

    public void setRefCat(int refCat) {
        this.refCat = refCat;
    }

    public String getCat() {
        return Cat;
    }

    public void setCat(String cat) {
        Cat = cat;
    }
}
