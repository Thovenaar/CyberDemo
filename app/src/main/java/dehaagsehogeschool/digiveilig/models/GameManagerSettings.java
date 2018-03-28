package dehaagsehogeschool.digiveilig.models;

import android.content.Context;
import android.widget.TextView;

import dehaagsehogeschool.digiveilig.enums.Game;

/**
 * Created by Tony on 3/26/2018.
 */

public class GameManagerSettings {

    public Context context;
    public int gameTime;
    public int secondsForZeroStars, secondsForOneStar, secondsForTwoStars, secondsForThreeStars, levelId;
    public TextView gameTimer;
    public Game game;

}
