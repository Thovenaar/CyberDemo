package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by Tony on 3/6/2018.
 */

public class QuizSpelAnnouncementActivity extends AppCompatActivity {

    public final static String TAG = QuizSpelAnnouncementActivity.class.getSimpleName();

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.quiz_spel_announcement_activity);
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

        public void startQuizSpel(View view){

            Intent intent = new Intent(this, QuizSpelActivity.class);
            startActivity(intent);
            finish();
        }
    }
