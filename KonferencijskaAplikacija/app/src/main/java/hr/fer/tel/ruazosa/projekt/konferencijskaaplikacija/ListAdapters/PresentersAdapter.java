package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.R;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Presenter;

/**
 * Created by Ivan on 7/4/2017.
 */

public class PresentersAdapter extends ArrayAdapter<Presenter> {
    public PresentersAdapter(Context context, List<Presenter> presenters) {
        super(context, 0, presenters);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.presenters_list_item, parent, false);
        }

        Presenter currentPresenter = getItem(position);

        TextView presenterName = (TextView) listItemView.findViewById(R.id.presenter_list_presenter_name);
        presenterName.setText(currentPresenter.getName() + " " + currentPresenter.getSurname());
        TextView title = (TextView) listItemView.findViewById(R.id.presenter_title);
        title.setText(currentPresenter.getTitle());

        return listItemView;
    }
}
