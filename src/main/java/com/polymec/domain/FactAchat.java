package com.polymec.domain;

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
@Table(name = "ftra")
public class FactAchat implements Serializable {

    private Long id;

    private Date date;
    private Date dateModif;    
    private String numero;
    private Fournisseur fournisseur;
    private int sr;
    @JsonIgnore
    private BlAchat blAchat;
    private List<Mouvement> mvts;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ftra_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ftra_de")
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
    
    @Column(name = "ftra_ne")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the fournisseur
     */
    @ManyToOne
    @JoinColumn(name = "ftra_fr")    
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    /**
     * @param fournisseur the fournisseur to set
     */
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    @OneToOne(mappedBy = "factAchat")
    public BlAchat getBlAchat() {
        return this.blAchat;
    }

    public void setBlAchat(BlAchat blAchat) {
        this.blAchat = blAchat;
    }

    @OneToMany(mappedBy = "factAchat")
    public List<Mouvement> getMvts() {
        return this.mvts;
    }

    public void setMvts(List<Mouvement> mvts) {
        this.mvts = mvts;
    }

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
}
