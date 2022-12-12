package org.techtown.healing_camp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<PlannerInformation> plannerList;
    String title;
    ImageButton onClickMakePlanner;
    Button onClickTopScroll;
    ListView listView;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        ActivityResultLauncher<Intent> startActivityResult;

        onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
        onClickTopScroll = findViewById(R.id.onClickTopScroll);
        listView = findViewById(R.id.listView);
        plannerList = new ArrayList<PlannerInformation>();

        HealingAdapter healingAdapter = new HealingAdapter(this, plannerList);
        listView.setAdapter(healingAdapter);

        //조건부 코드(1:타이틀 수정, 2: 삭제, 3: 캠핑장 이름 및 저장)
        startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == 1){
                    title = PlannerObject.getTitle();
                    plannerList.set(index,new PlannerInformation(title,null));
                    PlannerObject.setTitle(null);
                    healingAdapter.notifyDataSetChanged();
                }
                if(result.getResultCode() == 2){
                    plannerList.remove(index);
                    healingAdapter.notifyDataSetChanged();
                }
                if(result.getResultCode() == 3){
                    title = plannerList.get(index).title;
                    String[] context = PlannerObject.getResult();
                    plannerList.set(index, new PlannerInformation(title,context[0]));
                    objectReset();
                    healingAdapter.notifyDataSetChanged();
                }
            }
        });
        //메모장 추가
        onClickMakePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannerList.add(0,new PlannerInformation("작성해주세요",null));
                healingAdapter.notifyDataSetChanged();;
            }
        });
        //메모장 진입
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, InsidePlannerActivity.class);
                index = position;
                startActivityResult.launch(intent);
            }
        });
        //스크롤 감지
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int lastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lastFirstVisibleItem < firstVisibleItem) {
                    onClickTopScroll.setVisibility(View.VISIBLE);//스크롤 다운
                }
                if (firstVisibleItem == 0) {
                    onClickTopScroll.setVisibility(View.INVISIBLE);//스크롤 업
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        //스크롤 버튼 가장 위로 올라감
        onClickTopScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.smoothScrollToPosition(0);
            }
        });
    }
    void objectReset(){
        PlannerObject.setResult(null);
        PlannerObject.setMemo(null);
        PlannerObject.setTitle(null);
    }
}
