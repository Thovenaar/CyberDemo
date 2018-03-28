package dehaagsehogeschool.digiveilig.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.GameManager;
import dehaagsehogeschool.digiveilig.managers.QuestionManager;
import dehaagsehogeschool.digiveilig.models.GameManagerSettings;
import dehaagsehogeschool.digiveilig.models.Question;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsActivity;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsEndActivity;

/**
 * Created by Thomas on 27-Mar-18.
 */

public class QuizActivity extends AppCompatActivity implements ActivityInterface {

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

    List<Question> questions;
    private GameManager gameManager;
    private TextView gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        initializeObjects();
        initializeQuestions();

        Log.i(TAG, "I am created!");

        updateQuestion();
        initializeGameManager();
    }

    private void initializeGameManager(){
        GameManagerSettings settings = new GameManagerSettings();
        settings.gameTime = 30;
        settings.context = this;
        settings.gameTimer = gameTimer;
        settings.secondsForZeroStars = 5;
        settings.secondsForOneStar = 10;
        settings.secondsForTwoStars = 15;
        settings.secondsForThreeStars = 20;
        settings.levelId = getIntent().getExtras().getInt(GameSettings.LEVEL_ID);
        settings.game = Game.valueOf(getIntent().getExtras().getString(GameSettings.LEVEL_GAME));

        gameManager = new GameManager(settings);
    }

    @Override
    public void initializeObjects() {
        textViewQuestion = findViewById(R.id.quiz_spel_question);
        questionNumberView = findViewById(R.id.quiz_spel_question_number);
        buttonChoice1 = findViewById(R.id.quiz_spel_choice1);
        buttonChoice2 = findViewById(R.id.quiz_spel_choice2);
        buttonChoice3 = findViewById(R.id.quiz_spel_choice3);
        buttonChoice4 = findViewById(R.id.quiz_spel_choice4);
        gameTimer = findViewById(R.id.quiz_spel_timer);
    }

    private void initializeQuestions() {
        QuestionManager questionManager = new QuestionManager(GameSettings.QUESTIONS_LOCATION, getApplicationContext());
        questions = questionManager.getQuestions();

        Collections.shuffle(questions);
    }

    private void updateQuestion() {
        if (gameManager != null) {
            gameManager.score = score;
        }

        if (questionAnswerNumber >= questions.size()) {
            gameManager.gameEnded = true;
            // TODO
            return;
        }

        selectedQuestion = questions.get(questionAnswerNumber);
        textViewQuestion.setText(selectedQuestion.question);

       Collections.shuffle(selectedQuestion.answers);

        buttonChoice1.setText(selectedQuestion.answers.get(0));
        buttonChoice2.setText(selectedQuestion.answers.get(1));
        buttonChoice3.setText(selectedQuestion.answers.get(2));
        buttonChoice4.setText(selectedQuestion.answers.get(3));

        questionNumberView.setText("Vraag " + "(" + (questionAnswerNumber + 1) + "/" + questions.size() + ")");
        questionAnswerNumber++;
    }

    private void checkAnswer(String answer) {
        if (answer.equals(selectedQuestion.rightAnswer)) score++;

        updateQuestion();
    }

    public void stopSpel(View view) {
        super.finish();
    }

    public void choiceButton(View view) {
        checkAnswer(((Button) view).getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.finish();
        gameManager.stopTimer();
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