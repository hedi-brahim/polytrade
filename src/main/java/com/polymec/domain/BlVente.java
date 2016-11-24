package com.polymec.domain;

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

@Entity
@Table(name = "blv")
public class BlVente implements Serializable {

    private Long id;

    private Date date;
    private String numero;
    private Client client;
    private List<Mouvement> mvts;

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

    @Column(name = "blv_no")
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    @Override
    public String toString() {
        return "BL Achat - Id: " + id + ", Reference: " + numero;
    }
}
