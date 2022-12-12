package org.techtown.healing_camp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class DetailViewActivity extends AppCompatActivity {
    SearchList searchList;
    String [] result;
    Button addCampingPlaceToMemo, onClickBackLayer;
    TextView text0,text2,text3,text4,text5,text6,text7,text8;
    ImageView image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view_layout);

        addCampingPlaceToMemo = findViewById(R.id.addCampingPlaceToMemo);
        onClickBackLayer = findViewById(R.id.onClickBackLayer);

        image9 = findViewById(R.id.image9);
        text0 = findViewById(R.id.text0);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);


        //선택 정보 가져오기
        result = getIntent().getStringArrayExtra("List");
        if(result==null){
            result = PlannerObject.getResult();
        }
        searchList = new SearchList(result);
        //상세 내용작성
        text0.setText(searchList.getName());
        text2.setText(searchList.getDetailInfo());
        text3.setText(searchList.getHowToCome());
        text4.setText(searchList.getBigLocal());
        text5.setText(searchList.getLocal());
        text6.setText(searchList.getTel());
        text7.setText(searchList.getHomePage());
        text8.setText(searchList.getTheme());
        String image = searchList.getUrl();
        Glide.with(this).load(image)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .fallback(R.drawable.no_image)
                .into(image9);
        //추가 버튼
        addCampingPlaceToMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerObject.setResult(result);
                setResult(1);
                finish();
            }
        });
        //뒤로가기 버튼
        onClickBackLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}

