package dehaagsehogeschool.digiveilig.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thomas on 19-Mar-18.
 */

public class ResultManager {

    private SharedPreferences _sharedPreferences;
    private SharedPreferences.Editor _sharedPreferencesEditor;

    private static final String SOURCE_KEY = "all_results";
    private static final String NO_RESULT = "-";
    private static final int MAX_RESULTS = 10;
    private static final boolean ORDER_BY_DESC = true;

    public ResultManager(String source, Context context) {
        _sharedPreferences = context.getSharedPreferences(source, Context.MODE_PRIVATE);
        _sharedPreferencesEditor = _sharedPreferences.edit();
    }

    public ArrayList<Double> getResults() {
        ArrayList<Double> value = new ArrayList<Double>();
        String source = _sharedPreferences.getString(SOURCE_KEY, null);

        if (source == null || source == "") return value;

        List<String> results =  Arrays.asList(_sharedPreferences.getString(SOURCE_KEY, null).split(","));

        if (results == null) return value;

        for (String result : results) {
            try {
                value.add(Double.parseDouble(result));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (ORDER_BY_DESC) {
            Collections.sort(value);
            Collections.reverse(value);
        }

        return value;
    }

    private void setResults(List<Double> results) {
        String value;

        if (results.size() > MAX_RESULTS) {
            results = results.subList(0, MAX_RESULTS);
        }

        if (ORDER_BY_DESC) {
            Collections.sort(results);
            Collections.reverse(results);
        }

        value = TextUtils.join(",", results);

        _sharedPreferencesEditor.putString(SOURCE_KEY, value);
        _sharedPreferencesEditor.commit();
    }

    public void addResult(Double result) {
        List<Double> results = this.getResults();
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

    public String getHighestResult() {
        return getResultText(getResults(), 0);
    }

}
