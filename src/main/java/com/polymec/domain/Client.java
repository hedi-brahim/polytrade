package com.polymec.domain;

import com.polymec.domain.BlVente;
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
import java.util.Date;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
//import com.google.common.collect.Lists;
/*
@SqlResultSetMapping(
    name="listClientActs",
    classes={
        @ConstructorResult(
            targetClass=Client.class,
            columns={
                @ColumnResult(name="date", type = Date.class),
                @ColumnResult(name="dateModif", type = Date.class),
                @ColumnResult(name="type", type = String.class),
                @ColumnResult(name="numero", type = String.class),
                @ColumnResult(name="nom", type = String.class),
                @ColumnResult(name="qte", type = Double.class),
                @ColumnResult(name="puttx", type = Double.class),
                @ColumnResult(name="mntAct", type = Double.class),
                @ColumnResult(name="mntActs", type = Double.class),                
                @ColumnResult(name="mntReg", type = Double.class),
                @ColumnResult(name="mntRegs", type = Double.class),                
                @ColumnResult(name="marge", type = Double.class)                            
            }
        )
    }
)

@NamedNativeQueries({
@NamedNativeQuery(name="ClientAct.listArticlesBlVentes", 
        query="select 	blv_dl date, b.ue dateModif, 'BL' type, blv_no numero, arts_le nom,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) puttx,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m where m.mvt_be = b.blv_num and m.sr = 0) mntAct,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m1 \n" +
"			join blv b1 on m1.mvt_be = b1.blv_num \n" +
"            join clts c1 on c1.clts_num = b1.blv_ct\n" +
"            where c1.clts_num = c.clts_num and b1.blv_fe is null and b1.sr = 0 and m1.sr = 0) mntActs,               \n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_be = b.blv_num and r.sr = 0) mntReg,\n" +
"		(select sum(rgmt_mt) from rgmt r1\n" +
"			join blv b2 on r1.rgmt_be = b2.blv_num\n" +
"            join clts c2 on c2.clts_num = b2.blv_ct \n" +
"            where c2.clts_num = c.clts_num and b2.blv_fe is null and b2.sr = 0 and r1.sr = 0) mntRegs,             \n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from blv b	join mvt on blv_num = mvt_be and mvt.sr = 0\n" +
"					join artfrns on mvt_ar = ArtFrns_Num\n" +
"					join arts on arts_num = ArtFrns_ae\n" +
"					join clts c on c.clts_num = b.blv_ct\n" +
"		where b.blv_fe is null and b.sr = 0 and c.clts_num = :cltId", resultSetMapping="listClientActs"),
@NamedNativeQuery(name="ClientAct.listArticlesFactVentes", 
        query="select 	ftrev_de date, f.ue dateModif, 'FACTURE' type, ftrev_no numero, arts_le nom,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) puttx,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m where m.mvt_fe = f.ftrev_num and m.sr = 0) mntAct,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m1 \n" +
"			join ftrev f1 on m1.mvt_fe = f1.ftrev_num \n" +
"            join clts c1 on c1.clts_num = f1.ftrev_ct\n" +
"            where c1.clts_num = c.clts_num and f1.sr = 0 and m1.sr = 0) mntActs,              \n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_fe = f.ftrev_num and r.sr = 0) mntReg,\n" +
"		(select sum(rgmt_mt) from rgmt r1\n" +
"			join ftrev f2 on r1.rgmt_fe = f2.ftrev_num\n" +
"            join clts c2 on c2.clts_num = f2.ftrev_ct \n" +
"            where c2.clts_num = c.clts_num and f2.sr = 0 and r1.sr = 0) mntRegs,           \n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from ftrev f join mvt on ftrev_num = mvt_fe and mvt.sr = 0\n" +
"				join artfrns on mvt_ar = ArtFrns_Num\n" +
"				join arts on arts_num = ArtFrns_ae\n" +
"				join clts c on c.clts_num = f.ftrev_ct\n" +
"	where f.sr = 0 and c.clts_num = :cltId", resultSetMapping="listClientActs")    
})
 */
@Entity
@Table(name = "clts")
public class Client implements Serializable {

    private Long id;
    private String raison;
    private String tel;
    private String gsm;
    private String fax;
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

    @Column(name = "clts_tl")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "clts_gm")
    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
    
    @Column(name = "clts_fx")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
