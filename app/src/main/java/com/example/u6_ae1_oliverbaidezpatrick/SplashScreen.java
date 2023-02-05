package com.example.u6_ae1_oliverbaidezpatrick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private final static int SPLASH_TIEMPO = 4000;
    TextView tvCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Recojemos el elemento TEXTVIEW  que contendrá la cuenta atrás
        tvCountDown = findViewById(R.id.tvCountdown);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Intent de actividad principal
        Intent intent = new Intent(this, MainActivity.class);

        // Lanza el intent tras un tiempo (SPLASH_TEMPO)
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(intent);
            }
        }, SPLASH_TIEMPO);

        //Genera un Timer de SPLASH_TEMPO ms, que cuenta de 1000 en 1000
        //Cambia el texto del TextView cada segundo y al final escribe inicio.
        new CountDownTimer(SPLASH_TIEMPO, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountDown.setText((millisUntilFinished / 1000)+"");
            }

            public void onFinish() {
                tvCountDown.setText("Inicio");
            }
        }.start();
    }
}