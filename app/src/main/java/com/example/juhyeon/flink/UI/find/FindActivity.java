package com.example.juhyeon.flink.UI.find;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;

import com.example.juhyeon.flink.R;

public class FindActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String autoKeyword[]= {"뷰티","의류","디저트","메이드"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findactivity);

        Toolbar findtoolbar = (Toolbar) findViewById(R.id.findtoolbar);
        setSupportActionBar(findtoolbar);
        getSupportActionBar().setTitle(null);
//==========================================자동완성=================================================
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.automsg);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoKeyword));
//==========================================자동완성=================================================
        //requestWindowFeature(Window.FEATURE_NO_TITLE);



    }

    // 추천 키워드, 자동완성 UI를 직접 구성하거나,  내용을 가변시킬수 있다.
    class MyKeywordAdaper extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
