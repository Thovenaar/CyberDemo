package dehaagsehogeschool.digiveilig.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Thomas on 19-Mar-18.
 */

@Entity
public class Level {

    public Level() {}

    public Level(int id, boolean unlocked, String game, Integer stars) {
        this.id = id;
        this.unlocked = unlocked;
        this.game = game.toString();
        this.stars = stars;
    }

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "stars")
    public Integer stars;

    @ColumnInfo(name = "unlocked")
    public boolean unlocked;

    @ColumnInfo(name = "finish_time")
    public Integer finishTime;

    @ColumnInfo(name = "game_type")
    public String game;

    /*
    public Level(int id, Game game) {
        this.id = id;
        this.game = game.toString();
    }
    */
}
