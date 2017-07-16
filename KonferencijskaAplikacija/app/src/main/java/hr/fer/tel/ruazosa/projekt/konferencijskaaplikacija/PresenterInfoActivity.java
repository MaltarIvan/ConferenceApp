package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient.RClient;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Conference;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presenter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Ivan on 7/9/2017.
 */

public class PresenterInfoActivity extends AppCompatActivity {

    private static final String API_BASE_URL = "https://conferenceapp.mybluemix.net/";
    private Long currentConferenceId;
    private Long presenterId;
    private Presenter currentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenters_list);

        Intent intent = getIntent();
        currentConferenceId = intent.getLongExtra("conference_id", -1);
        presenterId = intent.getLongExtra("presenter_id", -1);

        getCurrentPresenter(presenterId);

        TextView presenterName = (TextView) findViewById(R.id.presenter_name);
        TextView presenterTitle = (TextView) findViewById(R.id.presenter_title);
        TextView presenterBiography = (TextView) findViewById(R.id.presenter_biography);

        presenterName.setText(currentPresenter.getName()+" "+currentPresenter.getSurname());
        presenterTitle.setText(currentPresenter.getTitle());
        presenterBiography.setText(currentPresenter.getBiography());
    }

    private void getCurrentPresenter(long id) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(new OkClient(new OkHttpClient()));
        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);

        client.getSinglePresenter(id, new Callback<Presenter>() {
            @Override
            public void success(Presenter presenter, Response response) {
                currentPresenter = presenter;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
            }
        });
    }
}
