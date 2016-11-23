package com.polymec.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "usr")
public class User implements Serializable {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private int enabled;
    private List<Role> roles = new ArrayList<Role>();    
    /*
    public User() {

    }

    public User(User user) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.password = user.password;
        this.email = user.email;
        this.enabled = user.enabled;
    }
*/
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "Usr_Num")
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    @Column(name = "Usr_ln")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "Usr_pd")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Usr_ae")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Usr_adm")
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "user")
    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return "User: " + userName + ", Email: " + email;
    }

}
