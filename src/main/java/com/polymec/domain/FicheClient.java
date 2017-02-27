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
/*
@SqlResultSetMapping(
    name="listClientActs",
    classes={
        @ConstructorResult(
            targetClass=FicheClient.class,
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

@Entity
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
public class FicheClient {

   private Long id;
    private String raison = "ben arous";
    private Double mntTotActs = 685.274;
    private Double mntTotRegs = 560.120;
    private Double mntTotRest = 125.154;

    public FicheClient() {
    }

    public FicheClient(String raison, Double mntTotActs, Double mntTotRegs) 
    {
        this.raison = raison;
        this.mntTotActs = mntTotActs;
        this.mntTotRegs = mntTotRegs;
        this.mntTotRest = this.mntTotActs - this.mntTotRegs;
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
     * @return the raison
     */
    public String getRaison() {
        return raison;
    }

    /**
     * @param raison the raison to set
     */
    public void setRaison(String raison) {
        this.raison = raison;
    }

    /**
     * @return the mntTotActs
     */
    public Double getMntTotActs() {
        return mntTotActs;
    }

    /**
     * @param mntTotActs the mntTotActs to set
     */
    public void setMntTotActs(Double mntTotActs) {
        this.mntTotActs = mntTotActs;
    }

    /**
     * @return the mntTotRegs
     */
    public Double getMntTotRegs() {
        return mntTotRegs;
    }

    /**
     * @param mntTotRegs the mntTotRegs to set
     */
    public void setMntTotRegs(Double mntTotRegs) {
        this.mntTotRegs = mntTotRegs;
    } 

    /**
     * @return the mntTotRest
     */
    public Double getMntTotRest() {
        return mntTotRest;
    }

    /**
     * @param mntTotRest the mntTotRest to set
     */
    public void setMntTotRest(Double mntTotRest) {
        this.mntTotRest = mntTotRest;
    }
    
}
