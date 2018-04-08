package dehaagsehogeschool.digiveilig.spel.games;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dehaagsehogeschool.digiveilig.BaseActivity;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.GameManager;
import dehaagsehogeschool.digiveilig.managers.QuestionManager;
import dehaagsehogeschool.digiveilig.models.GameManagerSettings;
import dehaagsehogeschool.digiveilig.models.Question;

/**
 * Created by Thomas on 27-Mar-18.
 */

public class QuizActivity extends BaseActivity implements ActivityInterface {

    private TextView textViewQuestion;
    private TextView questionNumberView;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    private Button buttonChoice4;

    private Question selectedQuestion;
    private int questionAnswerNumber = 0;
    private int score = 0;

    private List<Question> questions;
    private GameManager gameManager;
    private TextView gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        initializeObjects();
        initializeGameManager();
        initializeQuestions();

        updateQuestion();
    }

    private void initializeGameManager() {
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

        // Set level
        ((TextView) findViewById(R.id.quiz_level_text))
                .setText("Level: " + Integer.toString(getIntent().getExtras().getInt(GameSettings.LEVEL_ID)));
    }

    private void initializeQuestions() {
        QuestionManager questionManager = new QuestionManager(GameSettings.getCurrentGameSetting(gameManager.settings.levelId), getApplicationContext());
        questions = questionManager.getQuestions();

        Collections.shuffle(questions);
    }

    private void updateQuestion() {
        if (gameManager != null) {
            gameManager.score = score;
        }

        if (questionAnswerNumber >= questions.size()) {
            gameManager.gameEnded = true;
            return;
        }

        selectedQuestion = questions.get(questionAnswerNumber);

        textViewQuestion.setText(selectedQuestion.question);
        Collections.shuffle(selectedQuestion.answers);
        resetButtonsView();
        setButtons();
        questionNumberView.setText("Vraag " + "(" + (questionAnswerNumber + 1) + "/" + questions.size() + ")");
        questionAnswerNumber++;
    }

    private void checkAnswer(String answer) {
        if (answer.equals(selectedQuestion.rightAnswer)) score++;

        updateQuestion();
    }

    private void setButtons() {
        int totalAnswers = selectedQuestion.answers.size();
        if (totalAnswers == 4) {
            buttonChoice1.setText(selectedQuestion.answers.get(0));
            buttonChoice2.setText(selectedQuestion.answers.get(1));
            buttonChoice3.setText(selectedQuestion.answers.get(2));
            buttonChoice4.setText(selectedQuestion.answers.get(3));
        } else if (totalAnswers == 3) {
            buttonChoice1.setText(selectedQuestion.answers.get(0));
            buttonChoice2.setText(selectedQuestion.answers.get(1));
            buttonChoice3.setText(selectedQuestion.answers.get(2));
            buttonChoice4.setVisibility(View.INVISIBLE);
        } else if (totalAnswers == 2) {
            buttonChoice1.setText(selectedQuestion.answers.get(0));
            buttonChoice2.setText(selectedQuestion.answers.get(1));
            buttonChoice3.setVisibility(View.INVISIBLE);
            buttonChoice4.setVisibility(View.INVISIBLE);
        }
    }

    private void resetButtonsView(){
        buttonChoice1.setVisibility(View.VISIBLE);
        buttonChoice2.setVisibility(View.VISIBLE);
        buttonChoice3.setVisibility(View.VISIBLE);
        buttonChoice4.setVisibility(View.VISIBLE);
    }

    public void stopSpel(View view) {
        stopGame();
    }

    private void stopGame() {
        super.onBackPressed();
        super.finish();
        gameManager.stopTimer();
    }

    public void choiceButton(View view) {
        checkAnswer(((Button) view).getText().toString());
    }

    @Override
    public void onBackPressed() {
        stopGame();
    }
}