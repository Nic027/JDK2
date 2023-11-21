package jdk3.jdk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import jdk3.jdk.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer();
    }

    private void Timer(){
        TimerTask tiempo = new TimerTask() {
            @Override
            public void run() {
                Intent Login = new Intent(SplashScreen.this,Login.class);
                startActivity(Login);
                finish();
            }
        };
        Timer tiempot = new Timer();
        tiempot.schedule(tiempo,4000);
    }
}