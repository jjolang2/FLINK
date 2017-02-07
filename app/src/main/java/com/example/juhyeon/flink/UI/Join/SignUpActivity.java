package com.example.juhyeon.flink.UI.Join;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.juhyeon.flink.MainActivity;
import com.example.juhyeon.flink.R;


/**
 * 셀쇼 가입액티비티
 * 셀쇼 가입 후 버튼으로 메인페이지로 이동
 */
public class SignUpActivity extends AppCompatActivity {

    Button signupsucess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);

        signupsucess = (Button)findViewById(R.id.signupsucess);

    }
    //====================================로그인버튼 -> 메인액티비티====================================
    public void toMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
