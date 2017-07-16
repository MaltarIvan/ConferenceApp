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

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters.PresentersAdapter;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient.RClient;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presenter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class PresentersListActivity extends AppCompatActivity {
    private static final String API_BASE_URL = "https://conferenceapp.mybluemix.net/";
    private PresentersAdapter presentersAdapter;
    private ListView presentersListView;
    private String currentConferenceName;
    private Long currentConferenceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenters_list);

        Intent intent = getIntent();
        currentConferenceId = intent.getLongExtra("conference_id", -1);
        currentConferenceName = intent.getStringExtra("conference_name");

        TextView currentConferenceNameTextView = (TextView) findViewById(R.id.presenters_list_conference_name);
        currentConferenceNameTextView.setText(currentConferenceName);

        presentersListView = (ListView) findViewById(R.id.presenters_list);
        presentersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(PresentersListActivity.this, PresenterInfoActivity.class);
                intent1.putExtra("conference_id", currentConferenceId);
                intent1.putExtra("presenter_id", presentersAdapter.getItem(position).getId());
                startActivity(intent1);
            }
        });

        loadPresenters();
    }

    private void loadPresenters() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(
                        new OkClient(new OkHttpClient())
                );

        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);

        client.getPresenters(currentConferenceId, new Callback<List<Presenter>>() {
            @Override
            public void success(List<Presenter> presenters, Response response) {

                presentersAdapter = new PresentersAdapter(getApplicationContext(), presenters);
                presentersListView.setAdapter(presentersAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
            }
        });
    }
}