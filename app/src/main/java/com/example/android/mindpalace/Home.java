package com.example.android.mindpalace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Home extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void visitRoom(View view) {
        Intent i=new Intent(getApplicationContext(), Room.class);
        String room=((ImageButton)view).getContentDescription().toString();
        i.putExtra("ROOM", room);
        startActivity(i);
    }


    public void save(){

    }
}
