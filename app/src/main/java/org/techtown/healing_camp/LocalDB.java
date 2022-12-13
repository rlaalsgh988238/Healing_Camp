package org.techtown.healing_camp;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class LocalDB extends SQLiteOpenHelper
{
    static final String DATABASE_NAME = "test.db";

    public LocalDB(Context context, int version)
    {
        super(context, DATABASE_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Memo(TITLE TEXT, NUM INT, CONTENTS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // 데이터 베이스 유지보수에 쓰는거지만 우리는 쓸일이 없음
    {

    }

    public void insert(String title, int num, String contents) // 데이터 베이스 값 추가, 인자로 제목, 인덱스 넘버, 내용 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Memo VALUES('" + title + "', " + num + ", '" + contents + "')");
        db.close();
        System.out.println("insert succes");
    }

    public void update(String title, int num, String contents) // 데이터 베이스 수정 , 인자로 수정된 제목과 내용, 그리고 그 인덱스 넘버 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Memo SET TITLE = '" + title + "', CONTENTS = '" + contents + "'" + " WHERE NUM = " + num);
        db.close();
    }

    public void updateTitle(String title, int num) // 데이터 베이스 수정 , 인자로 수정된 제목과 내용, 그리고 그 인덱스 넘버 전달
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Memo SET TITLE = '" + title + "' WHERE NUM = " + num);
        db.close();
    }

    public String getResult(int num) // DB값 얻는 메서드 , 인자로 인덱스 넘버 전달
    {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        String sql="SELECT * FROM Memo WHERE NUM = "+num;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext())
        {
            result += " 제목 : " + cursor.getString(0)
                    + ", 내용 : "
                    + cursor.getString(2)
                    + "\n";
        }

        db.close();
        return result;
    }

    public String getTitle(int num){
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM Memo WHERE NUM = "+num;
        Cursor cursor = db.rawQuery(sql,null);
        String result = "";
        while (cursor.moveToNext()) {
            result = cursor.getString(0);
        }

        db.close();
        return result;
    }

    public String getContent(int num){
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM Memo WHERE NUM = "+num;
        Cursor cursor = db.rawQuery(sql,null);
        String result = "";
        while (cursor.moveToNext()) {
            result = cursor.getString(2);
        }

        db.close();
        return result;
    }

    public void delete(int num)
    { // 값 삭제 , 인자로 인덱스 넘버
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Memo WHERE NUM = " + num);
        db.close();
    }

    public int count(){

        SQLiteDatabase db = getReadableDatabase();

        String sql="SELECT count(*) FROM MEMO";
        int count =0;
        String result = "";

        Cursor cursor = db.rawQuery(sql,null);
        try
        {
            while (cursor.moveToNext()) {
                count = cursor.getInt(0);
                System.out.println("sssssssssssssssssssssssssssss");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fffffffffffffffffffffff");
        }


        return count;
    }
}
