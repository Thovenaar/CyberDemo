package dehaagsehogeschool.cyberdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Tony on 2/17/2018.
 */

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void quitGame(View view) {
        finish();
    }
}
