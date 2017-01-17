package com.polymec.domain;

import static javax.persistence.GenerationType.SEQUENCE;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.text.SimpleDateFormat;

@Entity
public class ClientAct implements Comparable<ClientAct>, Serializable {

    private Long id;
    private String date;
    private String dateModif;    
    private String type;
    private String numero;    
    private String nom;
    private double qte;
    private double puttx;
    private double marge;    

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public ClientAct(Date date, Date dateModif,String type, String num, String nom, double qte, double puaht, double puht, double remise, double ta) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        this.date = (date == null ? null :dateFormat.format(date));
        this.dateModif = (dateModif == null ? null : dateFormat.format(dateModif));        
        this.type = type;
        this.numero = num;
        this.nom = nom;
        this.qte = qte;
        // calculer le prix unitaire ttc de l' article avec remise        
        this.puttx = (puht * (1 - remise/100)) * (1 + ta/100);
        //calculer le gain en pourcentage
        if (puaht > 0) {
            this.marge = this.round((((puht * (1 - remise/100)) / puaht - 1) * 100), 2);
        } else {
            this.marge = 0.0;
        }        
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPuttx() {
        return this.puttx;
    }

    public void setPuttx(double puttx) {
        this.puttx = puttx;
    }

    public double getMarge() {
        return this.marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }
    
    @Override
    public int compareTo(ClientAct n) {
        /*
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
         */
        return n.getDate().compareTo(date);
    }

    @Override
    public String toString() {
        return "Act N°: " + numero;
    }
}
