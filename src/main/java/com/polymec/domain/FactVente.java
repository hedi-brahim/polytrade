package com.polymec.domain;

import com.polymec.domain.Client;
import com.polymec.domain.BlVente;
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
@Table(name = "ftrev")
public class FactVente implements Serializable {

    private Long id;

    private Date date;
    private Date dateModif;    
    private String numero;
    private Client client;
    private int sr;
    @JsonIgnore
    private BlVente blVente;
    private List<Mouvement> mvts;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ftrev_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ftrev_de")
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
    
    @Column(name = "ftrev_no")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @OneToOne(mappedBy = "factVente")
    public BlVente getBlVente() {
        return this.blVente;
    }

    public void setBlVente(BlVente blVente) {
        this.blVente = blVente;
    }
    
    @ManyToOne
    @JoinColumn(name = "ftrev_ct")
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "factVente")
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
