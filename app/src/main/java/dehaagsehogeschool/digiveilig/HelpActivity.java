package dehaagsehogeschool.digiveilig;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import dehaagsehogeschool.digiveilig.managers.DataManager;
import dehaagsehogeschool.digiveilig.managers.ResultManager;

/**
 * Created by Tony on 2/18/2018.
 */

public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);
    }

    public void resetGame(View view) {
        String[] resetOptions = new String[3];
        resetOptions[0] = "Sterren";
        resetOptions[1] = "Cijfers";
        resetOptions[2] = "Introductie";

        ArrayList mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Spel resetten")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(resetOptions, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Nee", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (mSelectedItems.contains(0)) {
                            DataManager dataManager = new DataManager(getApplicationContext());
                            dataManager.resetLevels();
                        }

                        if (mSelectedItems.contains(1)) {
                            ResultManager resultManager = new ResultManager(GameSettings.LOCATION_SHARED_PREFERENCES, getApplicationContext());
                            resultManager.resetResults();
                        }

                        if (mSelectedItems.contains(2)) {
                            SharedPreferences gameData = getSharedPreferences(GameSettings.LOCATION_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor _sharedPreferencesEditor = gameData.edit();
                            _sharedPreferencesEditor.putInt("instruction_screen", 0);
                            _sharedPreferencesEditor.commit();
                        }
                    }
                });

        Dialog dialog = builder.create();
        dialog.show();
    }
}
