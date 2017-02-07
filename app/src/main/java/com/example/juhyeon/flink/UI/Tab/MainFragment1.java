package com.example.juhyeon.flink.UI.Tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.model.ResponseDaumAPI_SearchImage;
import com.example.juhyeon.flink.util.ImageProc;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.juhyeon.flink.R.layout.include_tab1_list;

public class MainFragment1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    //통신 1회당 담는 그릇!
    ResponseDaumAPI_SearchImage results;
    //실제 검색 결과를 받는 그릇
    ArrayList<ResponseDaumAPI_SearchImage.channel.item> items
            = new ArrayList<ResponseDaumAPI_SearchImage.channel.item>();
    DaumSearchAdapt daumSearchAdapter;
    RequestQueue queue ;
    MyPagerAdapter MyPageradapter;
    ViewPager viewPager;
    ListView listView;
    LayoutInflater inflater;
    private boolean isAnimated;
    ImageButton side_left,side_right;
    View header_view;


    int pageNo = 1;
    int curPageNo ;

    String[] poster =
            {
                    "http://bshop.phinf.naver.net/20170111_66/showindowCommon_1484113272338JwquF_JPEG/47963288933741717_1333939885.jpg?type=w640",
                    "http://bshop.phinf.naver.net/20170111_37/showindowCommon_1484113674176Oz3a3_JPEG/23933306019343873_-808664475.jpg?type=w640",
                    "http://cshop.phinf.naver.net/20170111_245/showindowCommon_1484114383184gSmfW_JPEG/23926703330673543_-624249472.jpg?type=w640",
                    "http://imgnews.naver.net/image/417/2015/09/16/2015091613498015380_1_99_20150916214903.jpg?type=w540"
            };

    public MainFragment1() {
    }

    public static MainFragment1 newInstance(int sectionNumber) {
        MainFragment1 fragment = new MainFragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(include_tab1_list, container, false);
//===================================리스트뷰========================================================
        listView = (ListView) view.findViewById(R.id.listView);
        queue = Volley.newRequestQueue(MainFragment1.this.getActivity());
        ImageProc.getInstance().getImageLoader(MainFragment1.this.getActivity());
        daumSearchAdapter = new DaumSearchAdapt();
        listView.setAdapter(daumSearchAdapter);
        listView.setDividerHeight(0);
        getSearchImage();
//===================================인기마켓 오토뷰페이저=============================================
        header_view = getLayoutInflater(savedInstanceState).inflate(R.layout.include_tab1_header,null);
        viewPager = (ViewPager)header_view.findViewById(R.id.viewPager);
        MyPageradapter = new MyPagerAdapter();
        viewPager.setAdapter(MyPageradapter);

        listView.addHeaderView(header_view);

        //자동으로 이미지
        pagerCurPage = 0;
        handler.sendEmptyMessageDelayed(0,1000*3);

//====================================오토뷰 좌우버튼=================================================
        side_left = (ImageButton)header_view.findViewById(R.id.side_left);
        side_left.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                int cur = viewPager.getCurrentItem();    //현재 아이템 포지션
                if (cur > 0)                //첫 페이지가 아니면
                    viewPager.setCurrentItem(cur - 1, isAnimated);    //이전 페이지로 이동
            }
        });
        side_right = (ImageButton)header_view.findViewById(R.id.side_right);
        side_right.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                int cur = viewPager.getCurrentItem();    //현재 아이템 포지션
                if(cur < poster.length-1)        //마지막 페이지가 아니면
                    viewPager.setCurrentItem(cur+1, isAnimated);    //다음 페이지로 이동
            }
        });
//==================================================================================================
        return view;
    }


    //자동으로 이미지 넘겨줄 수 있는 핸들러
    int pagerCurPage;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                {
                    pagerCurPage ++;
                    int page = pagerCurPage % poster.length;
                    viewPager.setCurrentItem(page);
                    sendEmptyMessageDelayed(0,1000*4);
                }
                break;
            }
        }
    };

    //1페이지 이미지 검색 결과 가져오기
    public void getSearchImage(){
        //2. 요청 준비
        StringRequest sr = new StringRequest(
                Request.Method.GET,
                "https://apis.daum.net/search/image?apikey=4c8b95e4152c59b33a526d45bab5d129&q=%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88&output=json&pageno=" +pageNo,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.i("UI",response);
                        results = new Gson().fromJson(response,ResponseDaumAPI_SearchImage.class);
                        items.addAll(results.getChannel().getItem()); //계속 밀어넣는거..

                        curPageNo = pageNo;
                        //데이터가 변경되었음을 통보 한다 -> 리스트뷰가 반응하여 다시 갱신을 진행.
                        daumSearchAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> maps = new HashMap<String,String>();
                maps.put("apikey", "4c8b95e4152c59b33a526d45bab5d129"); //fire base에 넣어서 노출 안되게 사용.
                maps.put("q","%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88");
                maps.put("output","json");
                maps.put("pageno","1");
                return maps;
            }
        };
        //3. 큐에 요청 넣기
        queue.add(sr);
    }
    //리스트뷰와 아답터가 붙였을때 이 아답터 사용.
    // 통신이 완료 됐을때 아답터를 실행 시키자 > 에러를 줄이쟈..
    class ViewHolder {
        @BindView(R.id.mainimage)
        ImageView mainimage;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
    class DaumSearchAdapt extends BaseAdapter {
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public ResponseDaumAPI_SearchImage.channel.item getItem(int position) {
            return items.get(position); // item에 다 담겨있음.
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //viewholder 전역으로 놔도 되지만 함수 호출 시에만 사용하면 되므로 지역 변수로 선언.

            ViewHolder viewHolder;
            if(convertView == null) //재사용성 때문에 확인
            {
                //inflacter -> xml을 뷰로!;
                convertView = inflater.inflate(R.layout.cell_recyclerview_tab1, parent,false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            ResponseDaumAPI_SearchImage.channel.item item = getItem(position);

            //데이터세팅!!!!!!!!!!!!!!!!! 리스트는 계속해서 바뀐 내용이 보여야만 한다.
            //thumnail(썸네일)
            ImageProc.getInstance().drawImage(item.getThumbnail(), viewHolder.mainimage);
            //title
            viewHolder.title.setText(item.getTitle());

            //마지막 체크
            if(position == getCount()-1)
            {
                //최종 페이지 라면 더이상 목록이 없습니다. 등 메세지 처리 하면 됨.
                // 아니라면 다음 페이지를 가져온다.
                Log.i("UI","마지막");
                if(pageNo == curPageNo)
                {
                    pageNo++;
                    //통신
                    getSearchImage();
                }
            }
            return convertView;
        }
    }

    //페이저 아답터
    class MyPagerAdapter extends PagerAdapter {
        //뷰의 갯수
        @Override
        public int getCount() {
            //배열은 length 컬렉션은 size
            return poster.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        //뷰를 추가
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //posion -> 요쳥 페이지 => 요청 페이지 별로 뷰를 생성해서 처리!
            String url = poster[position];
            //이미지뷰 생성
            ImageView imageView = new ImageView(MainFragment1.this.getActivity());
            //이미지 세팅
            ImageProc.getInstance().drawImage(url,imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); //무조건 꽉채우기
            ((ViewPager)container).addView(imageView);
            return imageView;
        }
        //뷰를 제거
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }
    }
}
