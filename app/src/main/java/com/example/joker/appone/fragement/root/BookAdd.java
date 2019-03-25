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
import com.example.joker.appone.tools.AmountEditText;

import java.sql.Date;
import java.text.SimpleDateFormat;
/*
* 管理员操作 书籍添加碎片
* */
public class BookAdd extends Fragment {
    private MyDatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_add,container,false);
        return view ;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        Button button = getActivity().findViewById(R.id.book_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText bookAddId = (EditText) getActivity().findViewById(R.id.book_add_id);
                EditText bookAddName = (EditText) getActivity().findViewById(R.id.book_add_name);
                EditText bookAddAutor = (EditText) getActivity().findViewById(R.id.book_add_autor);
                AmountEditText bookAddPrice = (AmountEditText) getActivity().findViewById(R.id.book_add_price);
                EditText bookAddKucun = (EditText) getActivity().findViewById(R.id.book_add_kucun);
                if (bookAddId.getText().toString().trim().equals("")|| bookAddName.getText().toString().trim().equals("")||bookAddAutor.getText().toString().trim().equals("")||bookAddPrice.getText().toString().trim().equals("")||bookAddKucun.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"请将信息填完整！！！",Toast.LENGTH_SHORT).show();
                }else {
                    if (!bookAddPrice.isConformRules()){
                        Toast.makeText(getActivity(),"价格格式不对！！！",Toast.LENGTH_SHORT).show();
                    }else{
                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        int roll = 0;
                        Cursor cursor = db.query("book",null,null,null,null,null,null,null);
                        if (cursor.moveToFirst()){
                            do {
                                String  bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                                String  bookAddId1 = cursor.getString(cursor.getColumnIndex("bookid"));
                                if (bookAddName.getText().toString().equalsIgnoreCase(bookname)){roll =1;}
                                if (bookAddId.getText().toString().equalsIgnoreCase(bookAddId1)){roll =2;}
                            }while (cursor.moveToNext());
                        }
                        if (roll == 0){
                            ContentValues values = new ContentValues();
                            values.put("bookid",bookAddId.getText().toString());
                            values.put("bookname",bookAddName.getText().toString());
                            values.put("autor",bookAddAutor.getText().toString());
                            values.put("unitprice",bookAddPrice.getText().toString());
                            values.put("reserve",bookAddKucun.getText().toString());
                            SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                            Date curDate =  new Date(System.currentTimeMillis());
                            values.put("publishdate", curDate.toString());
                            db.insert("book",null,values);
                            values.clear();
                            Toast.makeText(getActivity(),"成功添加"+bookAddName.getText(),Toast.LENGTH_SHORT).show();
                        }else {
                            if (roll == 1){Toast.makeText(getActivity(),"书名:"+bookAddName.getText()+"已经存在!!!",Toast.LENGTH_SHORT).show();}
                            if (roll == 2){Toast.makeText(getActivity(),"书的编号:"+bookAddId.getText()+"已经存在!!!",Toast.LENGTH_SHORT).show();}
                        }
                    }
                }
            }
        });
    }
}