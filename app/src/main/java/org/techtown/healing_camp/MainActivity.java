package org.techtown.healing_camp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // 시험삼아 만들어 본 데이터 베이스
        LocalDB database = new LocalDB(MainActivity.this,1); // 메인액티비티에서 실행해야 머리 안아픔

        database.insert("second title",2,"this is second Memo");
        database.update("updated first Memo",1,"for test -> updated first Memo");
        database.delete(2); // 이렇게 하면 2번 메모 삭제됨
        System.out.println(database.getResult(1));//이렇게 하면 1번 메모 출력됨
*/

        // SearchDB예제
        SearchDB searchDatabase = new SearchDB(MainActivity.this,1); // SearchDB생성
        // KeywordSearch search = new KeywordSearch(); // 키워드 검색 시작
        // String [][] result=search.searchStart("바다"); // 배열에 결과 받아옴

        // searchDatabase.insert(result,2,1); // 두번쨰 결과값(캠핑장 정보) 입력
        // System.out.println(searchDatabase.searchAll()); // 데이터베이스 전체 출력 (test용)



    }
}