package com.example.juhyeon.flink.nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.UI.Join.JoinActivity;

public class Tie_upActivity extends AppCompatActivity {

    View header_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tie_up_nav_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        header_view = getLayoutInflater().inflate(R.layout.nav_header_main,null);

    }
    public void mypage_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(Tie_upActivity.this, JoinActivity.class);
        startActivity(intent);
        finish();
    }
    public void tie_up_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(Tie_upActivity.this, Tie_upActivity.class);
        startActivity(intent);
        finish();

    }
    public void environment_Onclick(View view){
        Log.e("mypage","클릭");
        Intent intent = new Intent(Tie_upActivity.this, EnvironmentActivity.class);
        startActivity(intent);
        finish();

    }

}
