package org.techtown.healing_camp;

import android.app.appsearch.SearchResult;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SearchDB extends SQLiteOpenHelper
{
    static final String DATABASE_NAME = "SearchAdd.db";

    public SearchDB(Context context, int version)
    {
        super(context, DATABASE_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) // 테이블 생성
    {
        db.execSQL("CREATE TABLE SearchResult(MEMONUM INT, TITLE TEXT, LINEINTRO TEXT, INTRO TEXT, LOCATION TEXT, DONM TEXT, SIGUNGU TEXT, TEL TEXT, HOMPAGE TEXT, THEME TEXT, IMAGE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // 데이터 베이스 유지보수에 쓰는거지만 우리는 쓸일이 없음
    {

    }

    public void insert(String[][] result, int resultNum, int memoNum) // 데이터 베이스 값 추가, 인자로 제목, 인덱스 넘버, 내용 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SearchResult VALUES('"+memoNum+" ',' " + result[resultNum][0] + " ',' " + result[resultNum][1] + " ',' " + result[resultNum][2] + " ',' " + result[resultNum][3] + " ',' " + result[resultNum][4] + " ',' " + result[resultNum][5] + " ',' " + result[resultNum][6] + " ',' " + result[resultNum][7] + " ',' " + result[resultNum][8] + " ',' " + result[resultNum][9] + "')");
        db.close();
        System.out.println("insert succes");
    }

    public void update(String title, int num, String contents) // 데이터 베이스 수정 , 인자로 수정된 제목과 내용, 그리고 그 인덱스 넘버 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SearchResult SET TITLE = '" + title + "', CONTENTS = '" + contents + "'" + " WHERE NUM = " + num);
        db.close();
    }

    public String getResult(int memoNum) // DB값 얻는 메서드 , 인자로 인덱스 넘버 전달
    {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        String sql="SELECT * FROM SearchResult WHERE MEMONUM = "+memoNum;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext())
        {
            result += " 제목 : " + cursor.getString(0)
                    + "\n 내용 : " + cursor.getString(1)
                    + "\n 내용 : " + cursor.getString(2)
                    +"\n 내용 : " + cursor.getString(3)
                    +"\n 내용 : " + cursor.getString(4)
                    +"\n 내용 : " + cursor.getString(5)
                    +"\n 내용 : " + cursor.getString(6)
                    +"\n 내용 : " + cursor.getString(7)
                    +"\n 내용 : " + cursor.getString(8)
                    +"\n 내용 : " + cursor.getString(9)
                    +"\n 내용 : " + cursor.getString(10);
        }
        return result;
    }

    public void delete(int memoNum) // 값 삭제 , 인자로 인덱스 넘버
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM SearchResult WHERE MEMONUM = " + memoNum);
        db.close();
    }

    public String searchAll() // 데이터베이스 테이블 열어보기
    {
        String sql="SELECT * FROM SearchResult";
        SQLiteDatabase db = getWritableDatabase();
        String result="";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext())
        {
            result += " 제목 : " + cursor.getString(0)
                    + "\n 내용 : " + cursor.getString(1)
                    + "\n 내용 : " + cursor.getString(2)
                    +"\n 내용 : " + cursor.getString(3)
                    +"\n 내용 : " + cursor.getString(4)
                    +"\n 내용 : " + cursor.getString(5)
                    +"\n 내용 : " + cursor.getString(6)
                    +"\n 내용 : " + cursor.getString(7)
                    +"\n 내용 : " + cursor.getString(8)
                    +"\n 내용 : " + cursor.getString(9)
                    +"\n 내용 : " + cursor.getString(10);
        }
        return result;
    }
}
