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

        // 시험삼아 만들어 본 데이터 베이스
        LocalDB database = new LocalDB(MainActivity.this,1);
        System.out.println(database.getResult(1));

        KeywordSearch search = new KeywordSearch();
        search.searchStart("바다");
    }
}