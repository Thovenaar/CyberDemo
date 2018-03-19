package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dehaagsehogeschool.cyberdemo.games.ResultProvider;

public class HomeActivity extends AppCompatActivity {

    public final static String TAG = HomeActivity.class.getSimpleName();

    TextView starScore, toetsResultaat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Log.i(TAG, "I am created!");

        starScore = (TextView) findViewById(R.id.home_star_score);
        toetsResultaat = (TextView) findViewById(R.id.home_toets_result_score);
    }

    @Override
    protected void onStart() {
        SharedPreferences gameData = getSharedPreferences("Game_Data", Context.MODE_PRIVATE);
        int starScoreGameData = gameData.getInt("starScore", 0);
        int toetsScoreGameData = gameData.getInt("toetsScore1", 0);

        ResultProvider resultProvider = new ResultProvider("Game_Data", getApplicationContext());


        toetsResultaat.setText("Cijfer: " + resultManager.getHighestResult());
        starScore.setText(starScoreGameData+" "+"Sterren");

//        SharedPreferences.Editor editor = gameData.edit();
//        editor.clear();
//        editor.commit();

        super.onStart();
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

    public void startDigiveiligToets(View view) {

        Intent intent = new Intent(this, DigiveiligToetsAnnouncementActivity.class);
        startActivity(intent);
    }

    public void startDigiveiligSpel(View view) {

        Intent intent = new Intent(this, DigiveiligSpelActivity.class);
        startActivity(intent);
    }

    public void startHighscore(View view) {

        Intent intent = new Intent(this, DigiveiligSpelToetsResultatenActivity.class);
        startActivity(intent);
    }

    public void startHelp(View view) {

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);

    }
}
