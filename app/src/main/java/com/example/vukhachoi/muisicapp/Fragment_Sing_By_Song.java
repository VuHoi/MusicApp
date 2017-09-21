package com.example.vukhachoi.muisicapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import adapter.song_adapte;
import model.song;

/**
 * Created by Vu Khac Hoi on 9/21/2017.
 */

public class Fragment_Sing_By_Song extends android.support.v4.app.Fragment {
    public Fragment_Sing_By_Song() {
    }
ListView lsvSingBySong;
    List<song> listsong;
    song_adapte songAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_by_singer, container, false);
        lsvSingBySong=view.findViewById(R.id.lsvsingbysong);


        return view;
    }
}
