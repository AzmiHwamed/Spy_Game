package com.akihima.spy_game;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class makeAVote extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_vote_screen);
        Button b=findViewById(R.id.btn);
        ImageView img=findViewById(R.id.img);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ChooseParamScreen.class);
                ActivityOptions opt=ActivityOptions.makeSceneTransitionAnimation(makeAVote.this,Pair.create(img,"spyword"));
                startActivity(i,opt.toBundle());
            }
        });
    }
}
