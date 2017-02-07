package com.example.juhyeon.flink;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.juhyeon.flink.UI.Join.JoinActivity;
import com.example.juhyeon.flink.UI.SSpage.SSPage1Activity;
import com.example.juhyeon.flink.UI.Tab.MainFragment1;
import com.example.juhyeon.flink.UI.Tab2.MainFragment2;
import com.example.juhyeon.flink.UI.Tab3.MainFragment3;
import com.example.juhyeon.flink.nav.EnvironmentActivity;
import com.example.juhyeon.flink.nav.Tie_upActivity;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager viewPager,viewPager2;
    Button mypage, tie_up, environment;
    View header_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);
        Log.e("MainActivity", "start");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //==============================Tabactivity=====================================
        viewPager = (ViewPager) findViewById(R.id.view);
        viewPager2 = (ViewPager) findViewById(R.id.viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //==============================개인페이지 버튼=====================================
        ImageButton find = (ImageButton) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SSPage1Activity.class);
                startActivity(intent);
            }
        });
        //==============================네비게이션 바=====================================
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        header_view = getLayoutInflater().inflate(R.layout.nav_header_main,null);

        mypage = (Button)header_view.findViewById(R.id.Mypage);
        tie_up = (Button)header_view.findViewById(R.id.Tie_up);
        environment = (Button)header_view.findViewById(R.id.Environment);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        Fragment[] fragments = new Fragment[]{
                new MainFragment1(),
                new MainFragment2(),
                new MainFragment3()
        };

        String[] titles = new String[]{
                "인기마켓", "최신아이템", "마켓정보"
        };

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
    public void mypage_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(MainActivity.this, JoinActivity.class);
        startActivity(intent);
    }
    public void tie_up_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(MainActivity.this, Tie_upActivity.class);
        startActivity(intent);
    }
    public void environment_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(MainActivity.this, EnvironmentActivity.class);
        startActivity(intent);
    }

}
