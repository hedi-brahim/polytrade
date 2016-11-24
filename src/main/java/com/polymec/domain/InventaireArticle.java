package com.polymec.domain;

import static javax.persistence.GenerationType.SEQUENCE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.text.SimpleDateFormat;

@Entity
public class InventaireArticle implements Comparable<InventaireArticle>, Serializable {

    private Long id;
    private String date;
    private String type;
    private String nom;
    private String reference;
    private double quantite;
    private double puaht;
    private double remise;
    private String designation;

    public InventaireArticle(Date date, String type, String ref, String nom, double qte, double puaht, double remise, String des) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        this.date = dateFormat.format(date); //date.toString();
        this.type = type;
        this.reference = ref;
        this.nom = nom;
        this.quantite = qte;
        this.designation = des;
        this.puaht = puaht;
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

    public double getPuaht() {
        return this.puaht;
    }

    public void setPuaht(double puaht) {
        this.puaht = puaht;
    }

    public double getRemise() {
        return this.remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    @Override
    public int compareTo(InventaireArticle n) {
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
