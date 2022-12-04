package org.techtown.healing_camp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchCompleteActivity extends AppCompatActivity {
    Button onClickBackLayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_layout);
        Intent intentToInsideView = new Intent(SearchCompleteActivity.this,InsidePlannerActivity.class);

        onClickBackLayer = findViewById(R.id.onClickBackLayer);

        //테스트
        Button test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1);
                finish();
            }
        });

        //뒤로가기
        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
