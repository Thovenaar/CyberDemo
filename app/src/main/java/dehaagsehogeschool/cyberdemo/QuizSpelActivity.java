package dehaagsehogeschool.cyberdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private QuizLibary quizLibary = new QuizLibary();

    private TextView quizScoreView;
    private TextView quizQuestionView;
    private Button quizButtonChoice1;
    private Button quizButtonChoice2;
    private Button quizButtonChoice3;
    private Button quizButtonChoice4;

    private String quizQuestionAnswer;
    private int quizQuestionNumber = 0;
    private int score = 0;
    private int time = 180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_spel_activity);

        quizQuestionView = (TextView) findViewById(R.id.quiz_spel_question);
        quizButtonChoice1 = (Button) findViewById(R.id.quiz_spel_choice1);
        quizButtonChoice2 = (Button) findViewById(R.id.quiz_spel_choice2);
        quizButtonChoice3 = (Button) findViewById(R.id.quiz_spel_choice3);
        quizButtonChoice4 = (Button) findViewById(R.id.quiz_spel_choice4);

        updateQuestion();
        startTimer();

    }

    public void quitGameClick(View view) {
        finish();
    }

    public void choice1Click(View view) {

        if (quizButtonChoice1.getText() == quizQuestionAnswer) {

            updateQuestion();
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

        }
    }

    public void choice2Click(View view) {

        if (quizButtonChoice2.getText() == quizQuestionAnswer) {

            updateQuestion();
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

        }
    }

    public void choice3Click(View view) {

        if (quizButtonChoice3.getText() == quizQuestionAnswer) {

            updateQuestion();
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

        }
    }

    public void choice4Click(View view) {

        if (quizButtonChoice4.getText() == quizQuestionAnswer) {

            updateQuestion();
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateQuestion() {

        quizQuestionView.setText(quizLibary.getQuestion(quizQuestionNumber));
        quizButtonChoice1.setText(quizLibary.getChoice1(quizQuestionNumber));
        quizButtonChoice2.setText(quizLibary.getChoice2(quizQuestionNumber));
        quizButtonChoice3.setText(quizLibary.getChoice3(quizQuestionNumber));
        quizButtonChoice4.setText(quizLibary.getChoice4(quizQuestionNumber));

        quizQuestionAnswer = quizLibary.getAnswer(quizQuestionNumber);
        quizQuestionNumber++;

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
                        timer.setText(time + "");
                        if (time > 0) {
                            time -= 1;
                        }
                        else {
                            timer.setText("Spel klaar");
                            //TODO
                        }
                    }
                });
            }
        };

        t.scheduleAtFixedRate(task, 0, 1000);
    }
}
