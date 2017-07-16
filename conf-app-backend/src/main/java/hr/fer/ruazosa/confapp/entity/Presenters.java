package hr.fer.ruazosa.confapp.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Patrik on 16-Jun-17.
 */

@Entity
@Table(name = "predavaci")
public class Presenters {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="predavac_predavac_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="predavac_id", unique=true, nullable=false)
    private long id;

    @Column(name = "imepredavac")
    private String name;

    @Column(name = "prezimepredavac")
    private String surname;

    @Column(name = "biografija")
    private String biography;

    @Column(name = "titula")
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Presenters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", biography='" + biography + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
