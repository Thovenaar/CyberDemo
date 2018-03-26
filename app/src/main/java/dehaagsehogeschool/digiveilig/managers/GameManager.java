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


    public GameManager(GameManagerSettings settings) {
        this.settings = settings;
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
                                settings.gameTimer.setText(Integer.toString(settings.gameTime));
                                if (settings.gameTime > 0) {
                                    if (!gameEnded) {
                                        settings.gameTime -= 1;
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

        if (settings.gameTime < settings.secondsForZeroStars) stars = 0;
        else if (settings.gameTime < settings.secondsForOneStar) stars = 1;
        else if (settings.gameTime < settings.secondsForTwoStars) stars = 2;
        else if (settings.gameTime < settings.secondsForThreeStars) stars = 3;

        return stars;
    }

    public void setStars() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase db = Room.databaseBuilder(settings.context,
                        AppDatabase.class, GameSettings.DATABASE_NAME).build();

                db.levelDao().updateStars(1, calculateStars());
                return null;
            }
        }.execute();
    }

    public void stopTimer() {

        _timer.cancel();
    }
}
