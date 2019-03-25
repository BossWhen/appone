package com.example.joker.appone.fragement.user;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.adapter.BorrowBookUserAdapter;
import com.example.joker.appone.demo.Book;
import com.example.joker.appone.demo.BorrowBook;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class BorrowBookUser extends Fragment {
    private List<BorrowBook> borrowBookList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private  String s;
    @SuppressLint("ValidFragment")
    public  BorrowBookUser(String s){
        this.s =s;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.borrow_book_user,container,false);
        return view ;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        ListView listView = (ListView)getActivity().findViewById(R.id.borrow_book_user_list);
        borrowBookList.clear();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String current_sql_sel = "SELECT * FROM borrowbook where readerid ="+s;
        Cursor cursor = db.rawQuery(current_sql_sel, null);
        if (cursor.moveToFirst()){
            do {
                int  readerid = cursor.getInt(cursor.getColumnIndex("readerid"));
                int  borrowid = cursor.getInt(cursor.getColumnIndex("borrowid"));
                int  bookid = cursor.getInt(cursor.getColumnIndex("bookid"));
                String  borrowdate = cursor.getString(cursor.getColumnIndex("borrowdate"));
                String  returndate = cursor.getString(cursor.getColumnIndex("returndate"));
                String  fine = cursor.getString(cursor.getColumnIndex("fine"));
                BorrowBook borrowBook = new BorrowBook(readerid,borrowid,bookid,borrowdate,returndate,fine);
                borrowBookList.add(borrowBook);
            }while (cursor.moveToNext());
        }if (borrowBookList.isEmpty()){
            TextView info = (TextView)getActivity().findViewById(R.id.user_book_info);
            info.setText("暂时没有借阅书籍！");
        }else {
            BorrowBookUserAdapter adapter = new BorrowBookUserAdapter(getContext(),R.layout.borrow_book_user_find_list,borrowBookList);
            listView.setAdapter(adapter);
        }

    }
}
