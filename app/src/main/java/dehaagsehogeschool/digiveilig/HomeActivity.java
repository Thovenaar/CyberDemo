package dehaagsehogeschool.digiveilig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dehaagsehogeschool.digiveilig.interfaces.ActivityInterface;
import dehaagsehogeschool.digiveilig.managers.DataManager;
import dehaagsehogeschool.digiveilig.managers.ResultManager;
import dehaagsehogeschool.digiveilig.models.Level;
import dehaagsehogeschool.digiveilig.spel.DigiveiligSpelActivity;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsResultsActivity;
import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsAnnouncementActivity;

public class HomeActivity extends AppCompatActivity implements ActivityInterface {

    public final static String TAG = HomeActivity.class.getSimpleName();

    TextView starScore, toetsResultaat;

    private LevelAdapter adapter;
    public RecyclerView recyclerViewLevels;
    private RecyclerView.LayoutManager mLayoutManager;


    private class FetchLevelListTask extends AsyncTask<Void, List<Level>, List<Level>> {

        @Override
        protected List<Level> doInBackground(Void... params) {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            List<Level> levels = db.levelDao().getAll();

            return levels;
        }

        @Override
        protected void onPostExecute(List<Level> levels) {
            super.onPostExecute(levels);
            adapter.addLevels(levels);
            starScore.setText(String.valueOf(adapter.getItemCount()) + " Sterren");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initializeData();
        initializeObjects();
        setStars();

        Log.i(TAG, "I am created!");

    }

    private void setStars() {

        recyclerViewLevels = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewLevels.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new LevelAdapter();
        recyclerViewLevels.setLayoutManager(mLayoutManager);
        recyclerViewLevels.setAdapter(adapter);

        FetchLevelListTask fetchLevelListTask = new FetchLevelListTask();
        fetchLevelListTask.execute();
        starScore.setText(String.valueOf(adapter.getItemCount()) + " Sterren");
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

        toetsResultaat.setText("Cijfer: " + resultManager.getHighestResult());
    }

    @Override
    public void initializeObjects() {

        starScore = (TextView) findViewById(R.id.home_star_score);
        toetsResultaat = (TextView) findViewById(R.id.home_toets_result_score);

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
