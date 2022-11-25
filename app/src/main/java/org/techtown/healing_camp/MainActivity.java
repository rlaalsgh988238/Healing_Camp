package org.techtown.healing_camp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton buttonOnClickMakePlanner;
    Button buttonOnClickWritePlaner;
    LinearLayout layoutWritePlaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOnClickMakePlanner = (ImageButton) findViewById(R.id.buttonOnClickMakePlanner);
        buttonOnClickWritePlaner = (Button) findViewById(R.id.buttonOnClickWritePlaner);
        layoutWritePlaner = (LinearLayout)findViewById(R.id.layoutWritePlaner);

        buttonOnClickMakePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              layoutWritePlaner.addView(buttonOnClickWritePlaner);
            }
        });
    }
}