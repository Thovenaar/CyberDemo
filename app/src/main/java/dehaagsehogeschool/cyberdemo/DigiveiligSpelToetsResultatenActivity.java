package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import dehaagsehogeschool.cyberdemo.games.ResultProvider;

/**
 * Created by Tony on 3/7/2018.
 */

public class DigiveiligSpelToetsResultatenActivity extends AppCompatActivity {

    public final static String TAG = DigiveiligSpelToetsResultatenActivity.class.getSimpleName();

    TextView highscore1, highscore2, highscore3, highscore4, highscore5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeObjects();

        //Ophalen huidige highscores toetsresultaat
        ResultProvider resultProvider = new ResultProvider("Game_Data", getApplicationContext());
        ArrayList<Double> results = resultProvider.getResults();

        //Vullen van de highscors in de labels
        highscore1.setText(resultProvider.getResultText(results, 0));
        highscore2.setText(resultProvider.getResultText(results, 1));
        highscore3.setText(resultProvider.getResultText(results, 2));
        highscore4.setText(resultProvider.getResultText(results, 3));
        highscore5.setText(resultProvider.getResultText(results, 4));
    }

    private void initializeObjects() {
        setContentView(R.layout.highscores_activity);
        Log.i(TAG, "I am created!");

        highscore1 = findViewById(R.id.highscore_name1);
        highscore2 = findViewById(R.id.highscore_name2);
        highscore3 = findViewById(R.id.highscore_name3);
        highscore4 = findViewById(R.id.highscore_name4);
        highscore5 = findViewById(R.id.highscore_name5);
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
