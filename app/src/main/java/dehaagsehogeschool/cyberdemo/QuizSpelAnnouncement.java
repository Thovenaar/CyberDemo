package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tony on 3/6/2018.
 */

public class QuizSpelAnnouncement extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_spel_announcement);

    }

    public void startQuizSpel(View view){

        Intent intent = new Intent(this, QuizSpelActivity.class);
        startActivity(intent);

    }

    }
