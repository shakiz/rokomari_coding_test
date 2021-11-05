package com.rokomari_coding_test.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.rokomari_coding_test.activities.HomeActivity;
import com.rokomari_coding_test.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //region perform all the user interactions
        bindUIWithComponents();
        //endregion
    }

    private void bindUIWithComponents() {
        //handler to start the dashboard activity after 2 sec
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        }, 2000);

    }
}
