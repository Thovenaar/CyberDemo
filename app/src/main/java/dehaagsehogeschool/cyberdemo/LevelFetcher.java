package dehaagsehogeschool.cyberdemo;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import dehaagsehogeschool.cyberdemo.models.Level;

/**
 * Created by Thomas on 22-Mar-18.
 */

public class LevelFetcher extends AsyncTask<Void, List<Level>, List<Level>> {
    private LevelResponse _caller;
    private Context _context;

    public LevelFetcher(LevelResponse caller, Context context) {
        _caller = caller;
        _context = context;
    }

    @Override
    protected List<Level> doInBackground(Void... params) {
        return AppDatabase.getInstance(_context).levelDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Level> result) {
        _caller.processFinish(result);
    }
}