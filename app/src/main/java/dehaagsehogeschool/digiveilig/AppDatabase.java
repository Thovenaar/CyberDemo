package dehaagsehogeschool.digiveilig;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import dehaagsehogeschool.digiveilig.daos.LevelDao;
import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 19-Mar-18.
 */

@Database(entities = {Level.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LevelDao levelDao();

    private static volatile AppDatabase Instance = null;

    public static AppDatabase getInstance(Context context) {
        AppDatabase _database = Instance;
        if (_database == null) {
            synchronized (AppDatabase.class) {
                _database = Instance;
                if (_database == null) {
                    Instance = _database = Room.databaseBuilder(context, AppDatabase.class, GameSettings.DATABASE_NAME)
                                            .fallbackToDestructiveMigration()
                                            .build();
                }
            }
        }
        return _database;
    }
}