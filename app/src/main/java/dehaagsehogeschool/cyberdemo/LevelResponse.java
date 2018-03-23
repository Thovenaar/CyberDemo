package dehaagsehogeschool.cyberdemo;

import java.util.List;

import dehaagsehogeschool.cyberdemo.models.Level;

/**
 * Created by Thomas on 22-Mar-18.
 */

public interface LevelResponse {
    void processFinish(List<Level> output);
}