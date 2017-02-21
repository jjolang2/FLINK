package com.example.juhyeon.flink;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.juhyeon.flink.UI.Join.JoinActivity;
import com.example.juhyeon.flink.UI.Market.MarketActivity;
import com.example.juhyeon.flink.UI.Mypage.MyPageActivity;
import com.example.juhyeon.flink.UI.Tab.MainFragment1;
import com.example.juhyeon.flink.UI.Tab2.MainFragment2;
import com.example.juhyeon.flink.UI.Tab3.MainFragment3;
import com.example.juhyeon.flink.UI.find.FindActivity;
import com.example.juhyeon.flink.model.model_GPS;
import com.example.juhyeon.flink.nav.EnvironmentActivity;
import com.example.juhyeon.flink.nav.Tie_upActivity;

import static com.example.juhyeon.flink.R.id.view;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager viewPager,viewPager2;
    Button mypage, tie_up, environment, onpager;
    View header_view;
    ImageButton find;

    EditText sendaddr;
    model_GPS model_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //==============================Tabactivity=================================================
        viewPager = (ViewPager) findViewById(view);
        viewPager2 = (ViewPager) findViewById(R.id.viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //==============================찾기 버튼====================================================
        find = (ImageButton) findViewById(R.id.find);
        find.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });
        //==============================네비게이션 바=================================================
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        header_view = getLayoutInflater().inflate(R.layout.nav_header_main,null);

        mypage = (Button)header_view.findViewById(R.id.Mypage);
        tie_up = (Button)header_view.findViewById(R.id.Tie_up);
        environment = (Button)header_view.findViewById(R.id.Environment);
//======================================마켓으로 넘어가기==============================================
        onpager = (Button)findViewById(R.id.onpager);
//======================================주소 넘기기==============================================
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
        startActivity(intent);
    }
    public void tie_up_Onclick(View view){
        Intent intent = new Intent(MainActivity.this, Tie_upActivity.class);
        startActivity(intent);
    }
    public void environment_Onclick(View view){
        Intent intent = new Intent(MainActivity.this, EnvironmentActivity.class);
        startActivity(intent);
    }
    public void navLogin_Onclick(View view){
        Intent intent = new Intent(this,JoinActivity.class);
        startActivity(intent);
    }
    public void onpager(View view){
        Intent intent = new Intent(this,MarketActivity.class);
        //String addr = sendaddr.getText().toString();
        //model_gps.setLocation(addr);
        startActivity(intent);
    }
}
