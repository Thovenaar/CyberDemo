package dehaagsehogeschool.digiveilig.managers;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import dehaagsehogeschool.digiveilig.App;
import dehaagsehogeschool.digiveilig.AppDatabase;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.models.GameManagerSettings;
import dehaagsehogeschool.digiveilig.spel.DigiveiligSpelResultActivity;

/**
 * Created by Tony on 3/26/2018.
 */

public class GameManager {
    private GameManagerSettings settings;
    public boolean gameEnded = false;
    private Timer _timer = new Timer();
    private int _timeLeft;
    public int score;

    public GameManager(GameManagerSettings settings) {
        this.settings = settings;
        this._timeLeft = settings.gameTime;
        startGameTimer();
    }

    public void startGameTimer() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                ((Activity) settings.context)
                        .runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                settings.gameTimer.setText(Integer.toString(_timeLeft));
                                if (_timeLeft > 0) {
                                    if (!gameEnded) {
                                        _timeLeft -= 1;
                                    } else {
                                        cancel();
                                        stopTimer();
                                        setData();
                                        showResults();
                                    }
                                } else {
                                    gameEnded = true;
                                    cancel();
                                    stopTimer();
                                    showResults();
                                }
                            }
                        });
            }

        };

        _timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private int calculateStars() {
        int finishTime = getFinishTime();

        switch(Game.valueOf(settings.game.toString())) {
           case MEMORY:
               return getStarsDefault(finishTime);

           case QUIZ:
               return getStarsQuiz(finishTime);
          }

      return 0;
    }

    private int getStarsDefault(int finishTime) {
        int stars = 0;

        if (finishTime <= settings.secondsForZeroStars) stars = 0;
        if (finishTime <= settings.secondsForOneStar) stars = 1;
        if (finishTime <= settings.secondsForTwoStars) stars = 2;
        if (finishTime <= settings.secondsForThreeStars) stars = 3;

        return stars;
    }

    private int getStarsQuiz(int finishTime) {
        int stars = getStarsDefault(finishTime);

        if (stars > score) {
            stars = score;
        }

        return stars;
    }

    private int getFinishTime() {
        return settings.gameTime - _timeLeft;
    }

    public void setData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase db = AppDatabase.getInstance(App.get().getApplicationContext());

                int stars = calculateStars();
                db.levelDao().updateStars(settings.levelId, stars);
                db.levelDao().updateFinishTime(settings.levelId, getFinishTime());

                if (stars > 0 && settings.levelId < 16) {
                    db.levelDao().unlock(settings.levelId + 1);
                }

                return null;
            }
        }.execute();
    }

    public void stopTimer() {
        _timer.cancel();
    }

    private void showResults(){
        Intent showResults = new Intent(settings.context, DigiveiligSpelResultActivity.class);
        showResults.putExtra(GameSettings.RESULT_STARS, calculateStars());
        showResults.putExtra(GameSettings.FINISH_TIME, getFinishTime());
        showResults.putExtra(GameSettings.SECONDS_FOR_ONE_STAR, settings.secondsForOneStar);
        showResults.putExtra(GameSettings.SECONDS_FOR_TWO_STAR, settings.secondsForTwoStars);
        showResults.putExtra(GameSettings.SECONDS_FOR_THREE_STAR, settings.secondsForThreeStars);
        showResults.putExtra(GameSettings.LEVEL_ID, settings.levelId);
        showResults.putExtra(GameSettings.RESULT_SCORE, score);
        showResults.putExtra(GameSettings.LEVEL_GAME, settings.game.toString());
        settings.context.startActivity(showResults);

        //Stop current game activity
        ((Activity) settings.context).finish();;
    }
}
