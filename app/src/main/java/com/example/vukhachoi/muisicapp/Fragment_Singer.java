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

import java.util.ArrayList;
import java.util.List;

import adapter.Singer_Adapter;
import model.singer;

/**
 * Created by Vu Khac Hoi on 9/21/2017.
 */

public class Fragment_Singer extends android.support.v4.app.Fragment {
    public Fragment_Singer() {
    }
    List<singer>singerList;
    ListView lsvSinger;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singer, container, false);
        lsvSinger=view.findViewById(R.id.lsvSinger);
        singerList=new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;
        String data="";

        String Name="";

        if(cur != null)
        {
            count = cur.getCount();

            if(count > 0)
            {
                while(cur.moveToNext())
                {
                    data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));

                    Name = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    singer singer1=new singer(Name, data);
                    try {

                        int trung=0;
                        for (singer i:singerList) {
                            if(i.getName().equals(Name)){trung=1;break;}

                        }
                        if(trung==0)     singerList.add(singer1);
                    }catch (Exception e){

                    }
                }

            }
        }

        cur.close();

        Singer_Adapter adapter=new Singer_Adapter(getActivity(),R.layout.item_singer,singerList);
        lsvSinger.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lsvSinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Sing_By_Song fragment_sing_by_song=new Fragment_Sing_By_Song();

                Bundle bundle =new Bundle();
                bundle.putString("ten",singerList.get(i).getName());


                fragment_sing_by_song.setArguments(bundle);

                fragmentTransaction.replace(R.id.layout2, fragment_sing_by_song, "singer");
                fragmentTransaction.addToBackStack("singer");
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}

