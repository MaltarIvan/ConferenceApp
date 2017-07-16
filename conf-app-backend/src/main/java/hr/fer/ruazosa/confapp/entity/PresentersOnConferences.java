package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Patrik on 16-Jun-17.
 */

@Entity
@Table(name = "predavaci_po_konferencijama")
public class PresentersOnConferences implements Serializable {


    //TODO: provjeri kada imas vieatributni kljuc jeli ga samo ovako oznacis kao sada ili moras jos nesta dodatno
    @Id
    @Column(name = "predavac_id", unique = true, nullable = false)
    private long presenterId;

    @Id
    @Column(name = "konferencija_id")
    private long conferenceId;

    public long getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(long presenterId) {
        this.presenterId = presenterId;
    }

    public long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(long conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public int hashCode(){
        return (int) (presenterId + conferenceId);
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof  PresentersOnConferences)) return false;
        PresentersOnConferences p = (PresentersOnConferences) object;
        if(this.conferenceId==p.conferenceId && this.presenterId==p.presenterId) return true;
        return false;
    }
}
