package org.techtown.healing_camp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    SQLiteDatabase database;
    openDatabase(databaseName)// 데이터베이스 열기

    execSQL(String): String을 SQL문으로 작성

    Cursor cursor = db.rawQuery("SELECT * FROM Person", null); // sql질의
 */
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

    public void insert(String title, int num, String contents) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Memo VALUES('" + title + "', " + num + ", '" + contents + "')");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void Update(String title, int num, String contents) // 데이터 베이스 수정
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Memo SET TITLE = " + title + ", CONTENTS = '" + contents + "'" + " WHERE NUM = '" + num + "'");
        db.close();
    }

    public String getResult() // DB값 얻는 메서드
    {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Memo", null);
        while (cursor.moveToNext()) {
            result += " 제목 : " + cursor.getString(0)
                    + ", 인덱스 : "
                    + cursor.getInt(1)
                    + ", 내용 : "
                    + cursor.getString(2)
                    + "\n";
        }
        return result;
    }
}
