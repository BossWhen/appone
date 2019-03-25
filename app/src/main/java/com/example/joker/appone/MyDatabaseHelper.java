package com.example.joker.appone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/*
* 数据库的创建
* */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_USERS ="create table users(" +
            "id integer primary key autoincrement," +
            "username text," +
            "userpassword text)";
    public static final String CREATE_READER ="create table reader(" +
            "readerid integer ," +
            "name text," +
            "age integer," +
            "sex text," +
            "phone integer)";
    public static final String CREATE_BORROWBOOK ="create table borrowbook(" +
            "borrowid integer primary key autoincrement," +
            "readerid integer ," +
            "bookid integer," +
            "borrowdate text," +
            "returndate text," +
            "fine real)";
    public static final String CREATE_BOOKTYPR ="create table booktype(" +
            "id integer primary key autoincrement," +
            "typename text)";
    public static final String CREATE_BOOK="create table book(" +
            "id integer primary key autoincrement," +
            "bookid integer,"+
            "typeid integer," +
            "bookname text," +
            "autor text," +
            "publish text," +
            "publishdate text," +
            "des text," +
            "unitprice real," +
            "pricture text," +
            "reserve text)";
    private Context mContext;
    public MyDatabaseHelper(Context context,String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERS);
        sqLiteDatabase.execSQL(CREATE_READER);
        sqLiteDatabase.execSQL(CREATE_BORROWBOOK);
        sqLiteDatabase.execSQL(CREATE_BOOKTYPR);
        sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"创建数据库成功",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
