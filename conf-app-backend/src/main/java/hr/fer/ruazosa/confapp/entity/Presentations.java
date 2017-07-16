package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Patrik on 15-Jun-17.
 */

@Entity
@Table(name = "predavanja")
public class Presentations {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="predavanja_predavanja_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="predavanja_id", unique=true, nullable=false)
    private long id;

    @Column(name = "konferencija_id")
    private long conferenceId;

    @Column(name = "datum_predavanja")
    private Date presentingDate;

    @Column(name = "naziv_predavanja")
    private String presentationName;

    @Column(name = "osnovne_info")
    private String presentationDescription;

    @Column(name = "vrijeme_predavanja")
    private Time startingTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Date getPresentingDate() {
        return presentingDate;
    }

    public void setPresentingDate(Date presentingDate) {
        this.presentingDate = presentingDate;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public void setPresentationName(String presentationName) {
        this.presentationName = presentationName;
    }

    public String getPresentationDescription() {
        return presentationDescription;
    }

    public void setPresentationDescription(String presentationDescription) {
        this.presentationDescription = presentationDescription;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    @Override
    public String toString() {
        return "Presentations{" +
                "id=" + id +
                ", conferenceId=" + conferenceId +
                ", presentingDate=" + presentingDate +
                ", presentationName=" + presentationName +
                ", presentationDescription='" + presentationDescription + '\'' +
                ", startingTime=" + startingTime +
                '}';
    }
}
