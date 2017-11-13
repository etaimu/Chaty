package com.etaimuallem.chaty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(com.github.paolorotolo.appintro.AppIntroFragment.newInstance("Welcome", "Chat with anyone about anything by subject", R.drawable.chat, getResources().getColor(R.color.colorPrimary)));
        addSlide(com.github.paolorotolo.appintro.AppIntroFragment.newInstance("Facebook Login", "Login with Facebook if you want to use this app and don't worry we will never use your details or post in your name", R.drawable.face, getResources().getColor(R.color.colorPrimary)));
        addSlide(com.github.paolorotolo.appintro.AppIntroFragment.newInstance("Warning!", "Be respectful", R.drawable.chat, getResources().getColor(R.color.colorPrimary)));
        showSkipButton(false);
        setFadeAnimation();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences pref = getSharedPreferences("appIntro", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("gotIt", true);
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
