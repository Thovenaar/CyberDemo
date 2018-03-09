package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Tony on 2/17/2018.
 */

public class QuizSpelActivity extends AppCompatActivity {

    public final static String TAG = QuizSpelActivity.class.getSimpleName();

    private QuizLibary quizLibary = new QuizLibary();

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
    public int score = 0;
    private int time = 10;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_spel_activity);

        Log.i(TAG, "I am created!");

        question = (TextView) findViewById(R.id.quiz_spel_question);
        questionNumberView = (TextView) findViewById(R.id.quiz_spel_question_number);
        buttonChoice1 = (Button) findViewById(R.id.quiz_spel_choice1);
        buttonChoice2 = (Button) findViewById(R.id.quiz_spel_choice2);
        buttonChoice3 = (Button) findViewById(R.id.quiz_spel_choice3);
        buttonChoice4 = (Button) findViewById(R.id.quiz_spel_choice4);
        stopButton = (Button) findViewById(R.id.quiz_spel_stop_button);

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

        Intent showScore = new Intent(getApplicationContext(), QuizSpelResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
        finish();

    }

    private void updateQuestion() {
        
        //Zodra er geen vragen meer over zijn, laat dan de score zien.
        if (questionAnswerNumber < quizLibary.quizQuestions.length) {

            question.setText(quizLibary.getQuestion(questionAnswerNumber));
            buttonChoice1.setText(quizLibary.getChoice1(questionAnswerNumber));
            buttonChoice2.setText(quizLibary.getChoice2(questionAnswerNumber));
            buttonChoice3.setText(quizLibary.getChoice3(questionAnswerNumber));
            buttonChoice4.setText(quizLibary.getChoice4(questionAnswerNumber));

            questionAnswer = quizLibary.getAnswer(questionAnswerNumber);
            questionNumberView.setText("Vraag" + questionAnswerNumber);
            questionNumber++;
            questionAnswerNumber++;

        } else {
            score++;
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
                                    if (quizLibary.quizQuestions.length > score) {
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
        Intent showScore = new Intent(getApplicationContext(), QuizSpelResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
    }

    public void choice1Click(View view) {

        if (buttonChoice1.getText() == questionAnswer) {

            updateQuestion();
            score++;

        }
    }

    public void choice2Click(View view) {

        if (buttonChoice2.getText() == questionAnswer) {

            updateQuestion();
            score++;

        }
    }

    public void choice3Click(View view) {

        if (buttonChoice3.getText() == questionAnswer) {

            updateQuestion();
            score++;

        }
    }

    public void choice4Click(View view) {

        if (buttonChoice4.getText() == questionAnswer) {

            updateQuestion();
            score++;

        }
    }
}

