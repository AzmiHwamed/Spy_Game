package com.akihima.spy_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            ImageView v = findViewById(R.id.anim);
            v.setMinimumWidth(100);
            v.setMinimumHeight(100);
            ImageView spy = findViewById(R.id.spyword);
            v.animate().alpha(1).setDuration(3000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), ChooseParamScreen.class);
                    ActivityOptions opt = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, Pair.create(spy, "spyword"));
                    startActivity(i,opt.toBundle());
                }
            });
    }
}