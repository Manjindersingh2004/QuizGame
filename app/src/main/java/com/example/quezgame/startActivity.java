package com.example.quezgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startActivity extends AppCompatActivity {
    Button btnstart;
    MediaPlayer mediaPlayer;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnstart=findViewById(R.id.btnstart);
        Intent i= new Intent(startActivity.this,MainActivity.class);


        score=findViewById(R.id.textView3);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mediaPlayer.start();

        DataBaseHandler db=new DataBaseHandler(getApplicationContext());
        score.setText(db.getScore()+"");
    }
}