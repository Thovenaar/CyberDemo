package dehaagsehogeschool.digiveilig.toets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;

import dehaagsehogeschool.digiveilig.BaseActivity;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.ResultManager;

/**
 * Created by Tony on 3/7/2018.
 */

public class DigiveiligToetsResultsActivity extends BaseActivity implements ActivityInterface {

    private TextView highscore1, highscore2, highscore3, highscore4, highscore5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeObjects();

        //Ophalen huidige highscores toetsresultaat
        ResultManager resultManager = new ResultManager(GameSettings.LOCATION_SHARED_PREFERENCES, getApplicationContext());
        ArrayList<Double> results = resultManager.getResults();

        //Vullen van de highscors in de labels
        highscore1.setText("Cijfer: " + resultManager.getResultText(results, 0));
        highscore2.setText("Cijfer: " + resultManager.getResultText(results, 1));
        highscore3.setText("Cijfer: " + resultManager.getResultText(results, 2));
        highscore4.setText("Cijfer: " + resultManager.getResultText(results, 3));
        highscore5.setText("Cijfer: " + resultManager.getResultText(results, 4));
    }

    @Override
    public void initializeObjects() {
        setContentView(R.layout.digiveilig_toets_results_activity);

        highscore1 = findViewById(R.id.highscore_name);
        highscore2 = findViewById(R.id.highscore_name2);
        highscore3 = findViewById(R.id.highscore_name3);
        highscore4 = findViewById(R.id.highscore_name4);
        highscore5 = findViewById(R.id.highscore_name5);
    }
}
