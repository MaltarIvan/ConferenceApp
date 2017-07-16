package hr.fer.ruazosa.confapp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Date;


/**
 * Created by dejannovak on 05/06/17.
 */

@Entity
@Table(name="konferencija")
public class Conference {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="konferencija_konferencija_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="konferencija_id", unique=true, nullable=false)
    private long id;

    @Column(name="datumpocetka")
    private Date dateOfBeginning;

    @Column(name="datumzavrsetka")
    private Date dateOfEnd;

    @Column(name="lokacija")
    private String location;

    @Column(name="naziv")
    private String conferenceName;

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceDescription() {
        return conferenceDescription;
    }

    public void setConferenceDescription(String conferenceDescription) {
        this.conferenceDescription = conferenceDescription;
    }

    @Column(name="osnovneinfo")
    private String conferenceDescription;

    public Date getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(Date dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public Date getDateOEnd() {
        return dateOfEnd;
    }

    public void setDateOEnd(Date dateOEnd) {
        this.dateOfEnd = dateOEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", dateOfBeginning=" + dateOfBeginning +
                ", dateOfEnd=" + dateOfEnd +
                ", location='" + location + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                ", conferenceDescription='" + conferenceDescription + '\'' +
                '}';
    }
}
