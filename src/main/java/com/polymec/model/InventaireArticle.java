package com.polymec.model;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


import org.hibernate.annotations.Type;

@Entity
public class InventaireArticle implements Serializable {
	
    private Long id;
    private String reference;
	private Double quantite;
	private String artDesignation;
	
	@Id
	@GeneratedValue(strategy = AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
		return reference;
    }

    public void setReference(String reference) {
		this.reference = reference;
    }
	
    public Double getQuantite() {
		return quantite;
    }

    public void setQuantite(Double quantite) {
		this.quantite = quantite;
    }
	
    public String getArtDesignation() {
		return artDesignation;
    }

    public void setArtDesignation(String artDesignation) {
		this.artDesignation = artDesignation;
    }

	
    @Override
    public String toString() {
        return "Document - Ref: " + reference + ", Article: " + artDesignation;
    }
}
