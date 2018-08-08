package com.example.kushagra.smartmeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kushagra.smartmeds.AlarmSystem.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent1 = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        },3000);
    }
}
