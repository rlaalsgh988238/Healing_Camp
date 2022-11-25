package org.techtown.healing_camp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerList;

    ImageButton buttonOnClickMakePlanner;
    LinearLayout plannerContainerLayout;
    Integer arrayIndex = 0;

    Button check;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOnClickMakePlanner = (ImageButton) findViewById(R.id.buttonOnClickMakePlanner);
        plannerContainerLayout = (LinearLayout)findViewById(R.id.layoutPlannerContainer);

        check = (Button) findViewById(R.id.check);

        //새로운 플래너 생성
        plannerList = new ArrayList<>();


        //애니매이션 생성
        Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_animation);

        buttonOnClickMakePlanner.setOnClickListener(new View.OnClickListener() { //버튼 누르면 새로운 플래너 생성
            @Override                                                            //생성된 플레너는 리스트에 스택
            public void onClick(View view) {
                PlannerInformation plannerInformation = new PlannerInformation(arrayIndex.toString());
                plannerList.add(plannerInformation);
                InflatorPlanner inflatorPlanner = new InflatorPlanner(getApplicationContext(), plannerList.get(arrayIndex));
                plannerContainerLayout.addView(inflatorPlanner);
                plannerContainerLayout.startAnimation(popUpAnimation);
                arrayIndex+=1;
            }
        });

        //리스트에 잘 들어갔는지 확인하는 버튼 코드
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    check.setText(plannerList.get(arrayIndex-1).text);
            }
        });


    }
}