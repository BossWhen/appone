package com.example.joker.appone.fragement.root;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Registration extends Fragment {
    private MyDatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        Button button = getActivity().findViewById(R.id.registration_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rollUserId="",rollBookId="";
                int rollreserve = 0;
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                EditText registrationBookid = (EditText)getView().findViewById(R.id.registration_bookid);
                EditText registrationUserid = (EditText)getView().findViewById(R.id.registration_userid);
                if (registrationBookid.getText().toString().trim().equals("")|| registrationUserid.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"请将信息填完整！！！",Toast.LENGTH_SHORT).show();
                }else{
                    Cursor cursor = db.query("reader",null,null,null,null,null,null,null);
                    if (cursor.moveToFirst()){
                        do {
                            String  readerid = cursor.getString(cursor.getColumnIndex("readerid"));
                            if (registrationUserid.getText().toString().equalsIgnoreCase(readerid)){rollUserId =readerid;}
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    Cursor cursor1 = db.query("book",null,null,null,null,null,null,null);
                    if (cursor1.moveToFirst()){
                        do {
                            String  bookid = cursor1.getString(cursor1.getColumnIndex("bookid"));
                            int  reserve = cursor1.getInt(cursor1.getColumnIndex("reserve"));
                            if (registrationBookid.getText().toString().equalsIgnoreCase(bookid)){rollBookId =bookid;rollreserve = reserve;}
                        }while (cursor1.moveToNext());
                    }
                    cursor1.close();
                    if (!(rollUserId.equals("")&&rollBookId.equals(""))){
                        if (rollreserve == 0){
                            Toast.makeText(getActivity(),"该图书已被借完！！",Toast.LENGTH_SHORT).show();
                        }else {
                            ContentValues values = new ContentValues();
                            values.put("bookid",rollBookId);
                            values.put("readerid",rollUserId);
                            SimpleDateFormat formatter   =   new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                            Date curDate =  new Date(System.currentTimeMillis());
                            values.put("borrowdate", curDate.toString());
                            db.insert("borrowbook",null,values);
                            values.clear();
                            rollreserve = rollreserve - 1;
                            values.put("reserve",rollreserve);
                            db.update("book",values,"bookid = ?",new String[]{rollBookId});
                            values.clear();
                            Toast.makeText(getActivity(),"借书成功！！",Toast.LENGTH_SHORT).show();
                        }

                    }
                    if (rollUserId.equals("")){Toast.makeText(getActivity(),"用户账号:"+registrationUserid.getText()+"不存在!!!",Toast.LENGTH_SHORT).show();}
                    if (rollBookId.equals("")){Toast.makeText(getActivity(),"图书编号:"+registrationBookid.getText()+"不存在!!!",Toast.LENGTH_SHORT).show();}
                }


            }
        });
    }
}
