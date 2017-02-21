package com.example.juhyeon.flink.UI.Tab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    Fragment fragment;

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
        View view = inflater.inflate(R.layout.tab2_newest, container, false);

        header1 = (ImageButton) view.findViewById(R.id.tab2_1_1);
        header1.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                change(0);
            }
        });

        header2 = (ImageButton) view.findViewById(R.id.tab2_2_1);
        header2.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                change(1);
            }
        });

        header3 = (ImageButton) view.findViewById(R.id.tab2_3_1);
        header3.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                change(2);
            }
        });

        header4 = (ImageButton) view.findViewById(R.id.tab2_4_1);
        header4.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                change(3);
            }
        });

        return view;
    }
    public void change(int index) {
        switch (index){
            case 0:
                fragment = new BeautyFragment();
                header1.setImageResource(R.mipmap.tab2_1_2);
                header2.setImageResource(R.mipmap.tab2_2_1);
                header3.setImageResource(R.mipmap.tab2_3_1);
                header4.setImageResource(R.mipmap.tab2_4_1);

                break;
            case 1:
                fragment = new ClothingFragment();
                header1.setImageResource(R.mipmap.tab2_1_1);
                header2.setImageResource(R.mipmap.tab2_2_2);
                header3.setImageResource(R.mipmap.tab2_3_1);
                header4.setImageResource(R.mipmap.tab2_4_1);
                break;
            case 2:
                fragment = new DessertFragment();
                header1.setImageResource(R.mipmap.tab2_1_1);
                header2.setImageResource(R.mipmap.tab2_2_1);
                header3.setImageResource(R.mipmap.tab2_3_2);
                header4.setImageResource(R.mipmap.tab2_4_1);

                break;
            case 3:
                fragment = new MaidFragment();
                header1.setImageResource(R.mipmap.tab2_1_1);
                header2.setImageResource(R.mipmap.tab2_2_1);
                header3.setImageResource(R.mipmap.tab2_3_1);
                header4.setImageResource(R.mipmap.tab2_4_2);
                break;
        }
        // 한개의 자리를 다른 fragment로 교체 하는 코드
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tab2_view_fragment, fragment);
        transaction.commit();
    }
}