package dehaagsehogeschool.digiveilig.managers;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import java.util.Timer;
import java.util.TimerTask;

import dehaagsehogeschool.digiveilig.AppDatabase;
import dehaagsehogeschool.digiveilig.GameSettings;
import dehaagsehogeschool.digiveilig.models.GameManagerSettings;

/**
 * Created by Tony on 3/26/2018.
 */

public class GameManager {
    private GameManagerSettings settings;
    public boolean gameEnded = false;
    private Timer _timer = new Timer();
    private int _timeLeft;


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
                                        setStars();
                                    }
                                } else {
                                    gameEnded = true;
                                    cancel();
                                    stopTimer();
                                }
                            }
                        });
            }

        };

        _timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public int calculateStars() {
        int stars = 0;
        int finishTime = getFinishTime();

        if (finishTime <= settings.secondsForZeroStars) stars = 0;
        if (finishTime <= settings.secondsForOneStar) stars = 1;
        if (finishTime <= settings.secondsForTwoStars) stars = 2;
        if (finishTime <= settings.secondsForThreeStars) stars = 3;

        return stars;
    }

    public int getFinishTime() {
        return settings.gameTime - _timeLeft;
    }

    public void setStars() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase db = Room.databaseBuilder(settings.context,
                        AppDatabase.class, GameSettings.DATABASE_NAME).build();

                db.levelDao().updateStars(settings.levelId, calculateStars());
                db.levelDao().updateFinishTime(settings.levelId, getFinishTime());
                return null;
            }
        }.execute();
    }

    public void stopTimer() {

        _timer.cancel();
    }
}
