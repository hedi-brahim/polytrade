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

import java.sql.Blob;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "docs")
public class Docs implements Serializable {

    private Long id;
    private String nom;
    private Blob img;
    private int sr;
    

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "doc_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "doc_nm")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "doc_fle")
    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "Doc - Id: " + id + ", Nom: " + nom;
    }
}
