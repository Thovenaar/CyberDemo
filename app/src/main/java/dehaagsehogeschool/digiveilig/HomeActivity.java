package dehaagsehogeschool.digiveilig;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.DataManager;
import dehaagsehogeschool.digiveilig.managers.ResultManager;
import dehaagsehogeschool.digiveilig.models.Level;
import dehaagsehogeschool.digiveilig.spel.DigiveiligSpelActivity;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsResultsActivity;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsAnnouncementActivity;

public class HomeActivity extends BaseActivity implements ActivityInterface {

    private TextView starScore, toetsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initializeData();
        initializeObjects();
        initializeObserver();
    }

    private void initializeObserver() {
        LevelsViewModel usersViewModel = ViewModelProviders.of(this).get(LevelsViewModel.class);
        usersViewModel.getLevelList().observe(this, this::showLevels);
    }

    private void showLevels(List<Level> levels) {
        starScore.setText(String.valueOf(getStars(levels) + " Sterren"));
    }

    private void initializeData() {
        DataManager dataManager = new DataManager(getApplicationContext());
        dataManager.initializeData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences gameData = getSharedPreferences(GameSettings.LOCATION_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        ResultManager resultManager = new ResultManager(GameSettings.LOCATION_SHARED_PREFERENCES, getApplicationContext());

        toetsResult.setText("Cijfer: " + resultManager.getHighestResult());
    }

    private int getStars(List<Level> levels) {
        int i = 0;

        for (Level level : levels) {
            i += level.stars;
        }

        return i;
    }

    @Override
    public void initializeObjects() {
        starScore = findViewById(R.id.home_star_score);
        toetsResult = findViewById(R.id.home_toets_result_score);
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
        Intent intent = new Intent(this, DigiveiligToetsResultsActivity.class);
        startActivity(intent);
    }

    public void startHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);

    }
}
