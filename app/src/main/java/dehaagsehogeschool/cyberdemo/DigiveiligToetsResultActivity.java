package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DigiveiligToetsResultActivity extends AppCompatActivity {

    public final static String TAG = DigiveiligToetsResultActivity.class.getSimpleName();

    private TextView newScoreLabel;
    private TextView highscoreLabel;
    private int highscore1;
    private int highscore2;
    private int highscore3;
    private int highscore4;
    private int highscore5;

    private int score;
    SharedPreferences gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "I am created!");

        setContentView(R.layout.digiveilig_toets_result_activity);
        super.onCreate(savedInstanceState);

        newScoreLabel = (TextView) findViewById(R.id.score_result_current_score);
        highscoreLabel = (TextView) findViewById(R.id.score_result_higscore);

        //Ophalen van de score die is behaald in de digiveilig toets
        score = getIntent().getIntExtra("SCORE", 0);
        newScoreLabel.setText("Behaalde score: " + score);

        //Ophalen huidige highscores toetsresultaat
        gameData = getSharedPreferences("Game_Data", Context.MODE_PRIVATE);
        highscore1 = gameData.getInt("toetsScore1", 0);
        highscore2 = gameData.getInt("toetsScore2", 0);
        highscore3 = gameData.getInt("toetsScore3", 0);
        highscore4 = gameData.getInt("toetsScore4", 0);
        highscore5 = gameData.getInt("toetsScore5", 0);

        checkNewScore();
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

    public void backToMainScreen(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkNewScore() {
        if(score > highscore1){
                highscoreLabel.setText("Nieuwe highscore!: " + score);
                SharedPreferences.Editor editor = gameData.edit();
                editor.putInt("toetsScore2",highscore1);
                editor.putInt("toetsScore1", score);
                editor.commit();
        }else if(score > highscore2 && score != highscore1 && score != highscore2
                && score != highscore3 && score !=highscore4 && score != highscore5) {
            highscoreLabel.setText("Highscore: " + highscore1);
            SharedPreferences.Editor editor = gameData.edit();
            editor.putInt("toetsScore3",highscore2);
            editor.putInt("toetsScore2", score);
            editor.commit();
        }else if(score > highscore3 && score != highscore1 && score != highscore2
                && score != highscore3 && score !=highscore4 && score != highscore5) {
            highscoreLabel.setText("Highscore: " + highscore1);
            SharedPreferences.Editor editor = gameData.edit();
            editor.putInt("toetsScore4",highscore3);
            editor.putInt("toetsScore3", score);
            editor.commit();
        }else if(score > highscore4 && score != highscore1 && score != highscore2
                && score != highscore3 && score !=highscore4 && score != highscore5) {
            highscoreLabel.setText("Highscore: " + highscore1);
            SharedPreferences.Editor editor = gameData.edit();
            editor.putInt("toetsScore5",highscore4);
            editor.putInt("toetsScore4", score);
            editor.commit();
        }else if(score > highscore5 && score != highscore1 && score != highscore2
                && score != highscore3 && score !=highscore4 && score != highscore5) {
            highscoreLabel.setText("Highscore: " + highscore1);
            SharedPreferences.Editor editor = gameData.edit();
            editor.putInt("toetsScore5", score);
            editor.commit();
        }else{
            highscoreLabel.setText("Highscore: " + highscore1);
        }
    }
}
