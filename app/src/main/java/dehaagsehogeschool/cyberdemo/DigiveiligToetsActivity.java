package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Tony on 2/17/2018.
 */

public class DigiveiligToetsActivity extends AppCompatActivity {

    public final static String TAG = DigiveiligToetsActivity.class.getSimpleName();

    private TextView textViewQuestion;
    private TextView questionNumberView;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    private Button buttonChoice4;

    private Question selectedQuestion;
    private int questionAnswerNumber = 0;
    public int score = 0;

    // SETTINGS
    private boolean randomize = true;
    private int time = 100;

    Timer timer = new Timer();
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digiveilig_toets_activity);

        initializeObjects();
        initializeQuestions();

        Log.i(TAG, "I am created!");

        updateQuestion();
        startTimer();
    }

    private void initializeObjects() {
        textViewQuestion = findViewById(R.id.quiz_spel_question);
        questionNumberView = findViewById(R.id.quiz_spel_question_number);
        buttonChoice1 = findViewById(R.id.quiz_spel_choice1);
        buttonChoice2 = findViewById(R.id.quiz_spel_choice2);
        buttonChoice3 = findViewById(R.id.quiz_spel_choice3);
        buttonChoice4 = findViewById(R.id.quiz_spel_choice4);
    }

    private void initializeQuestions() {
        QuestionProvider provider = new QuestionProvider("questions.xml", getApplicationContext());
        questions = provider.getQuestions();

        if (randomize) Collections.shuffle(questions);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        timer.cancel();
    }

    public void showScore() {
        timer.cancel();

        Intent showScore = new Intent(getApplicationContext(), DigiveiligToetsResultActivity.class);
        showScore.putExtra("SCORE", score);
        showScore.putExtra("QUESTIONS", questions.size());
        startActivity(showScore);

        finish();
    }

    private void updateQuestion() {
        if (questionAnswerNumber >= questions.size()) {
            showScore();
            return;
        }

        selectedQuestion = questions.get(questionAnswerNumber);
        textViewQuestion.setText(selectedQuestion.question);

        if (randomize) Collections.shuffle(selectedQuestion.answers);

        buttonChoice1.setText(selectedQuestion.answers.get(0));
        buttonChoice2.setText(selectedQuestion.answers.get(1));
        buttonChoice3.setText(selectedQuestion.answers.get(2));
        buttonChoice4.setText(selectedQuestion.answers.get(3));

        questionNumberView.setText("Vraag " + (questionAnswerNumber + 1));
        questionAnswerNumber++;
    }

    private void checkAnswer(String answer) {
        if (answer.equals(selectedQuestion.rightAnswer)) score++;

        updateQuestion();
    }

    public void stopSpel(View view) {
        showScore();
    }

    public void startTimer() {
        TimerTask task =
                new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView timer = findViewById(R.id.quiz_spel_timer);
                                timer.setText("Tijd over:" + time + "");
                                if (time > 0) {
                                    if (questions.size() > score) {
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

    public void choice1Click(View view) {
        checkAnswer(buttonChoice1.getText().toString());
    }

    public void choice2Click(View view) {
        checkAnswer(buttonChoice2.getText().toString());
    }

    public void choice3Click(View view) {
        checkAnswer(buttonChoice3.getText().toString());
    }

    public void choice4Click(View view) {
        checkAnswer(buttonChoice4.getText().toString());
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
}

