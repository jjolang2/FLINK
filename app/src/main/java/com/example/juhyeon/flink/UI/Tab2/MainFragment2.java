package com.example.juhyeon.flink.UI.Tab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.juhyeon.flink.R;

/**
 * Created by Juhyeon on 2017-01-12.
 */

public class MainFragment2 extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    EditText editText;
    ImageButton header1,header2,header3,header4;

    public MainFragment2() {
    }

    public static MainFragment2 newInstance(int sectionNumber) {
        MainFragment2 fragment = new MainFragment2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_tab2_header, container, false);

        header1 = (ImageButton) view.findViewById(R.id.tab2_1_1);
        header2 = (ImageButton) view.findViewById(R.id.tab2_2_1);
        header3 = (ImageButton) view.findViewById(R.id.tab2_3_1);
        header4 = (ImageButton) view.findViewById(R.id.tab2_4_1);

        return view;
    }

//    public void onSearch(View view){
//        String tel = editText.getText().toString();
//        Network.getInstance().searchHp(this,tel);
//    }
}