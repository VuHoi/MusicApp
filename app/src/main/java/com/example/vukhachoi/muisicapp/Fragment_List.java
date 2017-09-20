package com.example.vukhachoi.muisicapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class Fragment_List extends android.support.v4.app.Fragment{
public  Fragment_List(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.list_song,container,false);


        return view;
    }
}
