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

    public Level(int id, boolean unlocked, String game) {
        this.id = id;
        this.unlocked = unlocked;
        this.game = game.toString();
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "stars")
    public int stars;

    @ColumnInfo(name = "unlocked")
    public boolean unlocked;

    @ColumnInfo(name = "finish_time")
    public int finishTime;

    @ColumnInfo(name = "game_type")
    public String game;

    /*
    public Level(int id, Game game) {
        this.id = id;
        this.game = game.toString();
    }
    */
}
