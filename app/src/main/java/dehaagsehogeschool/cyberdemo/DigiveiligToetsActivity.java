package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Tony on 2/17/2018.
 */

public class DigiveiligToetsActivity extends AppCompatActivity {

    public final static String TAG = DigiveiligToetsActivity.class.getSimpleName();

    private TextView quizScoreView;
    private TextView question;
    private TextView questionNumberView;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    private Button buttonChoice4;
    private Button stopButton;


    private String questionAnswer;
    private int questionNumber = 1;
    private int questionAnswerNumber = 0;
    private int test = 0;
    public int score = 0;
    private int time = 10;
    Timer timer = new Timer();
    String[] questions;
    String[] choices;
    String[] answers;
    List<String> questionList;
    List<String> choicesList;
    List<String> answersList;
    List<String> newChoicesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digiveilig_toets_activity);

        Log.i(TAG, "I am created!");

        question = (TextView) findViewById(R.id.quiz_spel_question);
        questionNumberView = (TextView) findViewById(R.id.quiz_spel_question_number);
        buttonChoice1 = (Button) findViewById(R.id.quiz_spel_choice1);
        buttonChoice2 = (Button) findViewById(R.id.quiz_spel_choice2);
        buttonChoice3 = (Button) findViewById(R.id.quiz_spel_choice3);
        buttonChoice4 = (Button) findViewById(R.id.quiz_spel_choice4);
        stopButton = (Button) findViewById(R.id.quiz_spel_stop_button);

        questions = getResources().getStringArray(R.array.questions);
        choices = getResources().getStringArray(R.array.choices);
        answers = getResources().getStringArray(R.array.answers);

        questionList = Arrays.asList(questions);
        choicesList = Arrays.asList(choices);
        answersList = Arrays.asList(answers);

        updateQuestion();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "I am paused!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "I am stopped!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "I am destroyed!");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        timer.cancel();
    }

    public void showScore() {

        Intent showScore = new Intent(getApplicationContext(), DigiveiligToetsResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
        finish();

    }

    private void updateQuestion() {
        if (questionAnswerNumber < questions.length) {
            String answer = choicesList.get(questionAnswerNumber);

            for (int i = 0; i < 4; i++) {
                newChoicesList.add(answer.split(",")[i]);
            }

            question.setText(questionList.get(questionAnswerNumber));

            buttonChoice1.setText(newChoicesList.get(0));
            buttonChoice2.setText(newChoicesList.get(1));
            buttonChoice3.setText(newChoicesList.get(2));
            buttonChoice4.setText(newChoicesList.get(3));

            questionAnswer = answersList.get(questionAnswerNumber);
            questionNumberView.setText("Vraag" + questionAnswerNumber);
            questionNumber++;
            questionAnswerNumber++;
            newChoicesList.clear();
        } else {
            showScore();
        }
    }

    public void startTimer() {
        TimerTask task =
                new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView timer = (TextView) findViewById(R.id.quiz_spel_timer);
                                timer.setText("Tijd over:" + time + "");
                                if (time > 0) {
                                    if (questions.length > test) {
                                        time -= 1;
                                    } else {
                                        cancel();
                                    }
                                } else {
                                    showScore();
                                    cancel();
                                }
                            }
                        });
                    }
                };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopSpel(View view) {
        timer.cancel();
        showScore();
        Intent showScore = new Intent(getApplicationContext(), DigiveiligToetsResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
    }


    public void choice1Click(View view) {
        if (buttonChoice1.getText().equals(questionAnswer)) {
            updateQuestion();
            score++;
            test++;
        }else{
            updateQuestion();
            test++;
        }
    }

    public void choice2Click(View view) {
        if (buttonChoice2.getText().equals(questionAnswer)) {
            updateQuestion();
            score++;
            test++;
        }else{
            updateQuestion();
            test++;
        }
    }

    public void choice3Click(View view) {
        if (buttonChoice3.getText().equals(questionAnswer)) {
            updateQuestion();
            score++;
            test++;
        }else{
            updateQuestion();
            test++;
        }
    }

    public void choice4Click(View view) {
        if (buttonChoice4.getText().equals(questionAnswer)) {
            updateQuestion();
            score++;
            test++;
        }else{
            updateQuestion();
            test++;
        }
    }
}

