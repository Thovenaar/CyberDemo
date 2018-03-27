package dehaagsehogeschool.digiveilig.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 19-Mar-18.
 */

@Dao
public interface LevelDao {

    @Query("SELECT * FROM level")
    List<Level> getAll();

    @Query("SELECT * FROM level WHERE id = :id LIMIT 1")
    Level getById(int id);

    @Query("UPDATE level SET unlocked = 1 WHERE id = :id")
    void unlock(int id);

    @Query("UPDATE level SET stars = :stars WHERE id = :id AND stars < :stars")
    void updateStars(int id, int stars);

    @Query("UPDATE level SET stars = :stars WHERE id = :id")
    void updateStarsForce(int id, int stars);

    @Query("UPDATE level SET finish_time = :seconds WHERE id = :id AND finish_time > :seconds")
    void updateFinishTime(int id, int seconds);

    @Query("UPDATE level SET finish_time = :seconds WHERE id = :id")
    void updateFinishTimeForce(int id, int seconds);


    @Query("DELETE FROM level")
    void deleteAll();

    @Insert
    void insertAll(Level... levels);
}
