package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Patrik on 15-Jun-17.
 */

@Entity
@Table(name="sponzori")
public class Sponsors {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="sponzori_sponzori_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="sponzori_id", unique=true, nullable=false)
    private long id;

    //za ovo nisam siguran jeli treba biti byte[]
    @Column(name="logo")
    private String logo;

    @Column(name="naziv")
    private String sponsorName;

    @Column(name="sluzbena_stranica")
    private String officialSite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    @Override
    public String toString() {
        return "Sponsors{" +
                "id=" + id +
                ", logo=" + Arrays.toString(logo) +
                ", sponsorName='" + sponsorName + '\'' +
                ", officialSite='" + officialSite + '\'' +
                '}';
    }
}
