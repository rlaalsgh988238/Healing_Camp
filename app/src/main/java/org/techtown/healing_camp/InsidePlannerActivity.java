package org.techtown.healing_camp;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InsidePlannerActivity  extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerInformation;

    Button onClickSearch,onClickEditPlanner,onClickBackLayer;
    EditText writeToSearchEdit;
    TextView test1;
    LinearLayout editBox;
    private DeleteInterface delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_planner_layout);

        onClickEditPlanner = findViewById(R.id.onClickEditPlanner);
        onClickSearch = findViewById(R.id.onClickSearch);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);
        writeToSearchEdit = findViewById(R.id.writeToSearchEdit);
        editBox = findViewById(R.id.editBox);

        test1 = findViewById(R.id.test1);
        Intent intent = getIntent();

        int position = intent.getExtras().getInt("index");

        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Edit버튼 애니메이션
        View view = findViewById(R.id.backGround);
        Animation onePointPopUP = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop_up_animation_one_point);
        Animation onePointPopUpExit = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pop_up_animation_one_point_exit);
        Animation popUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_up_animation);
        onClickEditPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editBox.getVisibility()==view.INVISIBLE) {
                    editBox.setVisibility(View.VISIBLE);
                    editBox.startAnimation(onePointPopUP);
                    onClickEditPlanner.setVisibility(View.INVISIBLE);
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(editBox.getVisibility()==view.VISIBLE) {
                    editBox.startAnimation(onePointPopUpExit);
                    editBox.setVisibility(View.INVISIBLE);
                    onClickEditPlanner.setVisibility(View.VISIBLE);
                    onClickEditPlanner.startAnimation(popUp);
                }
                return false;
            }
        });


        String index = String.valueOf(position);//@@@@@@@@@@
        test1.setText("\n              "+index);//@@@@@@@@@@@@@@@

        RenameDialog renameDialog = new RenameDialog(this);
        findViewById(R.id.editPlanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renameDialog.showDialog();
                editBox.setVisibility(View.INVISIBLE);
                onClickEditPlanner.setVisibility(View.VISIBLE);
                onClickEditPlanner.startAnimation(popUp);
            }
        });
    }


}
