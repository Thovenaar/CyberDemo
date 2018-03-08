package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    public final static String TAG = HomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Log.i(TAG, "I am created!");
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

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
    }

    public void startQuiz(View view) {

        Intent intent = new Intent(this, QuizSpelAnnouncementActivity.class);
        startActivity(intent);
    }

    public void startDigiveilig(View view) {

        Intent intent = new Intent(this, DigiVeiligSpelActivity.class);
        startActivity(intent);
    }

    public void startHighscore(View view){

        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    public void startHelp(View view) {

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);

    }
}
