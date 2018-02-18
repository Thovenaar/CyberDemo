package dehaagsehogeschool.cyberdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_idee2);
    }

    public void startQuiz(View view) {

        launchQuiz();

    }

    public void startHelp(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);

    }

    public void launchQuiz() {

        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);

    }

}
