package com.etaimuallem.chaty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class IntreductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indruction);

        SharedPreferences pref = getSharedPreferences("appIntro", Context.MODE_PRIVATE);
        boolean gotIt = pref.getBoolean("gotIt", false);
        if (!gotIt) {
            Intent intent = new Intent(this, AppIntroActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
