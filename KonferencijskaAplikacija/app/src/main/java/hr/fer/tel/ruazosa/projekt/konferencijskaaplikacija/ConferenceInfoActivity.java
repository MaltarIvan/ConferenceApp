package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters.SponsorAdapter;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.RestClient.RClient;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Conference;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Sponsor;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Maltar on 7.6.2017..
 */

public class ConferenceInfoActivity extends AppCompatActivity {
    private static final String API_BASE_URL = "https://conferenceapp.mybluemix.net/";
    private static final String URI_BASE = "geo:0,0?q=";
    private Conference currentConference;
    private long currentConferenceId;

    private ListView sponsorsListView;
    private SponsorAdapter sponsorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_info);

        Intent intent = getIntent();
        currentConferenceId = intent.getLongExtra("conference_id", -1);
        sponsorsListView = (ListView) findViewById(R.id.conference_info_conference_sponsors);
        getCurrentConference(currentConferenceId);

        sponsorsListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        sponsorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = sponsorAdapter.getItem(position).getOfficialSite();
                if (URLUtil.isValidUrl(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        Button mapButton = (Button) findViewById(R.id.conference_info_show_on_map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri intentUri = createMapIntentUri();
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Button presentationsButton = (Button) findViewById(R.id.conference_info_predavanja_button);
        presentationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConferenceInfoActivity.this, PresentationList.class);
                intent.putExtra("conference_id", currentConferenceId);
                intent.putExtra("conference_name", currentConference.getConferenceName());
                startActivity(intent);
            }
        });
        
        Button presentersButton = (Button) findViewById(R.id.conference_info_predavaci_button);
        presentersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConferenceInfoActivity.this, PresentersListActivity.class);
                intent.putExtra("conference_id", currentConferenceId);
                intent.putExtra("conference_name", currentConference.getConferenceName());
                startActivity(intent);
            }
        });
    }

    private void getCurrentConference(long id) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(new OkClient(new OkHttpClient()));
        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);

        client.getSingleConference(id, new Callback<Conference>() {
            @Override
            public void success(Conference conference, Response response) {
                currentConference = conference;
                getSponsors(currentConferenceId);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
            }
        });
    }

    private void getSponsors(long id) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setClient(new OkClient(new OkHttpClient()));
        RestAdapter restAdapter = builder.build();
        RClient client = restAdapter.create(RClient.class);

        client.getSponsors(id, new Callback<List<Sponsor>>() {
            @Override
            public void success(List<Sponsor> sponsors, Response response) {
                if (sponsors.size() > 0) {
                    TextView textView = (TextView) findViewById(R.id.conference_info_sponsors_text_view);
                    textView.setText("Sponzori:");
                }
                sponsorAdapter = new SponsorAdapter(getApplicationContext(), sponsors);
                sponsorsListView.setAdapter(sponsorAdapter);
                setSponsorsListViewHeight();
                setUpUI();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("REST call", "rest call failure:" + error);
            }
        });
    }

    private Uri createMapIntentUri() {
        String currentLocation = currentConference.getLocation();
        String[] queryParts = currentLocation.split(", ");
        String str = URI_BASE;
        int i;
        for (i = 0; i < queryParts.length - 1; i++){
            str += (queryParts[i] + "+");
        }
        str += queryParts[i];

        return Uri.parse(str);
    }

    private void setUpUI() {
        TextView conferenceName = (TextView) findViewById(R.id.conference_info_name);
        TextView conferenceLocation = (TextView) findViewById(R.id.conference_info_location);
        TextView conferenceInfo = (TextView) findViewById(R.id.conference_info_conference_info);
        TextView conferenceBeginDate = (TextView) findViewById(R.id.conference_info_date);

        conferenceName.setText(currentConference.getConferenceName());
        conferenceLocation.setText(currentConference.getLocation());
        conferenceInfo.setText(currentConference.getConferenceDescription());
        conferenceBeginDate.setText(formatDate(currentConference.getDateOfBeginning()) + " do " + formatDate(currentConference.getDateOEnd()));
        ScrollView scrollView = (ScrollView) findViewById(R.id.conference_info_scroll_view);
        scrollView.smoothScrollTo(0, 0);
     }

    private String formatDate(long dateLong) {
        Date date = new Date(dateLong);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. LLL yyyy");
        return simpleDateFormat.format(date);
    }

    private void setSponsorsListViewHeight() {
        ListAdapter listAdapter = sponsorAdapter;
        if (listAdapter == null) return;

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(sponsorsListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, sponsorsListView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = sponsorsListView.getLayoutParams();
        params.height = totalHeight + (sponsorsListView.getDividerHeight() * (listAdapter.getCount() - 1));
        sponsorsListView.setLayoutParams(params);
    }
}
