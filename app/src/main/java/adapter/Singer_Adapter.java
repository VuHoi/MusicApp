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

import model.singer;

/**
 * Created by Vu Khac Hoi on 9/21/2017.
 */

public class Singer_Adapter  extends ArrayAdapter<singer> {
    Activity context;
    int resource;
    List<singer> objects;




    public Singer_Adapter(Activity context, int resource, List<singer> objects) {
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





        TextView textView = row.findViewById(R.id.txtsinger1);

        ImageView imageView = row.findViewById(R.id.imgAvatarSinger);


        final singer hoa = this.objects.get(position);
        textView.setText(hoa.getName());

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        byte[] rawArt;
        Bitmap art;
        BitmapFactory.Options bfo=new BitmapFactory.Options();

        mmr.setDataSource(getContext(), Uri.parse(hoa.getAvatar()) );
        rawArt = mmr.getEmbeddedPicture();


        if (null != rawArt)

        {
            art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
            imageView.setImageBitmap(art);
        }
        return row;
    }
}