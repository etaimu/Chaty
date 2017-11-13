package com.etaimuallem.chaty;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.etaimuallem.chaty.models.User;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    FirebaseAuth.AuthStateListener mAuthStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            signIn();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_login);
    }

    private void signIn() {

        mUser = mAuth.getCurrentUser();

        if (mUser == null) {
            List<AuthUI.IdpConfig> mProviders = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()
            );

            Intent intent = AuthUI.getInstance().
                    createSignInIntentBuilder().
                    setLogo(R.mipmap.ic_launchernew).
                    setAvailableProviders(mProviders).
                    setIsSmartLockEnabled(false).
                    build();

            startActivityForResult(intent, RC_SIGN_IN);
        } else {
            User u = new User(mUser);
            mDatabase.getReference("Users").child(mUser.getUid()).setValue(u);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
        sha1();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    void sha1() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo
                    ("more.hackeru.edu.shopee", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
