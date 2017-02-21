package com.example.juhyeon.flink.UI.Tab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.juhyeon.flink.Network.NetSSL;
import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.model.Res.Res_market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Juhyeon on 2017-01-12.
 */

public class MainFragment3 extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    RecyclerView recyclerview;
    ArrayList<String> arrayList = new ArrayList<String>();
    MyAdapter myAdapter = new MyAdapter();
    GridLayoutManager gridLayoutManager;
    Res_market res_market;
    Button checkbtn;

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

        //=====================================리사이클러뷰==========================================
        // 고정크기 그리드
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //recyclerview.setLayoutManager(gridLayoutManager);
        //recyclerview.setAdapter(myAdapter);
        //=================================레트로핏 통신=============================================

        Map<String, String> param = new HashMap<>();
        param.put("page", "1");
        param.put("rows", "10");

        Call<Res_market> res = NetSSL.getInstance().getMemberImpFactory()
                .markets(param);
        res.enqueue(new Callback<Res_market>() {
            @Override
            public void onResponse(Call<Res_market> call, Response<Res_market> response) {
                Log.e("레트로핏 마켓목록",""+response.body().getResult().getMessage());
            }
            @Override
            public void onFailure(Call<Res_market> call, Throwable t) {}
        });

//==============================================================================================
        checkbtn = (Button)view.findViewById(R.id.checkbtn);
        checkbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Log.e("마켓값 들어왔니?",""+res_market.getResult().getMarkets());
            }
        });
        return view;
    }

    // 아답터
    class MyAdapter extends RecyclerView.Adapter {
        // 데이터의 개수
        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        // ViewHolder 생성
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // xml -> view
            View itemView =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cell_tab3_gridview, parent, false);

            return new MarketHolder(itemView);
        }

        // ViewHolder에 데이터르 설정(바인딩)한다.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ((MarketHolder) holder).bindOnPost(arrayList.get(position));
            Log.e("","값값값"+arrayList.get(position));


        }
    }
}