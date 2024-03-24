package com.example.qlbanhoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.qlbanhoa.Activity.LoginActivity;
import com.example.qlbanhoa.Activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIMER = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                ProgressBar progressBar = findViewById(R.id.progressBar);
                int progress = 50; // Giá trị tiến trình (từ 0 đến 100)
                progressBar.setProgress(progress);
                if(user != null){
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                }else{
                    intent = new Intent(SplashScreen.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}