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
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presentation;

/**
 * Created by Maltar on 1.7.2017..
 */

public class PresentationAdapter extends ArrayAdapter<Presentation> {

    public PresentationAdapter(Context context, List<Presentation> presentations) {
        super(context, 0, presentations);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.presentation_list_item, parent, false);
        }

        Presentation currentPresentation = getItem(position);

        TextView dateAndTime = (TextView) listItemView.findViewById(R.id.presentation_list_date);
        TextView title = (TextView) listItemView.findViewById(R.id.presentation_list_title);

        dateAndTime.setText(formatDate(currentPresentation.getPresentingDate()) + ", " + currentPresentation.getStartingTime());
        title.setText(currentPresentation.getPresentationName());

        return listItemView;
    }

    private String formatDate(long dateLong) {
        Date date = new Date(dateLong);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. LLL yyyy");
        return simpleDateFormat.format(date);
    }
}
