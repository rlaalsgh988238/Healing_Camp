package org.techtown.healing_camp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerList;

    ImageButton onClickMakePlanner;
    Button onClickTopScroll;
    LinearLayout plannerContainerLayout;
    ListView listView;
    Integer arrayIndex = 0;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
        onClickTopScroll = findViewById(R.id.onClickTopScroll);
        plannerContainerLayout = findViewById(R.id.layoutPlannerContainer);
        listView = findViewById(R.id.listView);
        check = findViewById(R.id.check);

        plannerList = new ArrayList<PlannerInformation>();
        // 어댑터 객체 생성
        ArrayAdapter<PlannerInformation> adapter = new ArrayAdapter<PlannerInformation>(this, R.layout.planner_container_layout,plannerList);
        //final HealingAdapter healingAdapter = new HealingAdapter(this,plannerList);
        //애니매이션 생성
        Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_animation);
        //리스트 객체 생성

        listView.setAdapter(adapter);
        plannerList.add(new PlannerInformation(arrayIndex.toString()));arrayIndex++;
        plannerList.add(new PlannerInformation(arrayIndex.toString()));arrayIndex++;
        plannerList.add(new PlannerInformation(arrayIndex.toString()));

        //리스트에 잘 들어갔는지 확인하는 버튼 코드. 버튼 누르면 생성된 리스트의 내부정보 보여주는 버튼
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    check.setText(plannerList.get(arrayIndex-1).text);
            }

        });

    }


}