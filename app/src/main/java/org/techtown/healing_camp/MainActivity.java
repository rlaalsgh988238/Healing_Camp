package org.techtown.healing_camp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerList;

    ImageButton onClickMakePlanner;
    Button onClickTopScroll,onClickWritePlaner;
    ListView listView;
    Integer arrayIndex = 0;
    Button check;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
        onClickWritePlaner = findViewById(R.id.onClickWritePlaner);
        onClickTopScroll = findViewById(R.id.onClickTopScroll);
        listView = findViewById(R.id.listView);
        check = findViewById(R.id.check);

        plannerList = new ArrayList<PlannerInformation>();

        final HealingAdapter healingAdapter = new HealingAdapter(this,plannerList);
        //애니매이션 생성
        Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_animation);

        listView.setAdapter(healingAdapter);

        onClickMakePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannerList.add(new PlannerInformation(arrayIndex.toString()));
                healingAdapter.notifyDataSetChanged();
                arrayIndex++;
            }
        });
    }
}