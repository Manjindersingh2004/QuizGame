package com.example.quezgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class resultActivity extends AppCompatActivity {
    TextView txtmarks,highscore;
    Button btnfinish;
    MediaPlayer mediaPlayer;
    boolean newRecord=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtmarks=findViewById(R.id.txtmarks);
        btnfinish=findViewById(R.id.btnfinish);
        highscore=findViewById(R.id.highscore);
        animation();
        Intent i= getIntent();
        String marks=Integer.toString(i.getIntExtra("marks",1));
        int max=new DataBaseHandler(getApplicationContext()).getScore();

        if(Integer.parseInt(marks)>max){
            newRecord=true;
        }


        txtmarks.setText(marks);

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(newRecord){
            mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.high);
            new DataBaseHandler(getApplicationContext()).update(Integer.parseInt(marks));
            highscore.setText("New Record");
        }
        else{
            mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.end2);


            int current=Integer.parseInt(marks);

            if(current>max*0.90 ){
                highscore.setText("Too close");
            }
            else if(current>max*0.80){
                highscore.setText("Not Bad");
            }
            else if(current>max*0.70){
                highscore.setText("keep it up");
            }
            else {
                highscore.setText("Too low");
            }
        }
        mediaPlayer.start();



//        ObjectAnimator rotation = ObjectAnimator.ofFloat(findViewById(R.id.image), "scale", 0f, 360f);
//        rotation.setDuration(5000); // Set the duration in milliseconds (5 seconds in this example)
//        rotation.setInterpolator(new LinearInterpolator()); // Optional: Set an interpolator for smoother animation
//        rotation.setRepeatCount(ObjectAnimator.INFINITE); // Set to repeat indefinitely


        ObjectAnimator scale = ObjectAnimator.ofFloat(findViewById(R.id.image), "scaleX", 1f, 2f);
        scale.setDuration(10000); // Set the duration in milliseconds (5 seconds in this example)
        scale.setInterpolator(new LinearInterpolator()); // Optional: Set an interpolator for smoother animation
        scale.setRepeatCount(ObjectAnimator.INFINITE); // Set to repeat indefinitely

// Apply the same animation to scaleY if you want uniform scaling
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(findViewById(R.id.image), "scaleY", 1f, 2f);
        scaleY.setDuration(10000);
        scaleY.setInterpolator(new LinearInterpolator());
        scaleY.setRepeatCount(ObjectAnimator.INFINITE);

        // Start the animation
        scale.start();
        scaleY.start();
    }

    private void animation() {

        ScaleAnimation animation = new ScaleAnimation(0.5f, 1.0f, .5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        findViewById(R.id.cardView).startAnimation(animation);
        btnfinish.startAnimation(animation);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}