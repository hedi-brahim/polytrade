package com.polymec.domain.db;

import com.polymec.domain.db.BlVente;
import com.polymec.domain.db.BlAchat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

import java.util.List;
import javax.persistence.OneToOne;

@Entity
@Table(name = "rgmt")
public class Reglement implements Serializable {

    private Long id;

    private Date date;
    private Date dateModif;    
    private String numero;
    private double montant;  
    private int sr;
    private BlAchat blAchat;
    private FactAchat factAchat;    
    private BlVente blVente;
    private FactVente factVente;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rgmt_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ce")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "ue")        
    public Date getDateModif() {
        return this.dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }
    
    @Column(name = "rgmt_no")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "rgmt_mt")
    public double getMontant() {
        //return round(this.puaht,3);
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    /*
    @OneToOne(mappedBy = "factVente")
    public BlVente getBlVente() {
        return this.blVente;
    }

    public void setBlVente(BlVente blVente) {
        this.blVente = blVente;
    }
     */

    /*
    @OneToMany(mappedBy = "factVente")
    public List<Mouvement> getMvts() {
        return this.mvts;
    }

    public void setMvts(List<Mouvement> mvts) {
        this.mvts = mvts;
    }
     */
    @Override
    public String toString() {
        return "Facture Vente - Id: " + id + ", Reference: " + numero;
    }

    /**
     * @return the sr
     */
    @Column(name = "sr")
    public int getSr() {
        return sr;
    }

    /**
     * @param sr the sr to set
     */
    public void setSr(int sr) {
        this.sr = sr;
    }

   @ManyToOne
    @JoinColumn(name = "rgmt_bt")
    public BlAchat getBlAchat() {
        return this.blAchat;
    }

    public void setBlAchat(BlAchat blAchat) {
        this.blAchat = blAchat;
    }

    @ManyToOne
    @JoinColumn(name = "rgmt_ft")
    public FactAchat getFactAchat() {
        return this.factAchat;
    }

    public void setFactAchat(FactAchat factAchat) {
        this.factAchat = factAchat;
    }
    
    @ManyToOne
    @JoinColumn(name = "rgmt_be")
    public BlVente getBlVente() {
        return this.blVente;
    }

    public void setBlVente(BlVente blVente) {
        this.blVente = blVente;
    }

    @ManyToOne
    @JoinColumn(name = "rgmt_fe")
    public FactVente getFactVente() {
        return this.factVente;
    }

    public void setFactVente(FactVente factVente) {
        this.factVente = factVente;
    }

}
