package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Field;
import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters.ConferenceAdapter;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient.RClient;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Conference;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

//https://futurestud.io/tutorials/retrofit-getting-started-and-android-client

/**
 * Created by Maltar on 7.6.2017..
 */

public class ConferenceListActivity extends AppCompatActivity {
    private static final String API_BASE_URL = "https://conferenceapp.mybluemix.net/";
    private ConferenceAdapter conferenceAdapter;
    private ListView conferenceListView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list);

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        conferenceListView = (ListView) findViewById(R.id.conference_list);

        conferenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConferenceListActivity.this, ConferenceInfoActivity.class);
                long currentConferenceId = conferenceAdapter.getItem(position).getId();
                intent.putExtra("conference_id", currentConferenceId);
                startActivity(intent);
            }
        });

        loadAllConferences();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshConferencesList();
            }
        });
    }

    private void loadAllConferences() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(
                        new OkClient(new OkHttpClient())
                );

        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);

        client.getConferences(new Callback<List<Conference>>(){
            @Override
            public void success(List<Conference> conferences, Response response) {
                conferenceAdapter = new ConferenceAdapter(getApplicationContext(), conferences);
                conferenceListView.setAdapter(conferenceAdapter);
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void refreshConferencesList() {
        loadAllConferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conference_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_conferences_item:
                refreshConferencesList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
