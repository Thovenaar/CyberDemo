package dehaagsehogeschool.cyberdemo.games;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 19-Mar-18.
 */

public class ResultProvider {

    private SharedPreferences _sharedPreferences;
    private SharedPreferences.Editor _sharedPreferencesEditor;

    private static final String SOURCE_KEY = "results";
    private static final String NO_RESULT = "0";
    private static final int MAX_RESULTS = 10;

    public ResultProvider(String source, Context context) {
        _sharedPreferences = context.getSharedPreferences(source, Context.MODE_PRIVATE);
        _sharedPreferencesEditor = _sharedPreferences.edit();
    }

    public ArrayList<Double> getResults(boolean orderByDesc) {
        ArrayList<Double> value = new ArrayList<Double>();
        Set<String> results = _sharedPreferences.getStringSet(SOURCE_KEY, null);

        if (results == null) return value;

        for (String result : results) {
            try {
                value.add(Double.parseDouble(result));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (orderByDesc) {
            Collections.sort(value);
            Collections.reverse(value);
        }

        return value;
    }

    public void setResults(List<Double> results) {
        Set<String> value = new HashSet<String>();

        if (results.size() > MAX_RESULTS) {
            results = results.subList(0, MAX_RESULTS);
        }

        for (Double result : results) {
            value.add(result.toString());
        }

        _sharedPreferencesEditor.putStringSet(SOURCE_KEY, value);
        _sharedPreferencesEditor.commit();
    }

    public void addResult(Double result) {
        List<Double> results = this.getResults(false);
        results.add(result);

        this.setResults(results);
    }

    public String getResultText(List<Double> results, int index) {
        String value = NO_RESULT;

        try {
            DecimalFormat decimalFormat = new DecimalFormat("##.0");
            value = decimalFormat.format(results.get(index));
        } catch (Exception ex) {

        }

        return value;
    }

}
