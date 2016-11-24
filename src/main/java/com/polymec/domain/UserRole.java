package com.polymec.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "rle")
public class UserRole implements Serializable {

    private Long id;
    private String role;
    private int sr;        
    private User user;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Rle_Num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "Rle_nr")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "sr",columnDefinition="default '0'")
    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }
    
    @ManyToOne
    @JoinColumn(name = "util")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role: " + role;
    }
}
