package com.polymec.domain.db;

import com.polymec.domain.db.Article;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

// @Entity, which means that this is a mapped entity class
@Entity
// @Table annotation defines the table name in the database that this entity is being mapped to.
// You can skip the table and column names if the type
// and attribute names are exactly the same as the table and column names.
@Table(name = "artfrns")
/*@NamedQueries({
    @NamedQuery(name="ArticleFrns.findById",
        query="select distinct c from ArticleFrns c where c.id = :id")
})*/
public class ArticleFrns implements Serializable {

    private Long id;
    private Double quantite;
    private Article article = null;
    private Fournisseur fournisseur;

    //private List<Mouvement> mvts;
    private List<Inventaire> inventaires;	
    private int sr;

    /*
	@Id. This means it’s the primary key of the object.
	JPA will use it as the unique identifier when managing the article entity instances
	within its session. Additionally, the @GeneratedValue annotation tells JPA how the id
	value was generated. The IDENTITY strategy means that the id was generated by the backend
	during insert.
     */
    @Id
    // in case of database oracle, these 2 line replace @GeneratedValue(strategy = IDENTITY)
    //@GeneratedValue(strategy=SEQUENCE, generator = "id_Sequence")
    //@SequenceGenerator(name = "id_Sequence", sequenceName = "seq_article")	
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ArtFrns_Num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "ArtFrns_ae")
    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(name = "ArtFrns_qe")
    public Double getQuantite() {
        return this.quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    @Column(name = "sr")
    public int getSr() {
        return this.sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    @ManyToOne
    @JoinColumn(name = "ArtFrns_fr")
    public Fournisseur getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    /*
	@OneToMany(mappedBy = "articleFrns")
	public List<Mouvement> getMvts() {
		return this.mvts;
	}
	
	public void setMvts(List<Mouvement> mvts) {
		this.mvts = mvts;
	}
*/
	@OneToMany(mappedBy = "articleFrns")
	public List<Inventaire> getInventaires() {
		return this.inventaires;
	}
	public void setInventaires(List<Inventaire> inventaires) {
		this.inventaires = inventaires;
	}
     
    @Override
    public String toString() {
        return "Quantite: " + quantite;
    }
}
