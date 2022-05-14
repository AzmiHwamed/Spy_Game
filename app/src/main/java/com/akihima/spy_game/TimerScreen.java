package com.akihima.spy_game;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerScreen extends AppCompatActivity {
    TextView time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_screen);
        time = findViewById(R.id.Timer);
        Button skip=findViewById(R.id.skip);
        ImageView img=findViewById(R.id.img);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String result = extras.getString("Duration");
        System.out.println("yeah"+result);
        new CountDownTimer(60000*Integer.parseInt(result), 1000) {
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            public void onFinish() {
                Intent i=new Intent(getApplicationContext(),makeAVote.class);
                ActivityOptions opt=ActivityOptions.makeSceneTransitionAnimation(TimerScreen.this, Pair.create(img,"spyword"));
                startActivity(i,opt.toBundle());
            }
        }.start();
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),makeAVote.class);
                ActivityOptions opt=ActivityOptions.makeSceneTransitionAnimation(TimerScreen.this, Pair.create(img,"spyword"));
                startActivity(i,opt.toBundle());
            }
        });

    }
}