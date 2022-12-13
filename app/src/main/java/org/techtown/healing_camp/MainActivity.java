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
    String[] context;
    ImageButton onClickMakePlanner;
    Button onClickTopScroll;
    ListView listView;
    int index = 0 , clickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalDB memoDB = new LocalDB(MainActivity.this,1);
        SearchDB searchDB = new SearchDB(this,1);
        Intent intent = getIntent();

        ActivityResultLauncher<Intent> startActivityResult;

        onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
        onClickTopScroll = findViewById(R.id.onClickTopScroll);
        listView = findViewById(R.id.listView);
        plannerList = new ArrayList<PlannerInformation>();

        HealingAdapter healingAdapter = new HealingAdapter(this, plannerList);
        listView.setAdapter(healingAdapter);
        //기존의 메모장 확인
        index = memoDB.count();
        if(index !=0){
            for (int i=0;i<memoDB.count();i++){
                String title = memoDB.getTitle(synchro(memoDB)[i]);
                context = searchDB.getResult(synchro(memoDB)[i]);
                if(title.equals("null")) title = "메모장 이름을 작성해 주세요";
                if(searchDB.getFlag(synchro(memoDB)[i])==0) context[0] = "캠핑장소를 선택해 주세요";
                plannerList.add(new PlannerInformation(title,context[0]));
                healingAdapter.notifyDataSetChanged();
            }
        }

        //조건부 코드(1:타이틀 수정, 2: 삭제, 3: 캠핑장 이름 및 저장)
        startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == 1){
                    title = PlannerObject.getTitle();
                    context = searchDB.getResult(synchro(memoDB)[clickPosition]);
                    if(searchDB.getFlag(synchro(memoDB)[clickPosition])==0) context[0] = "캠핑장소를 선택해 주세요";
                    plannerList.set(clickPosition, new PlannerInformation(title,context[0]));
                    healingAdapter.notifyDataSetChanged();
                }
                if(result.getResultCode() == 2){
                    deleteDB(memoDB, synchro(memoDB)[clickPosition]);
                    plannerList.remove(clickPosition);
                    healingAdapter.notifyDataSetChanged();
                    index--;
                }
                if(result.getResultCode() == 3){
                    title = memoDB.getTitle(synchro(memoDB)[clickPosition]);
                    context = searchDB.getResult(synchro(memoDB)[clickPosition]);
                    if(title.equals("null")) title = "메모장 이름을 작성해 주세요";
                    if(searchDB.getFlag(synchro(memoDB)[clickPosition])==0) context[0]="캠핑장소를 선택해 주세요";
                    plannerList.set(clickPosition, new PlannerInformation(title,context[0]));
                    //다른 메모장까지 영향을 줄 수 있기 때문에 초기화
                    PlannerObject.setResult(null);
                    healingAdapter.notifyDataSetChanged();
                }
            }
        });

        String[] nullArray = new String[10];
        for(int i = 0;i<10;i++){
            nullArray[i]=null;
        }

        //메모장 추가
        onClickMakePlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plannerList.add(0,new PlannerInformation("메모장 이름을 적성해 주세요","캠핑장소를 선택해 주세요"));
                memoDB.insert(null, index,null);
                searchDB.insert(nullArray, index,0);
                healingAdapter.notifyDataSetChanged();
                index++;
            }
        });

        //메모장 진입
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, InsidePlannerActivity.class);
                intent.putExtra("position", synchro(memoDB)[position]);
                clickPosition = position;
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

    //DB 삭제 기능(삭제후 다음 db들 한칸 씩 앞으로)
    void deleteDB(LocalDB localDB, int index){
        localDB.delete(index);
        for(int i = index;i<plannerList.size();i++){
            if(i==index){
                localDB.insert(localDB.getTitle(i+1),i,localDB.getContent(i+1));
            }
            else localDB.update(localDB.getTitle(i+1),i,localDB.getContent(i+1));
        }
        localDB.delete(plannerList.size()-1);
    }

    //버튼 인덱스와 메모 테이블 인덱스 일치화 메서드
    int[] synchro(LocalDB localDB){
        int dbSize = localDB.count()-1;
        int[] dbPosition = new int[localDB.count()];
        for(int i =0;i<localDB.count();i++){
            dbPosition[i]=dbSize;
            dbSize--;
        }
        return  dbPosition;
    }
}
