package dehaagsehogeschool.digiveilig.managers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dehaagsehogeschool.digiveilig.AppDatabase;
import dehaagsehogeschool.digiveilig.enums.Game;
import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 19-Mar-18.
 */

public class DataManager {

    private Context _context;

    public DataManager(Context context) {
        _context = context;
    }

    public void initializeData() {
        createLevels();
    }

    private void createLevels() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (AppDatabase.getInstance(_context).levelDao().getAll().size() == 0) {
                    for (Level level : getLevels()) {
                        AppDatabase.getInstance(_context).levelDao().insertAll(level);
                    }
                }
                return null;
            }
        }.execute();
    }

    private List<Level> getLevels() {
        List<Level> levels = new ArrayList<Level>();

        levels.add(new Level(1, true, Game.MEMORY.toString(), 0));
        levels.add(new Level(2, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(3, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(4, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(5, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(6, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(7, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(8, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(9, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(10, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(11, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(12, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(13, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(14, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(15, false, Game.MEMORY.toString(), 0));
        levels.add(new Level(16, false, Game.MEMORY.toString(), 0));

        return levels;
    }

}
