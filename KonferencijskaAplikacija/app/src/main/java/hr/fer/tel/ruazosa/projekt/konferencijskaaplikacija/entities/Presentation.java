package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maltar on 5.7.2017..
 */

public class Presentation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("conferenceId")
    @Expose
    private long conferenceId;
    @SerializedName("presentingDate")
    @Expose
    private long presentingDate;
    @SerializedName("presentationName")
    @Expose
    private String presentationName;
    @SerializedName("presentationDescription")
    @Expose
    private String presentationDescription;
    @SerializedName("startingTime")
    @Expose
    private String startingTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public long getPresentingDate() {
        return presentingDate;
    }

    public void setPresentingDate(Integer presentingDate) {
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

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }
}
