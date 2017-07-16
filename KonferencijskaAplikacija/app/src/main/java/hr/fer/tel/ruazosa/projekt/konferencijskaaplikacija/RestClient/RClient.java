package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient;

import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Conference;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presentation;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presenter;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Sponsor;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

// https://futurestud.io/tutorials/retrofit-getting-started-and-android-client

/**
 * Created by Maltar on 7.6.2017..
 */

public interface RClient {
    @GET("/conferences")
    void getConferences(Callback<List<Conference>> callback);

    @GET("/conferences/{id}/presenters")
    void getPresenters(@Path("id") Long id, Callback<List<Presenter>> callback);

    @GET("/presenters/{id}")
    void getSinglePresenter(@Path("id") Long id, Callback<Presenter> callback);

    @GET("/conferences/{id}")
    void getSingleConference(@Path("id") Long id, Callback<Conference> callback);

    @GET("/conferences/{id}/sponsors")
    void getSponsors(@Path("id") Long id, Callback<List<Sponsor>> callback);

    @GET("/conferences/{id}/presentations")
    void getPresentations(@Path("id") Long id, Callback<List<Presentation>> callback);
}
