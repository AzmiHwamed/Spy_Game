package com.akihima.spy_game;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChooseParamScreen extends AppCompatActivity {
    String x;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooseparamsscreen);
        Button b=findViewById(R.id.btnstart);
        EditText nb=findViewById(R.id.editTextNumber);
        ImageView img=findViewById(R.id.img);
        EditText time=findViewById(R.id.editTextTime);
        Spinner s=findViewById(R.id.listtopic);
        ArrayList<String> topics=new ArrayList<>();
        topics.add("fruits");
        topics.add("vegetables");
        s.setAdapter(new ArrayAdapter<String>(getApplicationContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,topics));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verif(nb.getText().toString(),time.getText().toString())==true){
                    System.out.println("5Edmet el fonction");
                    Intent i=new Intent(getApplicationContext(),RoleAss.class);
                    x="";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://api.datamuse.com/words?ml="+topics.get(s.getSelectedItemPosition()))
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try{
                            JSONArray arr=new JSONArray(response.body().string());
                            JSONObject obj=arr.getJSONObject(new Random().nextInt(arr.length()));
                            x=obj.getString("word").toString();}
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });
                    while(x==""){
                    }
                    String s1=time.getText().toString();
                    String s2=nb.getText().toString();
                    i.putExtra("word",x);
                    i.putExtra("nbPlayers",s2);
                    i.putExtra("time",s1);
                    ActivityOptions opt=ActivityOptions.makeSceneTransitionAnimation(ChooseParamScreen.this, Pair.create(img,"spyword"));
                    startActivity(i,opt.toBundle());
                }
                else{
                    Toast.makeText(ChooseParamScreen.this, "Check Your Settings", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
    //TODO:ZID rake7 Hedhi
    boolean verif(String nb,String time){
        String a=Boolean.toString((!nb.isEmpty())&&(!time.isEmpty())&&(Integer.parseInt(nb)>2)&&(Integer.parseInt(nb)<15)&&(Integer.parseInt(time)>0)&&(Integer.parseInt(time)<10));
        return (!nb.isEmpty())&&(!time.isEmpty())&&(Integer.parseInt(nb)>2)&&(Integer.parseInt(nb)<15)&&(Integer.parseInt(time)>0)&&(Integer.parseInt(time)<10);
    }

}



