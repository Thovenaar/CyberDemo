package dehaagsehogeschool.digiveilig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dehaagsehogeschool.digiveilig.toets.DigiveiligToetsAnnouncementActivity;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_activity);

    }

    public void startDigiveilig(View view) {
        SharedPreferences gameData = getSharedPreferences(GameSettings.LOCATION_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor _sharedPreferencesEditor = gameData.edit();
        _sharedPreferencesEditor.putInt("instruction_screen", 1);
        _sharedPreferencesEditor.commit();


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }
}


