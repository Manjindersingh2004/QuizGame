package com.example.quezgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    quezData obj=new quezData();
    TextView txtQheading,txtQ,txtAns;
   // RadioGroup radG;
    //RadioButton radB1,radB2,radB3,radB4,radTemp;

    AppCompatButton A,B,C,D,h1,h2,h3;

    MediaPlayer mediaPlayer,mediaPlayer2;

    LinearLayout linearLayout;

    int level=1;
    Button btn;
    int life =3;
    int question=1,marks=0,index,totalQuestions=obj.questions.length;
    String Score="Score: ",Ans="Ans: ";
    Random random=new Random();

    ArrayList<Integer> numbers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQheading=findViewById(R.id.txtQheading);
        txtQ=findViewById(R.id.txtQ);
        txtAns=findViewById(R.id.txtAns);
        linearLayout=findViewById(R.id.linearLayout);
//        radG=findViewById(R.id.radioG);
//        radB1=findViewById(R.id.radB1);
//        radB2=findViewById(R.id.radB2);
//        radB3=findViewById(R.id.radB3);
//        radB4=findViewById(R.id.radB4);
        btn=findViewById(R.id.btn);
        btn.setEnabled(false);

        slideInViews();

        mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.theme2);
        mediaPlayer2.setLooping(true);
        mediaPlayer2.setVolume(0.1f,0.1f);


        A=findViewById(R.id.optionABtn);
        B=findViewById(R.id.optionBBtn);
        C=findViewById(R.id.optionCBtn);
        D=findViewById(R.id.optionDBtn);

        h1=findViewById(R.id.h1);
        h2=findViewById(R.id.h2);
        h3=findViewById(R.id.h3);
// Genrate random number that not repeated
        Random randomGenerator = new Random();
        while (numbers.size() < totalQuestions) {

            int random = randomGenerator .nextInt(totalQuestions);
            if (!numbers.contains(random) ) {
                numbers.add(random);
            }
        }


// set questions and options for first time

        txtQheading.setText(Score+(0));
        index= numbers.get(question-1);
        txtQ.setText("Q"+question+": "+obj.questions[index]);
        txtAns.setText("");
//        radB1.setText(obj.optionA[index]);
//        radB2.setText(obj.optionB[index]);
//        radB3.setText(obj.optionC[index]);
//        radB4.setText(obj.optionD[index]);

        A.setText(obj.optionA[index]);
        B.setText(obj.optionB[index]);
        C.setText(obj.optionC[index]);
        D.setText(obj.optionD[index]);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionSelectionAction(A);
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionSelectionAction(B);
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionSelectionAction(C);
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionSelectionAction(D);
            }
        });

// when option is choose by user

//        radG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(firstClick==1) {
//                    radTemp = findViewById(checkedId);
//                    radTemp.setChecked(true);
//                    if (radTemp.getText().toString().equals(obj.correctAnswers[index])) {
//                        radTemp.setTextColor(getColor(R.color.green));
//                        marks++;
//                    }
//                    else {
//                        radTemp.setTextColor(getColor(R.color.red));
//                    }
//                    txtAns.setText(Ans + obj.correctAnswers[index]);
//                    firstClick++;
//                    next=1;
//                }
//            }
//        });

        // when next button is clicked by user

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(true) {
                    if (question == totalQuestions || life==0) {
                        stopMusic();
                        Intent i = new Intent(MainActivity.this, resultActivity.class);
                        i.putExtra("marks", marks);
                        startActivity(i);
                        finish();

                    } else {
//                        if (questionLimit == question + 1) {
//                            btn.setText("Result");
//                        }
//                        radB1.setChecked(false);
//                        radB2.setChecked(false);
//                        radB3.setChecked(false);
//                        radB4.setChecked(false);


                        //radG.clearCheck();
                        question++;
                        index= numbers.get(question-1);;
                        txtQ.setText("Q"+question+": "+obj.questions[index]);
//                        radB1.setText(obj.optionA[index]);
//                        radB2.setText(obj.optionB[index]);
//                        radB3.setText(obj.optionC[index]);
//                        radB4.setText(obj.optionD[index])
//                       ;
                        A.setText(obj.optionA[index]);
                        B.setText(obj.optionB[index]);
                        C.setText(obj.optionC[index]);
                        D.setText(obj.optionD[index]);

                        A.setTextColor(getColor(R.color.black));
                        B.setTextColor(getColor(R.color.black));
                        C.setTextColor(getColor(R.color.black));
                        D.setTextColor(getColor(R.color.black));

                        A.setBackgroundDrawable(getDrawable(R.drawable.btn_background));
                        B.setBackgroundDrawable(getDrawable(R.drawable.btn_background));
                        C.setBackgroundDrawable(getDrawable(R.drawable.btn_background));
                        D.setBackgroundDrawable(getDrawable(R.drawable.btn_background));

                        txtAns.setText("");
                        btn.setEnabled(false);

                        A.setEnabled(true);
                        B.setEnabled(true);
                        C.setEnabled(true);
                        D.setEnabled(true);
                        slideInViews();
                    }
                }

            }
        });

    }


    void optionSelectionAction(AppCompatButton btn){
        if (btn.getText().toString().equals(obj.correctAnswers[index])) {
            //btn.setTextColor(getColor(R.color.green));
            btn.setBackgroundDrawable(getDrawable(R.drawable.btn_background_green));

            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
           if(marks==0){
               marks++;
           }
           else{
               level=(int)Math.ceil((double) question/10);
               marks=marks+(2*level);
           }
            txtQheading.setText(Score + marks);
        }
        else {
            //btn.setTextColor(getColor(R.color.red));
            btn.setBackgroundDrawable(getDrawable(R.drawable.btn_background_red));

            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);

            life--;
            if(life==2){
                h1.setVisibility(View.GONE);
            } else if (life==1) {
                h2.setVisibility(View.GONE);
            } else if (life==0) {
                h3.setVisibility(View.GONE);
                this.btn.setText("Finish");
            }
        }

        btn.setTextColor(getColor(R.color.white));
        txtAns.setText(Ans + obj.correctAnswers[index]);
        mediaPlayer.setVolume(0.7f,0.7f);
        mediaPlayer.start();

        this.btn.setEnabled(true);
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        D.setEnabled(false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       stopMusic();
    }

    void stopMusic(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer2.start();
            }
        },1000);
    }

    private void slideInViews() {

//        TranslateAnimation animation = new TranslateAnimation(0, -txtQ.getWidth(), 0, 0);
//        animation.setDuration(400);
//        txtQ.startAnimation(animation);
//
//        animation = new TranslateAnimation(0, -linearLayout.getWidth(), 0, 0);
//        animation.setDuration(400);
//        linearLayout.startAnimation(animation);
//
//       new Handler().postDelayed(new Runnable() {
//           @Override
//           public void run() {
//               TranslateAnimation animation = new TranslateAnimation(txtQ.getWidth(), 0, 0, 0);
//               animation.setDuration(400);
//               txtQ.startAnimation(animation);
//
//               animation = new TranslateAnimation(linearLayout.getWidth(), 0, 0, 0);
//               animation.setDuration(400);
//               linearLayout.startAnimation(animation);
//           }
//       },400);


        ScaleAnimation animation = new ScaleAnimation(0.9f, 1.0f, .9f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        txtQ.startAnimation(animation);

        animation = new ScaleAnimation(0.9f, 1.0f, .9f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        linearLayout.startAnimation(animation);

    }


}

