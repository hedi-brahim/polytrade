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

import java.util.List;
import java.util.ArrayList;
//import com.google.common.collect.Lists;

@Entity
@Table(name = "clts")
public class Client implements Serializable {

    private Long id;
    private String raison;
    private int sr;
    @JsonIgnore
    private List<BlVente> blVentes = new ArrayList<BlVente>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "clts_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "clts_nm")
    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
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

    @OneToMany(mappedBy = "client")
    public List<BlVente> getBlVentes() {
        return this.blVentes;
    }

    public void setBlVentes(List<BlVente> blVentes) {
        this.blVentes = blVentes;
    }

    @Override
    public String toString() {
        return "Client - Id: " + id + ", Raison: " + raison;
    }
}
