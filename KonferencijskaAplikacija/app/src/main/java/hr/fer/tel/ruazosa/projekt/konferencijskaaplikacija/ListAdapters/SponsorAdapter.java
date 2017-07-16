package hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.ListAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.R;
import hr.fer.tel.ruazosa.projekt.konferencijskaaplikacija.entities.Sponsor;

/**
 * Created by Maltar on 15.6.2017..
 */

public class SponsorAdapter extends ArrayAdapter<Sponsor> {

    public SponsorAdapter(Context context, List<Sponsor> sponsors) {
        super(context, 0, sponsors);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.conference_info_sponsors_list_item, parent, false);
        }

        Sponsor currentSponsor = getItem(position);

        TextView sponsorName = (TextView) listItemView.findViewById(R.id.conference_info_sponsor_name);
        ImageView sponsorImage = (ImageView) listItemView.findViewById(R.id.conference_info_sponsor_image);

        sponsorName.setText(currentSponsor.getSponsorName());
        sponsorImage.setImageBitmap(decodeBase64(currentSponsor.getLogo()));

        return listItemView;
    }

    private Bitmap decodeBase64(String input) {
        input = input.replace("data:image/jpeg;base64,", "");
        byte[] decodedByte = Base64.decode(input, 0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        return bitmap;
    }
}
