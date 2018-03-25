package dehaagsehogeschool.digiveilig.interfaces;

import java.util.List;

import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 22-Mar-18.
 */

public interface LevelResponse {
    void processFinish(List<Level> output);
}