package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities;

/**
 * Created by Maltar on 30.5.2017..
 */


import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// http://mobilesiri.com/retrofit-tutorial-android-studio/

public class Conference {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dateOfBeginning")
    @Expose
    private long dateOfBeginning;
    @SerializedName("dateOEnd")
    @Expose
    private long dateOEnd;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("conferenceName")
    @Expose
    private String conferenceName;
    @SerializedName("conferenceDescription")
    @Expose
    private String conferenceDescription;

    public Conference(Integer id, long dateOfBeginning, long dateOEnd, String location, String conferenceName, String conferenceDescription) {
        this.id = id;
        this.dateOfBeginning = dateOfBeginning;
        this.dateOEnd = dateOEnd;
        this.location = location;
        this.conferenceName = conferenceName;
        this.conferenceDescription = conferenceDescription;
        Log.d("LOG", "tu sam");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(long dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
        Log.d("LOG", "setter");
    }

    public long getDateOEnd() {
        return dateOEnd;
    }

    public void setDateOEnd(long dateOEnd) {
        this.dateOEnd = dateOEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

}