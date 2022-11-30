package org.techtown.healing_camp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InsidePlannerActivity  extends MainActivity {
    ArrayList<PlannerInformation> plannerInformation;

    Button onClickSearch,onClickEditPlanner,onClickBackLayer;
    EditText writeToSearchEdit;
    TextView test1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_planner_layout);

        onClickEditPlanner = findViewById(R.id.onClickEditPlanner);
        onClickSearch = findViewById(R.id.onClickSearch);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);
        writeToSearchEdit = findViewById(R.id.writeToSearchEdit);

        test1 = findViewById(R.id.test1);
        Intent intent = getIntent();

        int position = intent.getExtras().getInt("index");

        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        onClickEditPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannerList.remove(position);
                listView.setItemChecked(-1,true);
                onBackPressed();
            }
        });

        String index = String.valueOf(position);//@@@@@@@@@@
        test1.setText(index);//@@@@@@@@@@@@@@@
    }
}
