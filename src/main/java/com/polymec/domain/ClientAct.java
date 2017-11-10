package com.polymec.domain;

import static javax.persistence.GenerationType.SEQUENCE;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.text.SimpleDateFormat;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
    name="listClientActs",
    classes={
        @ConstructorResult(
            targetClass=ClientAct.class,
            columns={
                @ColumnResult(name="date", type = Date.class),
                @ColumnResult(name="dateModif", type = Date.class),
                @ColumnResult(name="type", type = String.class),
                @ColumnResult(name="numero", type = String.class),
                @ColumnResult(name="nom", type = String.class),
                @ColumnResult(name="qte", type = Double.class),
                @ColumnResult(name="puttx", type = Double.class),
                @ColumnResult(name="mntAct", type = Double.class),
                @ColumnResult(name="mntReg", type = Double.class),
                @ColumnResult(name="marge", type = Double.class)                            
            }
        )
    }
)

@Entity
@NamedNativeQueries({
@NamedNativeQuery(name="ClientAct.listArticlesBlVentes", 
        query="select 	blv_dl date, b.ue dateModif, 'BL' type, blv_no numero, arts_le nom,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) puttx,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m where m.mvt_be = b.blv_num and m.sr = 0) mntAct,\n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_be = b.blv_num and r.sr = 0) mntReg,\n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from blv b	join mvt on blv_num = mvt_be and mvt.sr = 0\n" +
"					join artfrns on mvt_ar = ArtFrns_Num\n" +
"					join arts on arts_num = ArtFrns_ae\n" +
"					join clts c on c.clts_num = b.blv_ct\n" +
"		where b.blv_fe is null and b.sr = 0 and c.clts_num = :cltId", resultSetMapping="listClientActs"),
@NamedNativeQuery(name="ClientAct.listArticlesFactVentes", 
        query="select 	ftrev_de date, f.ue dateModif, 'FACTURE' type, ftrev_no numero, arts_le nom,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) puttx,\n" +
"		((select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) \n" +
"			from mvt m where m.mvt_fe = f.ftrev_num and m.sr = 0) + \n" +
"				case when f.ftrev_te = 0 then 0 else 0.5 end) mntAct,\n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_fe = f.ftrev_num and r.sr = 0)  mntReg,\n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from ftrev f join mvt on ftrev_num = mvt_fe and mvt.sr = 0\n" +
"				join artfrns on mvt_ar = ArtFrns_Num\n" +
"				join arts on arts_num = ArtFrns_ae\n" +
"				join clts c on c.clts_num = f.ftrev_ct\n" +
"	where f.sr = 0 and c.clts_num = :cltId", resultSetMapping="listClientActs")    
})
public class ClientAct implements Comparable<ClientAct>, Serializable {

    private Long id;
    private String date;
    private String dateModif;
    private String type;
    private String numero;
    private String nom;
    private double qte;
    private double puttx;
    private Double mntAct;
    private Double mntReg; 
    private double marge;

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

   public ClientAct(Date date, Date dateModif, String type, String num, String nom, Double qte, Double puttx, Double mntAct, Double mntReg, Double marge) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        this.date = (date == null ? null : dateFormat.format(date));
        this.dateModif = (dateModif == null ? null : dateFormat.format(dateModif));
        this.type = type;
        if(type.equals("FACTURE"))
        {
            this.numero = "F".concat(num);
        }
        else if(type.equals("BL"))
        {
            this.numero = "BL".concat(num);
        }
        else
            this.numero = num;
        //this.numero = ("FACTURE".equals(type) ? "F".concat(num) : "BL".concat(num));
        this.nom = nom;
        this.qte = qte;
        // calculer le prix unitaire ttc de l' article avec remise        
        this.puttx = puttx;
        //calculer le gain en pourcentage
            this.marge = marge;
        this.mntAct = mntAct;
        this.mntReg = (mntReg == null ? null : mntReg);
        //this.reglement = reglement;        
    }
   
    public ClientAct(Date date, Date dateModif, String type, String num, String nom, double qte, double puaht, double puht, double remise, double ta, Double mntAct, Double mntReg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        this.date = (date == null ? null : dateFormat.format(date));
        this.dateModif = (dateModif == null ? null : dateFormat.format(dateModif));
        this.type = type;
        if(type.equals("FACTURE"))
        {
            this.numero = "F".concat(num);
        }
        else if(type.equals("BL"))
        {
            this.numero = "BL".concat(num);
        }
        else
            this.numero = num;
        //this.numero = ("FACTURE".equals(type) ? "F".concat(num) : "BL".concat(num));
        this.nom = nom;
        this.qte = qte;
        // calculer le prix unitaire ttc de l' article avec remise        
        this.puttx = (puht * (1 - remise / 100)) * (1 + ta / 100);
        //calculer le gain en pourcentage
        if (puaht > 0) {
            this.marge = this.round((((puht * (1 - remise / 100)) / puaht - 1) * 100), 2);
        } else {
            this.marge = 0.0;
        }
        this.mntAct = mntAct;
        this.mntReg = (mntReg == null ? null : mntReg);
        //this.reglement = reglement;        
    }

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateModif() {
        return this.dateModif;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPuttx() {
        return this.puttx;
    }

    public void setPuttx(double puttx) {
        this.puttx = puttx;
    }

    public Double getMntAct() {
        return this.mntAct;
    }

    public void setMntAct(Double mntAct) {
        this.mntAct = mntAct;
    }

    public Double getMntReg() {
        return this.mntReg;
    }

    public void setMntReg(Double mntReg) {
        this.mntReg = mntReg;
    }
   
    public double getMarge() {
        return this.marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }

    @Override
    public int compareTo(ClientAct n) {
        /*
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
         */
        //return n.getNumero().compareTo(this.numero);
        int lastCmp = n.date.compareTo(this.date);
        return (lastCmp != 0 ? lastCmp : n.getNumero().compareTo(this.numero));
    }

    @Override
    public String toString() {
        return "Act N°: " + numero;
    }
}
