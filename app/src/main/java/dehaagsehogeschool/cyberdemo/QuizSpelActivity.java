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
    private TextView quizQuestionView;
    private TextView quizQuestionNumber;
    private Button quizButtonChoice1;
    private Button quizButtonChoice2;
    private Button quizButtonChoice3;
    private Button quizButtonChoice4;
    private Button quizStopButton;

    private String quizQuestionAnswer;
    private int questionNumber = 1;
    private int questionAnswerNumber = 0;
    public int score = 0;
    private int time = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_spel_activity);

        Log.i(TAG, "I am created!");

        quizQuestionView = (TextView) findViewById(R.id.quiz_spel_question);
        quizQuestionNumber = (TextView) findViewById(R.id.quiz_spel_question_number);
        quizButtonChoice1 = (Button) findViewById(R.id.quiz_spel_choice1);
        quizButtonChoice2 = (Button) findViewById(R.id.quiz_spel_choice2);
        quizButtonChoice3 = (Button) findViewById(R.id.quiz_spel_choice3);
        quizButtonChoice4 = (Button) findViewById(R.id.quiz_spel_choice4);
        quizStopButton = (Button) findViewById(R.id.quiz_spel_stop_button);

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

    public void quitGameClick(View view) {
        Intent showScore = new Intent(getApplicationContext(), QuizSpelResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
        finish();
    }

    public void choice1Click(View view) {

        if (quizButtonChoice1.getText() == quizQuestionAnswer) {

            updateQuestion();
            score++;
        }
    }

    public void choice2Click(View view) {

        if (quizButtonChoice2.getText() == quizQuestionAnswer) {

            updateQuestion();
            score++;

        }
    }

    public void choice3Click(View view) {

        if (quizButtonChoice3.getText() == quizQuestionAnswer) {

            updateQuestion();
            score++;

        }
    }

    public void choice4Click(View view) {

        if (quizButtonChoice4.getText() == quizQuestionAnswer) {

            updateQuestion();
            score++;

        }
    }

    private void updateQuestion() {

        if (questionAnswerNumber < quizLibary.quizQuestions.length) {

            quizQuestionView.setText(quizLibary.getQuestion(questionAnswerNumber));
            quizButtonChoice1.setText(quizLibary.getChoice1(questionAnswerNumber));
            quizButtonChoice2.setText(quizLibary.getChoice2(questionAnswerNumber));
            quizButtonChoice3.setText(quizLibary.getChoice3(questionAnswerNumber));
            quizButtonChoice4.setText(quizLibary.getChoice4(questionAnswerNumber));

            quizQuestionAnswer = quizLibary.getAnswer(questionAnswerNumber);
            quizQuestionNumber.setText("Vraag" + questionNumber);
            questionNumber++;
            questionAnswerNumber++;

        } else {

            //If elde tijdelijk gedaan omdat anders de applicatie crasht op nullpointer.
            finish();

        }
    }
    public void startTimer() {
        Timer t = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        TextView timer = (TextView) findViewById(R.id.quiz_spel_timer);
                        timer.setText("Tijd over:" + time + "");
                        if (time > 0) {
                            time -= 1;
                        } else {
                        cancel();
                        finish();
                        showScore();
                        }
                    }
                });
            }
        };

        Log.i(TAG, "test");
        t.scheduleAtFixedRate(task, 0, 1000);
    }

    public void showScore(){
        Intent showScore = new Intent(getApplicationContext(), QuizSpelResultActivity.class);
        showScore.putExtra("SCORE", score);
        startActivity(showScore);
    }
}

