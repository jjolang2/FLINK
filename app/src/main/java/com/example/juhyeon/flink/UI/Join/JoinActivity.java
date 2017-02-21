package com.example.juhyeon.flink.UI.Join;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.juhyeon.flink.MainActivity;
import com.example.juhyeon.flink.R;

/**
 * 로그인 및 회원가입 방법 선택액티비티
 */

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinactivity);

    }

    //====================================로그인버튼 -> 메인액티비티====================================
    public void toMain(View view){
        Intent intent = new Intent(JoinActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    //====================================회원가입버튼 이동=========================================
    public void toSignUp(View view){
        Intent intent = new Intent(JoinActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
