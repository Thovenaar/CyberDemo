package dehaagsehogeschool.digiveilig.spel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dehaagsehogeschool.digiveilig.LevelFetcher;
import dehaagsehogeschool.digiveilig.R;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.games.MemoryActivity;
import java.util.List;

import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.interfaces.LevelResponse;
import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Tony on 2/20/2018.
 */

public class DigiveiligSpelActivity extends AppCompatActivity implements LevelResponse,ActivityInterface {

    public final static String TAG = DigiveiligSpelActivity.class.getSimpleName();

    TextView starScore;

    private List<Level> _levels;

    @Override
    public void processFinish(List<Level> output) {
        _levels = output;

        starScore.setText(getStars().toString() + " sterren");
        setLevelsUnlocked();
    }

    private Integer getStars() {
        int sum = 0;

        for (Level level : _levels) {
            sum += level.stars;
        }

        return sum;
    }

    private void setLevelsUnlocked() {
        for (Level level : _levels) {
            if (level.unlocked) {
                int id = getResources().getIdentifier("level_button_" + level.id, "id", getPackageName());
                Button levelButton = findViewById(id);
                levelButton.setText(level.id.toString());

                switch (level.stars) {
                    case 0:
                        levelButton.setBackground(getDrawable(R.drawable.digiveilig_spel_0_star));
                        break;
                    case 1:
                        levelButton.setBackground(getDrawable(R.drawable.digiveilig_spel_1_star));
                        break;
                    case 2:
                        levelButton.setBackground(getDrawable(R.drawable.digiveilig_spel_2_star));
                        break;
                    case 3:
                        levelButton.setBackground(getDrawable(R.drawable.digiveilig_spel_3_star));
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new LevelFetcher(this, getApplicationContext()).execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.digiveilig_spel_activity);

        Log.i(TAG, "I am here!");

        /*
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, GameSettings.DATABASE_NAME).build();

        db.levelDao().insertAll(new Level());
        */

      initializeObjects();
    }

    @Override
    public void initializeObjects() {
        starScore = findViewById(R.id.digiveilig_spel_star_score);
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

    public void startLevel(View view) {
        Intent intent = null;

        Level selectedLevel = _levels.get(Integer.parseInt(view.getTag().toString()));
        switch (Game.valueOf(selectedLevel.game)) {
            case MEMORY:
                intent = new Intent(this, MemoryActivity.class);
                break;
            case QUIZ:
                // TODO
                break;
        }

        startActivity(intent);
    }
}
