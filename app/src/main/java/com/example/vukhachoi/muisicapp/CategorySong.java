package com.example.vukhachoi.muisicapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.Singer_Adapter;
import model.singer;
import model.song;

/**
 * Created by Vu Khac Hoi on 9/22/2017.
 */

public class CategorySong extends android.support.v4.app.Fragment {
    public CategorySong() {
    }
ListView lsvAlbum;
    List<singer> singerList;
    List<song>songList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_song, container, false);
        lsvAlbum=view.findViewById(R.id.lsvAlbum);
        songList=new ArrayList<>();
        singerList=new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;
        String data="";
        String Artist="";
        String Name="";

        if(cur != null)
        {
            count = cur.getCount();

            if(count > 0)
            {
                while(cur.moveToNext())
                {
                    data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    Name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    Artist = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM));

                    singer singer1=new singer(Artist, data);

                    try {

                        int trung=0;
                        for (singer i:singerList) {
                            if(i.getName().equals(Artist)){trung=1;break;}

                        }
                        if(trung==0)     singerList.add(singer1);
                    }catch (Exception e){

                    }
                }

            }
        }

        cur.close();

        Singer_Adapter adapter=new Singer_Adapter(getActivity(),R.layout.item_singer,singerList);
        lsvAlbum.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lsvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContentResolver cr = getActivity().getContentResolver();
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
                String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
                Cursor cur = cr.query(uri, null, selection, null, sortOrder);
                int count = 0;
                String data="";
                String Artist="";
                String Name="";

                if(cur != null)
                {
                    count = cur.getCount();

                    if(count > 0)
                    {
                        while(cur.moveToNext())
                        {
                            data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                            Name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                            Artist = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                            String ALbum=cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                            if(ALbum.equals(singerList.get(i).getName()))
                            { songList.add(new song(Name, "", data, Artist));}

                        }

                    }
                }

                cur.close();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                Fragment_List fragment_list=new Fragment_List();

                Bundle bundle =new Bundle();

                bundle.putSerializable("listbaihat1", (Serializable) songList);

                fragment_list.setArguments(bundle);

                fragmentTransaction2.add(R.id.layout3, fragment_list, "singer");
                fragmentTransaction2.addToBackStack("singer");
                fragmentTransaction2.commit();
            }
        });
        return view;
    }
}