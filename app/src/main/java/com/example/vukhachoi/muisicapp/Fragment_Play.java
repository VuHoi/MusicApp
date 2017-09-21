package com.example.vukhachoi.muisicapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.song;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class Fragment_Play extends android.support.v4.app.Fragment{
    public  Fragment_Play(){}
    public static MediaPlayer mpintro;
    List<song>list;

    SeekBar timer;
    ImageView btnnext,btnprev,btnpause,btnstop;
    TextView timeStart,timeEnd;
    CircleImageView imgPlay;
    int index=0;

    TextView txttenbaihat;
    int size=0;
    RotateAnimation rotate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_music, container, false);
        imgPlay = view.findViewById(R.id.imgPlay);
        timeStart=view.findViewById(R.id.timeStart);
        timeEnd=view.findViewById(R.id.timeEnd);
        timer=view.findViewById(R.id.progressBar);
        btnnext=view.findViewById(R.id.nex);
        btnprev=view.findViewById(R.id.pre);
        btnpause=view.findViewById(R.id.pause);
        btnstop=view.findViewById(R.id.stop);
        txttenbaihat=view.findViewById(R.id.txttenbaihat);
        Bundle bundle = getArguments();

        if (bundle != null) {
            index =  bundle.getInt("baihat");
            list= (List<song>) bundle.getSerializable("listbaihat");

            createMusic(index);

        }
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setDuration(10000);
        rotate.setInterpolator(new LinearInterpolator());


        imgPlay.setAnimation(rotate);

timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mpintro.seekTo(seekBar.getProgress());
    }
});

btnnext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       if(index+1<=size-1)
       {
           index++;
           createMusic(index);
           try {
               imgPlay.setAnimation(rotate);
           }catch (Exception e){

           }
       }
    }
});

btnprev.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(index-1>=0)
        {
            index--;
            createMusic(index);
            try {
                imgPlay.setAnimation(rotate);
            }catch (Exception e){

            }
        }
    }
});

btnpause.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(!isstop) {
            if (mpintro.isPlaying()) {
                btnpause.setImageResource(R.drawable.play);
                mpintro.pause();
                isstop=false;
                imgPlay.clearAnimation();
                imgPlay.animate().cancel();
            } else {
                btnpause.setImageResource(R.drawable.pause);
                mpintro.start();
                isstop=false;
                imgPlay.setAnimation(rotate);
            }
        }
        else
        {
            mpintro = MediaPlayer.create(getActivity(), Uri.parse(list.get(index).getPath()));
            mpintro.setLooping(true);

            mpintro.start();

            imgPlay.setAnimation(rotate);
            btnpause.setImageResource(R.drawable.pause);

        }

    }
});

btnstop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        btnpause.setImageResource(R.drawable.play);
        mpintro.stop();
        isstop=true;
        timeStart.setText("00:00");
        imgPlay.clearAnimation();
        imgPlay.animate().cancel();






    }
});
        return view;
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        timer.setProgress(30);
//    }

    boolean isstop=false;
    private  void createMusic(int index1)
    {
btnpause.setImageResource(R.drawable.pause);
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        size=list.size();
        byte[] rawArt;
        Bitmap art;
        BitmapFactory.Options bfo = new BitmapFactory.Options();

        mmr.setDataSource(getContext(), Uri.parse(list.get(index1).getPath()));
        rawArt = mmr.getEmbeddedPicture();


        if (null != rawArt)

        {
            art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
            imgPlay.setImageBitmap(art);
        }
        if(mpintro!=null) {
            if (mpintro.isPlaying()) mpintro.stop();
        }
        mpintro = MediaPlayer.create(getActivity(), Uri.parse(list.get(index1).getPath()));
        mpintro.setLooping(true);

        mpintro.start();
        txttenbaihat.setText(list.get(index1).getName());
        SimpleDateFormat dinhdang=new SimpleDateFormat("mm:ss");
        timeEnd.setText(dinhdang.format(mpintro.getDuration()));
        timer.setMax(mpintro.getDuration());
        UpdateSong();
    }


  private   void UpdateSong()
    {
     final   Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                SimpleDateFormat dinhdang=new SimpleDateFormat("mm:ss");
                timeStart.setText(dinhdang.format(mpintro.getCurrentPosition()));
                timer.setProgress(mpintro.getCurrentPosition());
                handler.postDelayed(this,500);
                if(isstop) timeStart.setText("00:00");


            }
        },100);
    }

}
