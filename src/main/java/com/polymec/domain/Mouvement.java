package com.polymec.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "mvt")
public class Mouvement implements Serializable {

    private Long id;
    private Date date;
    private Date dateModif;    
    private double quantite;
    private double puaht;
    private double marge;    
    private double remise;
    private int sr;
    private BlAchat blAchat;
    private BlVente blVente;
    private FactAchat factAchat;    
    private FactVente factVente;
    private ArticleFrns articleFrns;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Mvt_Num")
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
    @Column(name = "mvt_qt")
    public double getQuantite() {
        return this.quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    @Column(name = "mvt_pt")
    public double getPuaht() {
        return this.puaht;
    }

    public void setPuaht(double puaht) {
        this.puaht = puaht;
    }

    @Column(name = "mvt_tx")
    public double getMarge() {
        return this.marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }
    
    @Column(name = "mvt_re")
    public double getRemise() {
        return this.remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
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
    @JoinColumn(name = "mvt_bt")
    public BlAchat getBlAchat() {
        return this.blAchat;
    }

    public void setBlAchat(BlAchat blAchat) {
        this.blAchat = blAchat;
    }

    @ManyToOne
    @JoinColumn(name = "mvt_be")
    public BlVente getBlVente() {
        return this.blVente;
    }

    public void setBlVente(BlVente blVente) {
        this.blVente = blVente;
    }

    @ManyToOne
    @JoinColumn(name = "mvt_fe")
    public FactVente getFactVente() {
        return this.factVente;
    }

    public void setFactVente(FactVente factVente) {
        this.factVente = factVente;
    }

    @ManyToOne
    @JoinColumn(name = "mvt_ft")
    public FactAchat getFactAchat() {
        return this.factAchat;
    }

    public void setFactAchat(FactAchat factAchat) {
        this.factAchat = factAchat;
    }
    
    @ManyToOne
    @JoinColumn(name = "mvt_ar")
    public ArticleFrns getArticleFrns() {
        return this.articleFrns;
    }

    public void setArticleFrns(ArticleFrns articleFrns) {
        this.articleFrns = articleFrns;
    }

    @Override
    public String toString() {
        return "Mouvement - Id: " + id + ", Quantite: " + quantite;
    }
}
