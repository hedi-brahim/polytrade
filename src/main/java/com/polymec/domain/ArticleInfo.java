package com.polymec.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ArticleInfo implements Serializable {

    private Long id;    
    private String famille;   
    private String reference;
    private String designation;
    private Double qteInv;    
    private String dateInv;     
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

    //avec date du dernier inventaire
    public ArticleInfo(Long id, String famille, String reference, String designation, Double qteInv, Date dateInv, double qte, double puaht, double puvht, double tva) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        
        this.id = id;
        this.famille = famille;
        this.reference = reference;
        this.designation = designation;
        this.qteInv = (qteInv == null ? null : qteInv);
        this.dateInv = (dateInv == null ? null : dateFormat.format(dateInv));         
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

    public Double getQteInv() {
        return qteInv;
    }

    public void setQteInv(Double qteInv) {
        this.qteInv = qteInv;
    }

    public String getDateInv() {
        return this.dateInv;
    }

    public void setDateInv(String dateInv) {
        this.dateInv = dateInv;
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
