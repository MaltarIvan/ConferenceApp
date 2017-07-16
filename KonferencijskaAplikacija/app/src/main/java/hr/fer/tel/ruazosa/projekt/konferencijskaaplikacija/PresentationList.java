package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters.PresentationAdapter;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient.RClient;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presentation;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Maltar on 7.6.2017..
 */

public class PresentationList extends AppCompatActivity {
    private static final String API_BASE_URL = "https://conferenceapp.mybluemix.net/";
    private PresentationAdapter presentationAdapter;
    private ListView presentationListView;
    private String currentConferenceName;
    private Long currentConferenceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_list);

        Intent intent = getIntent();
        currentConferenceId = intent.getLongExtra("conference_id", -1);
        currentConferenceName = intent.getStringExtra("conference_name");

        TextView currentConferenceNameTextView = (TextView) findViewById(R.id.presentation_list_conference_name);
        currentConferenceNameTextView.setText(currentConferenceName);
        
        presentationListView = (ListView) findViewById(R.id.presentation_list_list);
        presentationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //// TODO: 1.7.2017. pokreni aktivnost prikaza pojedinog predavanja 
            }
        });

        loadPresentations();
    }

    private void loadPresentations() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(
                        new OkClient(new OkHttpClient())
                );

        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);
        
        client.getPresentations(currentConferenceId, new Callback<List<Presentation>>() {
            @Override
            public void success(List<Presentation> presentations, Response response) {
                presentationAdapter = new PresentationAdapter(getApplicationContext(), presentations);
                presentationListView.setAdapter(presentationAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
            }
        });
    }
}
