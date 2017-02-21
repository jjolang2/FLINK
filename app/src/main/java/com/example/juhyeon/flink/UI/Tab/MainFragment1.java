package com.example.juhyeon.flink.UI.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.util.ImageProc;

import static com.example.juhyeon.flink.R.layout.tab1_famous;

public class MainFragment1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    MyPagerAdapter MyPageradapter;
    ViewPager viewPager;
    LayoutInflater inflater;
    private boolean isAnimated;
    ImageButton side_left, side_right;
    TextView curDot;
    Button onpager;
    Fragment fragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(tab1_famous, container, false);
//===================================인기마켓 포스터페이저=============================================
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        curDot = (TextView) view.findViewById(R.id.curDot);
        MyPageradapter = new MyPagerAdapter();
        viewPager.setAdapter(MyPageradapter);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageMargin(70);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        ImageProc.getInstance().getImageLoader(getContext()); //초기화
//====================================오토뷰 좌우버튼=================================================
        side_left = (ImageButton) view.findViewById(R.id.side_left);
        side_left.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cur = viewPager.getCurrentItem();    //현재 아이템 포지션
                if (cur > 0)                //첫 페이지가 아니면
                    viewPager.setCurrentItem(cur - 1, isAnimated);    //이전 페이지로 이동
            }
        });
        side_right = (ImageButton) view.findViewById(R.id.side_right);
        side_right.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cur = viewPager.getCurrentItem();    //현재 아이템 포지션
                if (cur < poster.length - 1)        //마지막 페이지가 아니면
                    viewPager.setCurrentItem(cur + 1, isAnimated);    //다음 페이지로 이동
            }
        });
//======================================마켓으로 넘어가기==============================================


//==================================================================================================

        return view;
    }

    public void changeDot(int position) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < poster.length; i++) {
            if (i == position) {
                sb.append("● ");
            } else {
                sb.append("○ ");
            }
        }
        curDot.setText(sb.toString().trim());
    }

    //페이저 아답터
    class MyPagerAdapter extends PagerAdapter {
        //뷰의 갯수
        @Override
        public int getCount() {
            return poster.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String url = poster[position];
            ImageView imageView = new ImageView(getContext());
            ImageProc.getInstance().drawImage(url, imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); // 무조건 꽉 채우기
            ((ViewPager) container).addView(imageView); // 뷰 삭제

            return imageView;
        }
    }

}
