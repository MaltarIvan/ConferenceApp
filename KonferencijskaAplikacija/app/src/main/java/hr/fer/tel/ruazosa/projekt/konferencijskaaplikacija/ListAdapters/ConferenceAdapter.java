package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.R;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Conference;

/**
 * Created by Maltar on 7.6.2017..
 */

public class ConferenceAdapter extends ArrayAdapter<Conference> {
    public ConferenceAdapter(Context context, List<Conference> conferences) {
        super(context, 0, conferences);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.conference_list_item, parent, false);
        }

        Conference currentConference = getItem(position);

        TextView date = (TextView) listItemView.findViewById(R.id.conference_list_date);
        String formattedDate = formatDate(currentConference.getDateOfBeginning());
        date.setText(formattedDate);
        TextView conferenceName = (TextView) listItemView.findViewById(R.id.conference_list_conference_name);
        conferenceName.setText(currentConference.getConferenceName());
        TextView location = (TextView) listItemView.findViewById(R.id.conference_list_location);
        location.setText(currentConference.getLocation());

        return listItemView;
    }

    private String formatDate(long dateLong) {
        Date date = new Date(dateLong);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. LLL yyyy");
        return simpleDateFormat.format(date);
    }
}
