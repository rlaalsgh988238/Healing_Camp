package org.techtown.healing_camp;

import android.app.appsearch.SearchResult;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SearchDB extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "SearchAdd.db";

    public SearchDB(Context context, int version) {
        super(context, DATABASE_NAME, null, version);

    }
    //db에 저장된 캠핑장소가 있는지 확인하기 위한 플래그(있으면 1, 없으면 0) - FLAG
    @Override
    public void onCreate(SQLiteDatabase db) // 테이블 생성
    {
        db.execSQL("CREATE TABLE SearchResult(" +
                " NUM INT," +
                " FLAG INT," +
                " TITLE TEXT," +
                " LINEINTRO TEXT," +
                " INTRO TEXT," +
                " LOCATION TEXT," +
                " DONM TEXT," +
                " SIGUNGU TEXT," +
                " TEL TEXT," +
                " HOMPAGE TEXT," +
                " THEME TEXT," +
                " IMAGE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // 데이터 베이스 유지보수에 쓰는거지만 우리는 쓸일이 없음
    {

    }

    @Nullable
    public void insert(String[] result, int searchNum, int flag) // 데이터 베이스 값 추가, 인자로 제목, 인덱스 넘버, 내용 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SearchResult VALUES(' " + searchNum + " ',' " + flag + " ',' " + result[0] + " ',' " + result[1] + " ',' " + result[2] + " ',' " + result[3] + " ',' " + result[4] + " ',' " + result[5] + " ',' " + result[6] + " ',' " + result[7] + " ',' " + result[8] + " ',' " + result[9] + "')");
        db.close();
    }

    public void update(String[] result, int num,int flag) // 데이터 베이스 수정 , 인자로 수정된 제목과 내용, 그리고 그 인덱스 넘버 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SearchResult SET " +
                " FLAG = '" + flag +
                "', TITLE = '" + result[0] + "'" +
                ", LINEINTRO = '" + result[1] + "'" +
                ", INTRO = '" + result[2] + "'" +
                ", LOCATION = '" + result[3] + "'" +
                ", DONM = '" + result[4] + "'" +
                ", SIGUNGU = '" + result[5] + "'" +
                ", TEL = '" + result[6] + "'" +
                ", HOMPAGE = '" + result[7] + "'" +
                ", THEME = '" + result[8] + "'" +
                ", IMAGE = '" + result[9] + "'" +
                " WHERE NUM = " + num);
        db.close();
    }

    public String[] getResult(int memoNum) // DB값 얻는 메서드 , 인자로 인덱스 넘버 전달
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM SearchResult WHERE NUM = " + memoNum;
        String[] result = new String[12];

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            for(int i=2;i<12;i++){
                result[i-2] = cursor.getString(i);
            }
        }
        return result;
    }
    public int getFlag(int num){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM SearchResult WHERE NUM = " + num;
        int result=0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            result = Integer.parseInt(cursor.getString(1));
        }
        db.close();
        return result;
    }

    public void delete(int memoNum) // 값 삭제 , 인자로 인덱스 넘버
    { // 값 삭제 , 인자로 인덱스 넘버
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM SearchResult WHERE NUM = " + memoNum);
            db.close();
        }
    }
