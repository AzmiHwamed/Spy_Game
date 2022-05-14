package com.akihima.spy_game;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RoleAss extends AppCompatActivity {
    String word;
    TextView tv;
    boolean rotatedTotally;
    int nb;
    int time;
    int i;
    int spyorder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_ass);
        ImageView img=findViewById(R.id.img);
        ImageView spyimg=findViewById(R.id.spyimg);
        word=getIntent().getStringExtra("word");
        nb=Integer.parseInt(getIntent().getStringExtra("nbPlayers"));
        time=Integer.parseInt(getIntent().getStringExtra("time").toString());
        View card=findViewById(R.id.carda);
        img.setImageResource(0);
        tv=findViewById(R.id.text);
        rotatedTotally=false;
        i=0;
        spyorder=new Random().nextInt(nb);
        card.animate().rotationYBy(180).setDuration(10).withEndAction(new Runnable() {
            @Override
            public void run() {
                tv.setText("");
                card.setBackgroundResource(R.drawable.back);
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.animate().rotationYBy(180).setDuration(300).withEndAction(new Runnable() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void run() {
                        if(i==spyorder&&!rotatedTotally){
                            tv.setText("Spy");
                            card.setBackgroundColor(R.color.purple_500);
                            img.setImageResource(R.drawable.detective);
                        }
                        else if(rotatedTotally){
                            card.setBackgroundResource(R.drawable.back);
                            img.setImageResource(0);
                            tv.setText("");
                            i++;
                        }
                        else{
                            tv.setText(word);
                            card.setBackgroundColor(R.color.purple_500);
                            img.setImageResource(R.drawable.detective);
                        }

                        rotatedTotally=!rotatedTotally;
                        if(i==nb){
                            Intent in=new Intent(getApplicationContext(),TimerScreen.class);
                            in.putExtra("Duration",Integer.toString(time));
                            ActivityOptions opt=ActivityOptions.makeSceneTransitionAnimation(RoleAss.this, Pair.create(spyimg,"spyword"));
                            startActivity(in,opt.toBundle());

                        }
                    }
                });
            }
        });

    }
}
