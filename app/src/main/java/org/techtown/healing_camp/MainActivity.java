package org.techtown.healing_camp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PlannerInformation> plannerList;

        ImageButton onClickMakePlanner;
        Button onClickTopScroll;
        LinearLayout plannerContainerLayout;
        ScrollView scrollView;
        Integer arrayIndex = 0;

        Button check;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            onClickMakePlanner = findViewById(R.id.OnClickMakePlanner);
            onClickTopScroll = findViewById(R.id.onClickTopScroll);
            plannerContainerLayout = findViewById(R.id.layoutPlannerContainer);
            scrollView = findViewById(R.id.scrollView);

            check = findViewById(R.id.check);

            //새로운 플래너 생성
            plannerList = new ArrayList<>();
            //애니매이션 생성
            Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_up_animation);

            onClickMakePlanner.setOnClickListener(new View.OnClickListener() {       //버튼 누르면 새로운 플래너 생성
                @Override
                //생성된 플레너는 리스트에 스택
                public void onClick(View view) {
                    PlannerInformation plannerInformation = new PlannerInformation(arrayIndex.toString());//플래너 내부 정보가 입력 된 플래너정보객체 생성
                    plannerList.add(plannerInformation); // 리스트에 생성된 플래너정보객체 삽입
                    InflatorPlanner inflatorPlanner = new InflatorPlanner(getApplicationContext(), plannerList.get(arrayIndex));//삽입된 플래너정보 화면에 생성시킬 준비
                    plannerContainerLayout.addView(inflatorPlanner);// 화면에 생성
                    // plannerContainerLayout.startAnimation(popUpAnimation); //애니메이션 추가
                    arrayIndex += 1;
                }
            });

            //리스트에 잘 들어갔는지 확인하는 버튼 코드. 버튼 누르면 생성된 리스트의 내부정보 보여주는 버튼
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check.setText(plannerList.get(arrayIndex - 1).text);
                }

            });

            //if(){onClickTopScroll.setVisibility(View.VISIBLE);}
            //else if (){onClickTopScroll.setVisibility(View.INVISIBLE);}

            onClickTopScroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scrollView.fullScroll(scrollView.FOCUS_UP);
                }
            });//스크롤 버튼이 생성되고, 버튼을 누르면 위로 올라감

       /* .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check.setText("3");
            }
        });
        */ //생성된 박스를 클릭했을때 이벤트 만들기 시도(실패)

/*
        // 시험삼아 만들어 본 데이터 베이스
        LocalDB database = new LocalDB(MainActivity.this,1); // 메인액티비티에서 실행해야 머리 안아픔

        database.insert("second title",2,"this is second Memo");
        database.update("updated first Memo",1,"for test -> updated first Memo");
        database.delete(2); // 이렇게 하면 2번 메모 삭제됨
        System.out.println(database.getResult(1));//이렇게 하면 1번 메모 출력됨
*/

            // SearchDB예제
            SearchDB searchDatabase = new SearchDB(MainActivity.this, 1); // SearchDB생성
            // KeywordSearch search = new KeywordSearch(); // 키워드 검색 시작
            // String [][] result=search.searchStart("바다"); // 배열에 결과 받아옴

            // searchDatabase.insert(result,2,1); // 두번쨰 결과값(캠핑장 정보) 입력
            // System.out.println(searchDatabase.searchAll()); // 데이터베이스 전체 출력 (test용)


        }
    }