package com.example.vukhachoi.muisicapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.song_adapte;
import model.song;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class Fragment_List extends android.support.v4.app.Fragment{
    public  Fragment_List(){}
    ListView lsv;
   List<song>listsong;
    song_adapte songAdapter;
    Fragment Play;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.list_song,container,false);
        ContentResolver cr = getActivity().getContentResolver();
        lsv=view.findViewById(R.id.lsvsong);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Play=new Fragment_Play();
        listsong=new ArrayList<>();

        Bundle bundle = getArguments();

        if (bundle != null) {

            listsong= (List<song>) bundle.getSerializable("listbaihat1");



        }else {
            //get path from MediaStore
            Cursor cur = cr.query(uri, null, selection, null, sortOrder);
            int count = 0;
            String data = "";
            String Artist = "";
            String Name = "";
            String ss = "";
            if (cur != null) {
                count = cur.getCount();

                if (count > 0) {
                    while (cur.moveToNext()) {
                        data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                        Artist = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                        Name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                        ss = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                        listsong.add(new song(Name, ss, data, Artist));
                        Log.d("xx", ss);
                    }

                }
            }

            cur.close();
        }

songAdapter=new song_adapte(getActivity(),R.layout.item_song,listsong);
        lsv.setAdapter(songAdapter);
songAdapter.notifyDataSetChanged();


lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ViewPager viewPager=getActivity().findViewById(R.id.view_pager);
        viewPager.setCurrentItem(0);
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


        Bundle bundle =new Bundle();
        bundle.putInt("baihat",i);

        bundle.putSerializable("listbaihat", (Serializable) listsong);
        Play.setArguments(bundle);

if(Fragment_Play.mpintro!=null) {
//    duration=Fragment_Play.mpintro.getCurrentPosition();
//    Fragment_Play oldFragment = (Fragment_Play) fragmentManager.findFragmentByTag("play");
//    FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
//    fragmentTransaction1.remove(oldFragment).commit();
    Fragment_Play.mpintro.stop();
}
          fragmentTransaction.replace(R.id.layout, Play, "play");



          fragmentTransaction.addToBackStack("play");
          fragmentTransaction.commit();


    }
});
        return view;
    }
int duration=0;

}

