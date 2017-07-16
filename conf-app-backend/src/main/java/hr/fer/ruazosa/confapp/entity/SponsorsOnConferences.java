package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Patrik on 16-Jun-17.
 */

@Entity
@Table(name = "sponzori_po_konferencijama")
public class SponsorsOnConferences implements Serializable {

    @Id
    @Column(name = "sponzori_id", unique = true, nullable = false)
    private long sponsorId;

    @Id
    @Column(name = "konferencija_id", unique = true, nullable = false)
    private long conferenceId;

    public long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(long conferenceId) {
        this.conferenceId = conferenceId;
    }
}
