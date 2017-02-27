package com.polymec.domain.db;

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
import javax.persistence.Transient;

@Entity
@Table(name = "blv")
public class BlVente implements Serializable {

    private Long id;

    private Date date;
    private Date dateModif;    
    private String numero;
    private Client client;
    private Double mntTot;
    //private Long blv_fe;
    private FactVente factVente = null;
    private int sr;
    private List<Mouvement> mvts;
    private List<Reglement> regls;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "blv_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "blv_dl")
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
    
    @Column(name = "blv_no")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
  
<<<<<<< HEAD:src/main/java/com/polymec/domain/BlVente.java
    @Column(name = "blv_remg")
    public Double getMntTot() {
        return this.mntTot;
    }

    public void setMntTot(Double mntTot) {
        this.mntTot = mntTot;
    }
=======
>>>>>>> develop:src/main/java/com/polymec/domain/db/BlVente.java
    
    @OneToOne
    @JoinColumn(name = "blv_fe")
    public FactVente getFactVente() {
        return this.factVente;
    }

    public void setFactVente(FactVente factVente) {
        this.factVente = factVente;
    }   
    
    @ManyToOne
    @JoinColumn(name = "blv_ct")
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "blVente")
    public List<Mouvement> getMvts() {
        return this.mvts;
    }

    public void setMvts(List<Mouvement> mvts) {
        this.mvts = mvts;
    }
    
    @OneToMany(mappedBy = "blVente")
    public List<Reglement> getRegls() {
            return this.regls;
    }
    public void setRegls(List<Reglement> reglements) {
            this.regls = reglements;
    }
        

    @Override
    public String toString() {
        return "BL Achat - Id: " + id + ", Reference: " + numero;
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
/*
    public Long getBlv_fe() {
        return blv_fe;
    }

    public void setBlv_fe(Long blv_fe) {
        this.blv_fe = blv_fe;
    }
*/
}
