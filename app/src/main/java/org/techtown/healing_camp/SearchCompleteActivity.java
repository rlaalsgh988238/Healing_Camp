package org.techtown.healing_camp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.xml.transform.sax.SAXResult;

public class SearchCompleteActivity extends AppCompatActivity {
    ArrayList<SearchList> searchList;
    ArrayList<SearchList> initSearchList;
    Button onClickBackLayer;
    ListView searchListView;
    boolean flag;
    String keyWord;
    String result[][] = new String[10][10];
    int deleteIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_layout);

        KeywordSearch search = new KeywordSearch(); // 키워드서치 클래스 객체 생성

        Intent intentToInsideView = new Intent(SearchCompleteActivity.this,InsidePlannerActivity.class);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);
        searchListView = findViewById(R.id.searchListView);


        keyWord = getIntent().getStringExtra("keyWord");
        result = search.searchStart(keyWord); // 검색 시작, result배열 리턴
        searchList = new ArrayList<SearchList>();
        initSearchList = new ArrayList<SearchList>();
        SearchAdapter searchAdapter = new SearchAdapter(this,searchList);
        searchListView.setAdapter(searchAdapter);

        //조건식
        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == 1){
                    setResult(1);
                    finish();
                };
            }
        });
        //리스트 생성
        for(int i=0;i<result.length;i++){
            if(result[i][0]==null){break;}
            searchList.add(new SearchList(result[i]));
            deleteIndex++;
        }
        //리스트 클릭
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SearchCompleteActivity.this,DetailViewActivity.class);
                intent.putExtra("List",result[position]);
                startActivityResult.launch(intent);
            }
        });
        //뒤로가기
        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<deleteIndex;i++){
                    for(int j = 0;j<10;j++){
                        result[i][j]=null;
                    }
                }
                finish();
            }
        });
    }
}
