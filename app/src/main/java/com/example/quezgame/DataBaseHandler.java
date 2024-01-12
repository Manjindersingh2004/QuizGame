package com.example.quezgame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, "score", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query="Create table t (score integer)";
        sqLiteDatabase.execSQL(Query);
        sqLiteDatabase.execSQL("insert into t values(0)");
    }

    void update(int score){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("update t set score="+score);
    }

    int getScore(){
        int score=0;
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select score from t",null,null);
        if(c.moveToFirst()){
            score=c.getInt(0);
        }
        return score;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
