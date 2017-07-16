package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Patrik on 16-Jun-17.
 */

@Entity
@Table(name = "predavaci_po_predavanjima")
public class PresentersOnPresentations implements Serializable {

    @Id
    @Column(name = "predavac_id", unique = true, nullable = false)
    private long presenterId;

    @Id
    @Column(name = "predavanja_id", unique = true, nullable = false)
    private long presentationId;

    public long getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(long presenterId) {
        this.presenterId = presenterId;
    }

    public long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(long presentationId) {
        this.presentationId = presentationId;
    }
}
