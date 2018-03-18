package dehaagsehogeschool.cyberdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tony on 2/20/2018.
 */

public class DigiveiligSpelActivity extends AppCompatActivity {

    public final static String TAG = DigiveiligSpelActivity.class.getSimpleName();

    Button level_1, level_2, level_3, level_4,
            level_5, level_6, level_7, level_8,
            level_9, level_10, level_11, level_12,
            level_13, level_14, level_15, level_16;

    TextView starScore;
    int starScoreInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digiveilig_spel_activity);

        Log.i(TAG, "I am here!");

        starScore = (TextView) findViewById(R.id.digiveilig_spel_star_score);
        level_1 = (Button) findViewById(R.id.level_button_1);
        level_2 = (Button) findViewById(R.id.level_button_2);
        level_3 = (Button) findViewById(R.id.level_button_3);
        level_4 = (Button) findViewById(R.id.level_button_4);
        level_5 = (Button) findViewById(R.id.level_button_5);
        level_6 = (Button) findViewById(R.id.level_button_6);
        level_7 = (Button) findViewById(R.id.level_button_7);
        level_8 = (Button) findViewById(R.id.level_button_8);
        level_9 = (Button) findViewById(R.id.level_button_9);
        level_10 = (Button) findViewById(R.id.level_button_10);
        level_11 = (Button) findViewById(R.id.level_button_11);
        level_12 = (Button) findViewById(R.id.level_button_12);
        level_13 = (Button) findViewById(R.id.level_button_13);
        level_14 = (Button) findViewById(R.id.level_button_14);
        level_15 = (Button) findViewById(R.id.level_button_15);
        level_16 = (Button) findViewById(R.id.level_button_16);
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

    private void setStarScore() {
        starScore.setText(starScoreInt);
    }


    public void startLevel1(View view){

    }

}
