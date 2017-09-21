package com.example.vukhachoi.muisicapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vu Khac Hoi on 9/21/2017.
 */

public class layoutFragment_2 extends android.support.v4.app.Fragment {
    public layoutFragment_2() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout2, container, false);

        return view;
    }
}
