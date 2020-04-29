package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView resultTV;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int noOfQuestion = 0;
    TextView scoreTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView answerTextView;
    TextView timerTextView;
    Button resetBtn;
    Button goButton;
    RelativeLayout relativeLayout;

    public void chooseAnswer(View view) {
        resultTV=findViewById(R.id.resultTV);
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTV.setText("Correct!");
            score++;
        } else {
            resultTV.setText("Wrong :(");
        }
        noOfQuestion++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestion));
        newQuestion();
    }

    public void newQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        answerTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        relativeLayout = findViewById(R.id.layoutTwo);
        relativeLayout.setVisibility(View.INVISIBLE);

        answerTextView = findViewById(R.id.answerTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        timerTextView = findViewById(R.id.timerTextView);
        resetBtn = findViewById(R.id.resetBtn);
        scoreTextView=findViewById(R.id.scoreTextView);
        newQuestion();
    }

    public void resetGame(View view) {
        score = 0;
        noOfQuestion = 0;
        timerTextView.setText("30s");
        newQuestion();
        resultTV.setText("");
        scoreTextView.setText("0/0");
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000 + "s"));

            }

            @Override
            public void onFinish() {
                resultTV.setText("Done!");
                resetBtn.setVisibility(View.VISIBLE);

            }
        }.start();
        resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setVisibility(View.INVISIBLE);
    }


    public void goButton(View view) {

        goButton.setVisibility(View.INVISIBLE);
        relativeLayout = findViewById(R.id.layoutTwo);
        relativeLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000 + "s"));
            }

            @Override
            public void onFinish() {
                resultTV.setText("Done!");
                resetBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

}
