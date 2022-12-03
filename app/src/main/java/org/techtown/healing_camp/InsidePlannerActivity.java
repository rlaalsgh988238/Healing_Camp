package org.techtown.healing_camp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    EditText writeToSearch;
    TextView test1;
    LinearLayout editBox;
    //타이틀 수정 다이얼로그
    Button editCancel,editAccept;
    EditText editReName;
    //삭제 다이얼로그
    Button deleteCancel,deleteAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_planner_layout);

        onClickEditPlanner = findViewById(R.id.onClickEditPlanner);
        onClickSearch = findViewById(R.id.onClickSearch);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);
        writeToSearch = findViewById(R.id.writeToSearch);
        editBox = findViewById(R.id.editBox);

        test1 = findViewById(R.id.test1);
        Intent intent = getIntent();

        Intent intentToMain = new Intent(getBaseContext(),MainActivity.class);


        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 버튼 애니메이션
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

        //메모장 메모 수정
        findViewById(R.id.onClickTitleEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBox.setVisibility(View.INVISIBLE);
                onClickEditPlanner.setVisibility(View.VISIBLE);
                onClickEditPlanner.startAnimation(popUp);

                View editDialogView = getLayoutInflater().inflate(R.layout.planner_rename_dialog,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(InsidePlannerActivity.this);
                AlertDialog dialog = builder.create();
                dialog.setView(editDialogView);
                editCancel = editDialogView.findViewById(R.id.reNameCancel);
                editAccept = editDialogView.findViewById(R.id.reNameAccept);
                editReName = editDialogView.findViewById(R.id.editReName);
                dialog.show();

                editCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                editAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = editReName.getText().toString();
                        intentToMain.putExtra("title",title);
                        setResult(1);
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });

        //메모장 삭제
        findViewById(R.id.onClickDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBox.setVisibility(View.INVISIBLE);
                onClickEditPlanner.setVisibility(View.VISIBLE);
                onClickEditPlanner.startAnimation(popUp);

                View deleteDialogView = getLayoutInflater().inflate(R.layout.planner_delete_dialog,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(InsidePlannerActivity.this);
                AlertDialog dialog = builder.create();
                dialog.setView(deleteDialogView);
                deleteCancel = deleteDialogView.findViewById(R.id.deleteCancel);
                deleteAccept = deleteDialogView.findViewById(R.id.deleteAccept);
                dialog.show();

                deleteCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                deleteAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(2);
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }
}
