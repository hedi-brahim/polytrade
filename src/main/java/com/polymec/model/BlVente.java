package com.polymec.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import java.sql.Timestamp;

import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "blv")
public class BlVente implements Serializable {
    private Long id;

    private Timestamp date;
    private String numero;
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
	public Timestamp getDate() {
		return this.date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
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
