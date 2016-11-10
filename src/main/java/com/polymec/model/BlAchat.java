package com.polymec.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import java.util.Date;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "bla")
public class BlAchat implements Serializable {

    private Logger logger = LoggerFactory.getLogger("com.polymec.model.BlAchat");

    private Long id;

    //@Transient 
    //private String type = "Achat";
    //@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")	
    private Date date;
    private String numero;
    private List<Mouvement> mvts;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bla_num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
     */
    //@Column(name = "bla_dl", columnDefinition="DATETIME")
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bla_dl")
    public Date getDate() {
        logger.info("Print Method GetDate");
        return this.date;
    }

    public void setDate(Date date) {
        logger.info("Print Method SetDate");
        this.date = date;
    }

    @Column(name = "bla_nl")
    public String getNumero() {
        logger.info("Print Method GetNumero");
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @OneToMany(mappedBy = "blAchat")
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
