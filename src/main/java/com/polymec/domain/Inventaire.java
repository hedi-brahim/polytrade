package com.polymec.domain;

import com.polymec.domain.ArticleFrns;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Table(name = "iteds")
public class Inventaire implements Serializable {

    private Long id;
    private Date date;
    private double quantite;
    private double qteAvInvt;

    private ArticleFrns articleFrns;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "iteds_num")
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

    @Column(name = "iteds_qne")
    public double getQuantite() {
        return this.quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    @Column(name = "iteds_qan")
    public double getQteAvInvt() {
        return this.qteAvInvt;
    }

    public void setQteAvInvt(double qteAvInvt) {
        this.qteAvInvt = qteAvInvt;
    }

    @ManyToOne
    @JoinColumn(name = "iteds_ar")
    public ArticleFrns getArticleFrns() {
        return this.articleFrns;
    }

    public void setArticleFrns(ArticleFrns articleFrns) {
        this.articleFrns = articleFrns;
    }

    @Override
    public String toString() {
        return "Inventaire - Id: " + id;
    }
}
