package com.example.juhyeon.flink.nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.UI.Join.JoinActivity;

public class EnvironmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envelopment_activity);
    }

    public void toJoin(View view){
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
        finish();
    }
}
