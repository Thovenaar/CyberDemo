package dehaagsehogeschool.digiveilig;

import android.util.SparseArray;

/**
 * Created by Thomas on 19-Mar-18.
 */

public class GameSettings {

    private static SparseArray<String> settings;

    // General
    public final static String LOCATION_SHARED_PREFERENCES = "Game_Data";
    public final static String LEVEL_ID = "LevelId";
    public final static String LEVEL_GAME = "LevelGame";

    // Database
    public final static String DATABASE_NAME = "digiveilig";

    // Result calculation
    public final static String QUESTIONS_AMOUNT = "QUESTIONS";
    public final static String RESULT_SCORE = "SCORE";
    public final static String RESULT_STARS = "STARS";
    public final static String FINISH_TIME = "FINISH_TIME";
    public final static String SECONDS_FOR_ONE_STAR = "ONE_STAR";
    public final static String SECONDS_FOR_TWO_STAR = "TWO_STARS";
    public final static String SECONDS_FOR_THREE_STAR = "THREE_STARS";

    // Digiveilig toets
    public final static String QUESTIONS_LOCATION = "digiveilig_toets_questions.xml";

    //Scenarios
    private final static String SCENARIOS_LEVEL_2 = "scenarios_level_2.xml";
    private final static String SCENARIOS_LEVEL_6 = "scenarios_level_6.xml";
    private final static String SCENARIOS_LEVEL_9 = "scenarios_level_9.xml";
    private final static String SCENARIOS_LEVEL_11 = "scenarios_level_11.xml";
    private final static String SCENARIOS_LEVEL_14 = "scenarios_level_14.xml";

    // Quiz game
    private final static String QUESTIONS_LEVEL_4 = "questions_level_4.xml";
    private final static String QUESTIONS_LEVEL_8 = "questions_level_8.xml";
    private final static String QUESTIONS_LEVEL_10 = "questions_level_10.xml";
    private final static String QUESTIONS_LEVEL_12 = "questions_level_12.xml";
    private final static String QUESTIONS_LEVEL_16 = "questions_level_16.xml";

    public static String getCurrentGameSetting(int level) {
        settings = new SparseArray<String>();
        settings.put(2, SCENARIOS_LEVEL_2);
        settings.put(4, QUESTIONS_LEVEL_4);
        settings.put(6, SCENARIOS_LEVEL_6);
        settings.put(8, QUESTIONS_LEVEL_8);
        settings.put(9, SCENARIOS_LEVEL_9);
        settings.put(10, QUESTIONS_LEVEL_10);
        settings.put(11, SCENARIOS_LEVEL_11);
        settings.put(12, QUESTIONS_LEVEL_12);
        settings.put(14, SCENARIOS_LEVEL_14);
        settings.put(16, QUESTIONS_LEVEL_16);

        return settings.get(level);
    }
}
