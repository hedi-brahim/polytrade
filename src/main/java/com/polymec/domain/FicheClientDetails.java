/*
 * Copyright 2017 Pivotal Software, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.polymec.domain;

import com.polymec.domain.ClientAct;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author Hedi
 */

@SqlResultSetMapping(
    name="listFicheClientActs",
    classes={
        @ConstructorResult(
            targetClass=FicheClientDetails.class,
            columns={
                @ColumnResult(name="date", type = Date.class),
                @ColumnResult(name="type", type = String.class),
                @ColumnResult(name="numero", type = String.class),
                @ColumnResult(name="reference", type = String.class),                
                @ColumnResult(name="designation", type = String.class),
                @ColumnResult(name="qte", type = Double.class),
                @ColumnResult(name="pu", type = Double.class),
                @ColumnResult(name="mntAct", type = Double.class),
                @ColumnResult(name="mntReg", type = Double.class),
                @ColumnResult(name="marge", type = Double.class)                            
            }
        )
    }
)

@Entity
@NamedNativeQueries({
@NamedNativeQuery(name="FicheClientDetails.listArticlesBlVentes", 
        query="select 	blv_dl date, 'BL' type, blv_no numero, arts_ne reference, arts_le designation,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) pu,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m where m.mvt_be = b.blv_num and m.sr = 0) mntAct,\n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_be = b.blv_num and r.sr = 0) mntReg,\n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from blv b	join mvt on blv_num = mvt_be and mvt.sr = 0\n" +
"					join artfrns on mvt_ar = ArtFrns_Num\n" +
"					join arts on arts_num = ArtFrns_ae\n" +
"					join clts c on c.clts_num = b.blv_ct\n" +
"		where b.blv_fe is null and b.sr = 0 and c.clts_num = :cltId", resultSetMapping="listClientActs"),
@NamedNativeQuery(name="FicheClientDetails.listArticlesFactVentes", 
        query="select 	ftrev_de date, f.ue dateModif, 'FT' type, ftrev_no numero, arts_ne reference, arts_le designation,\n" +
"		mvt_qt qte, (mvt_pt * (1 - mvt_re/100) * (1 + mvt_ta/100)) pu,\n" +
"		(select sum(mvt_pt*mvt_qt*(1-mvt_re/100)*(1+mvt_ta/100)) from mvt m where m.mvt_fe = f.ftrev_num and m.sr = 0) mntAct,\n" +
"		(select sum(rgmt_mt) from rgmt r where r.rgmt_fe = f.ftrev_num and r.sr = 0) mntReg,\n" +
"		if(arts_pat>0,((mvt_pt * (1 - mvt_re/100))/arts_pat - 1)*100,0.0) marge\n" +
"		from ftrev f join mvt on ftrev_num = mvt_fe and mvt.sr = 0\n" +
"				join artfrns on mvt_ar = ArtFrns_Num\n" +
"				join arts on arts_num = ArtFrns_ae\n" +
"				join clts c on c.clts_num = f.ftrev_ct\n" +
"	where f.sr = 0 and c.clts_num = :cltId", resultSetMapping="listFicheClientActs")    
})
public class FicheClientDetails implements Comparable<FicheClientDetails>, Serializable {

    private Long id;
    private String date;
    private String type;    
    private String numero;
    private String reference;
    private String designation;
    private Double qte;
    private Double pu;
    private Double mntAct;
    private Double mntReg;    
    private Double marge;

    public FicheClientDetails() {
    }

    public FicheClientDetails(String date, String type, String numero, String reference, String designation, Double qte, Double pu, Double mntAct, Double mntReg, Double marge) {
        this.date = date;
        this.type = type;
        this.numero = numero;
        this.reference = reference;
        this.designation = designation;
        this.qte = qte;
        this.pu = pu;
        this.mntAct = mntAct;
        this.mntReg = mntReg;
        this.marge = marge;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
 
    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
       
    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the qte
     */
    public Double getQte() {
        return qte;
    }

    /**
     * @param qte the qte to set
     */
    public void setQte(Double qte) {
        this.qte = qte;
    }

    /**
     * @return the pu
     */
    public Double getPu() {
        return pu;
    }

    /**
     * @param pu the pu to set
     */
    public void setPu(Double pu) {
        this.pu = pu;
    }

    /**
     * @return the mntAct
     */
    public Double getMntAct() {
        return mntAct;
    }

    /**
     * @param mntAct the mntAct to set
     */
    public void setMntAct(Double mntAct) {
        this.mntAct = mntAct;
    }

    /**
     * @return the mntReg
     */
    public Double getMntReg() {
        return mntReg;
    }

    /**
     * @param mntReg the mntReg to set
     */
    public void setMntReg(Double mntReg) {
        this.mntReg = mntReg;
    }

    /**
     * @return the marge
     */
    public Double getMarge() {
        return marge;
    }

    /**
     * @param marge the marge to set
     */
    public void setMarge(Double marge) {
        this.marge = marge;
    }

    @Override
    public int compareTo(FicheClientDetails n) {
        int lastCmp = n.date.compareTo(this.date);
        return (lastCmp != 0 ? lastCmp : n.getNumero().compareTo(this.numero));
    }

}
