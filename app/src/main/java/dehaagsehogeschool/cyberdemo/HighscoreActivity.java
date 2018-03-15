package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Tony on 3/7/2018.
 */

public class HighscoreActivity extends AppCompatActivity {

    public final static String TAG = HighscoreActivity.class.getSimpleName();

    TextView highscore1, highscore2, highscore3, highscore4, highscore5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores_activity);
        Log.i(TAG, "I am created!");

        highscore1 = (TextView) findViewById(R.id.highscore_name1);
        highscore2 = (TextView) findViewById(R.id.highscore_name2);
        highscore3 = (TextView) findViewById(R.id.highscore_name3);
        highscore4 = (TextView) findViewById(R.id.highscore_name4);
        highscore5 = (TextView) findViewById(R.id.highscore_name5);

        //Ophalen huidige highscores toetsresultaat
        SharedPreferences gameData = getSharedPreferences("Game_Data", Context.MODE_PRIVATE);
        int gameDatahighscore1 = gameData.getInt("toetsScore1", 0);
        int gameDatahighscore2 = gameData.getInt("toetsScore2", 0);
        int gameDatahighscore3 = gameData.getInt("toetsScore3", 0);
        int gameDatahighscore4 = gameData.getInt("toetsScore4", 0);
        int gameDatahighscore5 = gameData.getInt("toetsScore5", 0);

        //Vullen van de highscors in de labels
        highscore1.setText("Cijfer:"+""+gameDatahighscore1);
        highscore2.setText("Cijfer:"+""+gameDatahighscore2);
        highscore3.setText("Cijfer:"+""+gameDatahighscore3);
        highscore4.setText("Cijfer:"+""+gameDatahighscore4);
        highscore5.setText("Cijfer:"+""+gameDatahighscore5);
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
