package org.techtown.healing_camp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InsidePlannerActivity  extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerInformation;

    Button onClickSearch,onClickEditPlanner,onClickBackLayer;
    EditText writeToSearchEdit;
    TextView test1;
    LinearLayout editSpinner;
    private DeleteInterface delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_planner_layout);

        onClickEditPlanner = findViewById(R.id.onClickEditPlanner);
        onClickSearch = findViewById(R.id.onClickSearch);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);
        writeToSearchEdit = findViewById(R.id.writeToSearchEdit);
        editSpinner = findViewById(R.id.editSpinner);

        test1 = findViewById(R.id.test1);
        Intent intent = getIntent();

        int position = intent.getExtras().getInt("index");

        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delegate.delete();
                onBackPressed();
            }
        });

        // Edit버튼 애니메이션
        View view = findViewById(R.id.backGround);
        Animation onePointPopUP = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop_up_animation_one_point);
        Animation onePointPopUpExit = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop_up_animation_one_point_exit);
        onClickEditPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSpinner.getVisibility()==view.INVISIBLE) {
                    editSpinner.setVisibility(View.VISIBLE);
                    editSpinner.startAnimation(onePointPopUP);
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(editSpinner.getVisibility()==view.VISIBLE) {
                    editSpinner.startAnimation(onePointPopUpExit);
                    editSpinner.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });


        String index = String.valueOf(position);//@@@@@@@@@@
        test1.setText("\n              "+index);//@@@@@@@@@@@@@@@
    }
}
