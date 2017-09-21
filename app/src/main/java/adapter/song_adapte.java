package adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vukhachoi.muisicapp.R;

import java.util.List;

import model.song;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class song_adapte extends ArrayAdapter<song>  {
    Activity context;
    int resource;
    List<song> objects;




    public song_adapte(Activity context, int resource, List<song> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);





        TextView textView = row.findViewById(R.id.txtname);
        TextView textView1 = row.findViewById(R.id.txtArtist);
        ImageView imageView = row.findViewById(R.id.imgAvatar);


        final song hoa = this.objects.get(position);
        textView.setText(hoa.getName());
textView1.setText(hoa.getArtist());
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        byte[] rawArt;
        Bitmap art;
        BitmapFactory.Options bfo=new BitmapFactory.Options();

        mmr.setDataSource(getContext(), Uri.parse(hoa.getPath()) );
        rawArt = mmr.getEmbeddedPicture();


        if (null != rawArt)

        {
            art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
            imageView.setImageBitmap(art);
        }
        return row;
    }
}