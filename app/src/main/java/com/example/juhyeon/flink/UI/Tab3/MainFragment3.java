package com.example.juhyeon.flink.UI.Tab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juhyeon.flink.R;

/**
 * Created by Juhyeon on 2017-01-12.
 */

public class MainFragment3 extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public MainFragment3() {
    }

    public static MainFragment3 newInstance(int sectionNumber) {
        MainFragment3 fragment = new MainFragment3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_market, container, false);
        return view;
    }
}