package com.example.android.mindpalace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Room extends AppCompatActivity  implements Dialog.DialogListener{
    private String room;
    private ArrayList<Assosiation> words=new ArrayList<Assosiation>();
    public String toRemove="";

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String WORDS="words";


    private void setWelcomeMsg(String room){
        TextView welcome=findViewById(R.id.welcomeMessage);
        welcome.setText("You are in the "+room);
        ImageView bg=findViewById(R.id.bg);
        System.out.println(room);
        if(room.contains("living")){
            bg.setImageResource(R.drawable.lrb);
        }
        if(room.contains("Bed")){
            bg.setImageResource(R.drawable.brb);
        }
        if(room.contains("ath")){
            bg.setImageResource(R.drawable.bthb);
        }
        if(room.contains("itc")){
            bg.setImageResource(R.drawable.kb);
        }
        if(room.contains("ase")){
            bg.setImageResource(R.drawable.bb);
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Intent i=getIntent();
        room=i.getStringExtra("ROOM");
        setWelcomeMsg(room);
        load();
        showWords();


    }




    private void showWords() {
        GridLayout gl=(GridLayout)findViewById(R.id.gl);
        gl.removeAllViews();
        int n=this.words.size();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            if (this.words.get(i).getRoom().equals(room)){
                Button btn = new Button(this);

            btn.setTextSize(10);

            btn.setTag(i);

            btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Button b=(Button)v;
                    toRemove=b.getText().toString();
                    System.out.println(toRemove);
                    Button rem=(Button)findViewById(R.id.remove);
                    rem.setVisibility(View.VISIBLE);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            Button rem=(Button)findViewById(R.id.remove);
                            rem.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);
                    return true;
                }
            });

            btn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Button btn=(Button)v;
                    for(int j=0;j<words.size();j++){
                        if(words.get(j).getWord()==btn.getText().toString()){
                            Toast.makeText(Room.this, words.get(j).getMeaning(), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }
            });
            btn.setText(this.words.get(i).getWord());
            gl.addView(btn);
        }
        }
    }

    public void save(){
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE );
        SharedPreferences.Editor editor=sp.edit();
        Gson gson=new Gson();
        String json=gson.toJson(this.words);
        editor.putString(WORDS, json);
        editor.apply();
    }

    public void load(){
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        Gson gson=new Gson();
        String json = sp.getString(WORDS, null);
        Type type = new TypeToken<ArrayList<Assosiation>>() {}.getType();


        this.words= gson.fromJson(json, type);
        if(words==null){
            words=new ArrayList<Assosiation>();
        }

    }

    public void removeWord(View view) {
        int i=0;
        Button rem=(Button)findViewById(R.id.remove);
        rem.setVisibility(View.INVISIBLE);
        while(i<this.words.size()){
            if(this.words.get(i).getWord().equals(this.toRemove)){
                this.words.remove(i);
                save();
                showWords();
                break;
            }
            i=i+1;
        }
    }



    public void openDialog(View view) {
        Dialog dialog=new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");

    }

    @Override
    public void applyTexts(String word, String mean) {
        Assosiation a=new Assosiation(word, mean, room);
        this.words.add(a);
        showWords();
        save();

    }
}
