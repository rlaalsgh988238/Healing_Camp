package org.techtown.healing_camp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetailViewActivity extends AppCompatActivity {
    Button addCampingPlaceToMemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view_layout);

        addCampingPlaceToMemo = findViewById(R.id.addCampingPlaceToMemo);

        Intent intentToInsidePlanner = new Intent(DetailViewActivity.this,InsidePlannerActivity.class);

        addCampingPlaceToMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1);
                finish();
            }
        });
    }
}
