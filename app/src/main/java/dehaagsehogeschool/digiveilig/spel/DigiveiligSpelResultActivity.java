package dehaagsehogeschool.digiveilig.spel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dehaagsehogeschool.digiveilig.BaseActivity;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.HomeActivity;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;

public class DigiveiligSpelResultActivity extends BaseActivity implements ActivityInterface {

    private TextView finishedGameInSeconds, currentLevelNumber, gameHints;
    private ImageView starsEarned;
    private int stars, finishTime, levelNumber;
    private int secondsForOneStar, secondsForTwoStars, secondsForThreeStars;
    private int correctAnswers;
    private Integer[] starScoreImages = new Integer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digiveilig_spel_result_activity);
        initializeObjects();
        receiveResults();
        showResults();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void initializeObjects() {
        starsEarned = findViewById(R.id.digiveilig_spel_result_stars);
        finishedGameInSeconds = findViewById(R.id.digiveilig_spel_result_seconden);
        currentLevelNumber = findViewById(R.id.digiveilig_spel_result_level);
        gameHints = findViewById(R.id.digiveilig_spel_result_hint);

        starScoreImages[0] = R.drawable.digiveilig_spel_star_result_0;
        starScoreImages[1] = R.drawable.digiveilig_spel_star_result_1;
        starScoreImages[2] = R.drawable.digiveilig_spel_star_result_2;
        starScoreImages[3] = R.drawable.digiveilig_spel_star_result_3;
    }

    private void receiveResults() {
        Bundle results = getIntent().getExtras();

        try {
            stars = results.getInt(GameSettings.RESULT_STARS);
            correctAnswers = results.getInt(GameSettings.RESULT_SCORE);
            finishTime = results.getInt(GameSettings.FINISH_TIME);
            levelNumber = results.getInt(GameSettings.LEVEL_ID);
            secondsForOneStar = results.getInt(GameSettings.SECONDS_FOR_ONE_STAR);
            secondsForTwoStars = results.getInt(GameSettings.SECONDS_FOR_TWO_STAR);
            secondsForThreeStars = results.getInt(GameSettings.SECONDS_FOR_THREE_STAR);
            String gameType = results.getString(GameSettings.LEVEL_GAME);
        } catch (Exception e) {
            finish();
            Toast.makeText(this, "Oops, er ging iets fout. Resultaten kunnen niet worden getoond.", Toast.LENGTH_SHORT);
        }
    }

    private void showResults() {
        finishedGameInSeconds.setText(finishTime + " seconden");
        currentLevelNumber.setText("Level " + levelNumber);
        starsEarned.setBackground(getDrawable(starScoreImages[stars]));

        switch (stars) {
            case 0:
                gameHints.setText("Voltooi het spel in " + secondsForOneStar + " seconden om 1 ster te behalen.");
                break;
            case 1:
                gameHints.setText("Voltooi het spel in " + secondsForTwoStars + " seconden om 2 sterren te behalen.");
                break;
            case 2:
                gameHints.setText("Voltooi het spel in " + secondsForThreeStars + " seconden om 3 sterren te behalen.");
                break;
            case 3:
                gameHints.setText("Spel uitgespeeld!");
                break;
        }
    }

    public void ToGamesScreen(View view) {
        finish();
    }
}
