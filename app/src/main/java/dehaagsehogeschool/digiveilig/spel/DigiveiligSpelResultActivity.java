package dehaagsehogeschool.digiveilig.spel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;

public class DigiveiligSpelResultActivity extends AppCompatActivity implements ActivityInterface {

    private TextView finishedGameInSeconds, currentLevelNumber, timeHint;
    private ImageView starsEarned;
    private int stars, finishTime, levelNumber;
    private int secondsForOneStar, secondsForTwoStars, secondsForThreeStars;
    String gameType;
    private int score;

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
        timeHint = findViewById(R.id.digiveilig_spel_result_hint);
    }

    private void receiveResults() {
        Bundle results = getIntent().getExtras();

        try {
            stars = results.getInt(GameSettings.RESULT_STARS);
            score = results.getInt(GameSettings.RESULT_SCORE);
            finishTime = results.getInt(GameSettings.FINISH_TIME);
            levelNumber = results.getInt(GameSettings.LEVEL_ID);
            secondsForOneStar = results.getInt(GameSettings.SECONDS_FOR_ONE_STAR);
            secondsForTwoStars = results.getInt(GameSettings.SECONDS_FOR_TWO_STAR);
            secondsForThreeStars = results.getInt(GameSettings.SECONDS_FOR_THREE_STAR);
            gameType = results.getString(GameSettings.LEVEL_GAME);
        } catch (Exception e) {
            finish();
            Toast.makeText(this, "Oops, er ging iets fout. Resultaten kunnen niet worden getoond.", Toast.LENGTH_SHORT);
        }
    }

    private void showResults() {
        finishedGameInSeconds.setText(finishTime + " " + "seconden");
        currentLevelNumber.setText("level" + " " + levelNumber);

        if (gameType.equals("MEMORY")) {
            switch (stars) {
                case 0:
                    starsEarned.setBackground(getDrawable(R.drawable.digiveilig_spel_star_result_0));
                    timeHint.setText("Haal het spel in " + secondsForOneStar + " seconden om 1 ster te halen");
                    break;
                case 1:
                    starsEarned.setBackground(getDrawable(R.drawable.digiveilig_spel_star_result_1));
                    timeHint.setText("Haal het spel in " + secondsForTwoStars + " seconden om 1 ster meer te halen");
                    break;
                case 2:
                    starsEarned.setBackground(getDrawable(R.drawable.digiveilig_spel_star_result_2));
                    timeHint.setText("Haal het spel in " + secondsForThreeStars + " seconden om alle drie de sterren te halen!");
                    break;
                case 3:
                    starsEarned.setBackground(getDrawable(R.drawable.digiveilig_spel_star_result_3));
                    timeHint.setText("Je hebt dit level al uitgespeeld!");
                    break;
            }
        } else if (gameType.equals("QUIZ")) {
            if(score > 1)timeHint.setText("Je hebt in "+ finishTime + " seconden " +score+" vragen goed kunnen beantwoorden");
            else timeHint.setText("Je hebt in "+ finishTime + " seconden " +score+" vraag goed kunnen beantwoorden");
        }
    }
}
