package com.example.vukhachoi.muisicapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class layoutFragment extends android.support.v4.app.Fragment {
    public layoutFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout, container, false);
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        Fragment list=new Fragment_List();
//
//        fragmentTransaction.add(R.id.layout,list,"list");
//
//        fragmentTransaction.commit();
        return view;
    }
}


