package dehaagsehogeschool.cyberdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Tony on 2/17/2018.
 */

public class QuizActivity extends AppCompatActivity {

    private QuizQuestions quizQuestions = new QuizQuestions();

    private TextView quizScoreView;
    private TextView quizQuestionView;
    private Button quizButtonChoice1;
    private Button quizButtonChoice2;
    private Button quizButtonChoice3;
    private Button quizButtonChoice4;

    private String quizQuestionAnswer;
    private int quizQuestionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        quizQuestionView = (TextView) findViewById(R.id.question);
        quizButtonChoice1 = (Button) findViewById(R.id.choice1);
        quizButtonChoice2 = (Button) findViewById(R.id.choice2);
        quizButtonChoice3 = (Button) findViewById(R.id.choice3);
        quizButtonChoice4 = (Button) findViewById(R.id.choice4);

        updateQuestion();
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

        quizQuestionView.setText(quizQuestions.getQuestion(quizQuestionNumber));
        quizButtonChoice1.setText(quizQuestions.getChoice1(quizQuestionNumber));
        quizButtonChoice2.setText(quizQuestions.getChoice2(quizQuestionNumber));
        quizButtonChoice3.setText(quizQuestions.getChoice3(quizQuestionNumber));
        quizButtonChoice4.setText(quizQuestions.getChoice4(quizQuestionNumber));

        quizQuestionAnswer = quizQuestions.getAnswer(quizQuestionNumber);
        quizQuestionNumber++;

    }
}
