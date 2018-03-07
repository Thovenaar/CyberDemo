package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class QuizSpelResultActivity extends AppCompatActivity {

    public final static String TAG = QuizSpelResultActivity.class.getSimpleName();

    private TextView newScoreLabel;
    private TextView highscoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "I am created!");

        setContentView(R.layout.quiz_spel_result_activity);
        super.onCreate(savedInstanceState);

        newScoreLabel  = (TextView) findViewById(R.id.score_result_current_score);
        highscoreLabel = (TextView) findViewById(R.id.score_result_higscore);

        int score = getIntent().getIntExtra("SCORE", 0);
        newScoreLabel.setText("Behaalde score : " + score);

        SharedPreferences settings = getSharedPreferences("Game_Data", Context.MODE_PRIVATE);
        int highscore = settings.getInt("High_Score", 0);

        if (score > highscore){

            highscoreLabel.setText("Nieuwe highscore! : " + score);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("High_Score", score);
            editor.commit();

        }else{

            highscoreLabel.setText("Highscore : " + "" + highscore);

        }

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

}
