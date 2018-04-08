package dehaagsehogeschool.digiveilig.spel.games;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dehaagsehogeschool.digiveilig.BaseActivity;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.GameManager;
import dehaagsehogeschool.digiveilig.managers.ScenarioManager;
import dehaagsehogeschool.digiveilig.models.GameManagerSettings;
import dehaagsehogeschool.digiveilig.models.Scenario;

public class ScenarioActivity extends BaseActivity implements ActivityInterface {

    private ImageView scenarioImage;
    private Button button1, button2, button3;
    private TextView question, gameTimer;

    private Scenario currentScenario;
    private GameManager gameManager;

    private List<Scenario> scenarios;
    private int scenarioQuestionNumber = 0;
    private int score = 0;
    private int imageClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario_activity);

        initializeObjects();
        initializeGameManager();
        initializeScenarios();

        updateScenario();
    }

    @Override
    public void initializeObjects() {
        scenarioImage = findViewById(R.id.scenario_image);
        button1 = findViewById(R.id.scenario_button_1);
        button2 = findViewById(R.id.scenario_button_2);
        button3 = findViewById(R.id.scenario_button_3);
        question = findViewById(R.id.scenario_question);
        gameTimer = findViewById(R.id.scenario_gametimer);
    }

    private void initializeGameManager() {
        GameManagerSettings settings = new GameManagerSettings();
        settings.gameTime = 40;
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

    private void initializeScenarios() {
        ScenarioManager scenarioManager = new ScenarioManager(GameSettings.getCurrentGameSetting(gameManager.settings.levelId), getApplicationContext());
        scenarios = scenarioManager.getScenarios();
    }

    private void updateScenario() {
        if (gameManager != null) {
            gameManager.score = score;
        }
        if (scenarioQuestionNumber >= scenarios.size()) {
            gameManager.gameEnded = true;
            return;
        }
        currentScenario = scenarios.get(scenarioQuestionNumber);

        scenarioImage.setImageResource(currentScenario.scenarioImage);
        resetButtonsView();
        setButtons();
        question.setText(currentScenario.question);
        scenarioQuestionNumber++;
        imageClicked = 0;
    }

    private void setButtons() {
        int totalAnswers = currentScenario.answers.size();
        if (totalAnswers > 2) {
            button1.setText(currentScenario.answers.get(0));
            button2.setText(currentScenario.answers.get(1));
            button3.setText(currentScenario.answers.get(2));
        } else {
            button1.setText(currentScenario.answers.get(0));
            button2.setText(currentScenario.answers.get(1));
            button3.setVisibility(View.INVISIBLE);
        }
    }
    private void resetButtonsView(){
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }

    public void buttonClick(View view) {
        checkAnswer(((Button) view).getText().toString());
    }

    private void checkAnswer(String answer) {
        if (answer.equals(currentScenario.rightAnswer)) score++;

        updateScenario();
    }

    public void clickedImage(View view) {
        if (imageClicked == 0) {
            scenarioImage.getLayoutParams().height = scenarioImage.getHeight() + 100;
            scenarioImage.getLayoutParams().width = scenarioImage.getWidth() + 100;
            imageClicked++;
        } else if (imageClicked == 1) {
            scenarioImage.getLayoutParams().height = scenarioImage.getHeight() - 100;
            scenarioImage.getLayoutParams().width = scenarioImage.getWidth() - 100;
            imageClicked = 0;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gameManager.stopTimer();
        super.finish();
    }

    public void stopGame(View view){
        gameManager.stopTimer();
        super.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
