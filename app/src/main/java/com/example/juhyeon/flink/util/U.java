package com.example.juhyeon.flink.util;

import android.util.Log;

/**
 * Created by Juhyeon on 2017-01-20.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
        ////////////////////////////////////////////////////////////////////////////
        //로그
    }
    public void log(String msg){
        Log.e("U*","***********************************");
        Log.e("U*",""+msg); // null을 출력하면 죽으니까 ""하고 더함.
        Log.e("U*","***********************************");
    }

}
