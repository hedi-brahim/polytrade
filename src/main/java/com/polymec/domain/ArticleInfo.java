package com.polymec.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ArticleInfo implements Serializable {

    private Long id;    
    private String famille;   
    private String reference;
    private String designation;
    private double quantite;
    private double puaht;
    private double puvht;

    public ArticleInfo()
    {
        
    }
    
    public ArticleInfo(Long id, String famille, String reference, String designation, double qte, double puaht, double puvht, double tva) {
        this.id = id;
        this.famille = famille;
        this.reference = reference;
        this.designation = designation;
        this.quantite = qte;
        this.puaht = puaht * (1 + tva/100);
        this.puvht = puvht * (1 + tva/100);
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //@Column(nullable=true)
    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPuaht() {
        return this.puaht;
    }

    public void setPuaht(double puaht) {
        this.puaht = puaht;
    }

    public double getPuvht() {
        return this.puvht;
    }

    public void setPuvht(double puvht) {
        this.puvht = puvht;
    }

    @Override
    public String toString() {
        return "Document - Ref: " + reference + ", Article: " + designation;
    }
}
