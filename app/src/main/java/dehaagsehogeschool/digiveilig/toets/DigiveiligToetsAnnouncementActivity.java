package dehaagsehogeschool.digiveilig.toets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import dehaagsehogeschool.digiveilig.BaseActivity;
import dehaagsehogeschool.digiveilig.R;

/**
 * Created by Tony on 3/6/2018.
 */

public class DigiveiligToetsAnnouncementActivity extends BaseActivity {

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.digiveilig_toets_announcement_activity);
        }

        public void startQuizSpel(View view){
            Intent intent = new Intent(this, DigiveiligToetsActivity.class);
            startActivity(intent);
            finish();
        }
    }
