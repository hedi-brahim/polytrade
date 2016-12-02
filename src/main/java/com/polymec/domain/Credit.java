package com.polymec.domain;

import static javax.persistence.GenerationType.SEQUENCE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.text.SimpleDateFormat;
import javax.persistence.Column;

@Entity
public class Credit implements Comparable<Credit>, Serializable {

    private Long id;
    private String date;
    private String dateModif = null;
    private String type;
    private String nom;
    private String reference;
    private double quantite;
    private double mnt;
    private double remise;
    private String designation;

    public Credit(Date date, Date dateModif, String type, String ref, String nom, double qte, double mnt, double remise, String des) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        this.date = dateFormat.format(date); //date.toString();
        if(dateModif!=null)
        {
            this.dateModif = dateFormat.format(dateModif);
        } //date.toString();        
        
        this.type = type;
        this.reference = ref;
        this.nom = nom;
        this.quantite = qte;
        this.designation = des;
        // calculer le montant total de la ligna article * qte - remise
        this.mnt = qte*mnt*(1 - (remise/100));
        this.remise = remise;
    }

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(nullable = true)
    public String getDateModif() {
        return this.dateModif;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getMnt() {
        return this.mnt;
    }

    public void setMnt(double mnt) {
        this.mnt = mnt;
    }

    public double getRemise() {
        return this.remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    @Override
    public int compareTo(Credit n) {
        /*
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
         */
        return n.getDate().compareTo(date);
    }

    @Override
    public String toString() {
        return "Document - Ref: " + reference + ", Article: " + designation;
    }
}
