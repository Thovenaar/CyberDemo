package dehaagsehogeschool.digiveilig;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 29-Mar-18.
 */

public class LevelsViewModel extends ViewModel {
    private LiveData<List<Level>> levelLiveData = new MutableLiveData<>();

    public LevelsViewModel() {
        levelLiveData = AppDatabase.getInstance(App.get().getApplicationContext()).levelDao().getAllLive();
    }

    public LiveData<List<Level>> getLevelList() {
        return levelLiveData;
    }
}