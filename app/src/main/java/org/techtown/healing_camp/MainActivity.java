package org.techtown.healing_camp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
        onClickWritePlaner = findViewById(R.id.onClickWritePlaner);
        onClickTopScroll = findViewById(R.id.onClickTopScroll);
        listView = findViewById(R.id.listView);

        plannerList = new ArrayList<PlannerInformation>();

        final HealingAdapter healingAdapter = new HealingAdapter(this,plannerList);
        Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_animation);

        listView.setAdapter(healingAdapter);

        onClickMakePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannerList.add(0,new PlannerInformation(arrayIndex.toString()));
                healingAdapter.notifyDataSetChanged();
                arrayIndex++;
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int lastFirstVisibleItem;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(lastFirstVisibleItem<firstVisibleItem){
                    onClickTopScroll.setVisibility(View.VISIBLE);//스크롤 다운
                }
                if(firstVisibleItem==0){
                    onClickTopScroll.setVisibility(View.INVISIBLE);//스크롤 업
                }
                lastFirstVisibleItem=firstVisibleItem;
            }
        });//스크롤 감지 함수

        onClickTopScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.smoothScrollToPosition(0);
            }
        });//버튼클릭 최상단 이동 함수

        }
    }
