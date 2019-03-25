package com.example.joker.appone.fragement.root;

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

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.adapter.BorrowBookChangeAdapter;
import com.example.joker.appone.demo.BorrowBook;

import java.util.ArrayList;
import java.util.List;


public class BorrowChange extends Fragment {
    private MyDatabaseHelper dbHelper;
    private List<BorrowBook> borrowBookList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.borrow_change_find,container,false);
        return view ;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bookChangeLoad = (Button) getActivity().findViewById(R.id.borrow_change_find_load);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        bookChangeLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ListView listView = (ListView)getActivity().findViewById(R.id.borrow_change_find_list);
                borrowBookList.clear();
                EditText borrowBookFindInput = getActivity().findViewById(R.id.borrow_change_find_input);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String current_sql_sel = "SELECT * FROM borrowbook where bookid like '%"+borrowBookFindInput.getText().toString()+"%'";
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
                }
                Log.e(borrowBookList.size()+"","ssssssssssssssssssssssssssssssssss");
                BorrowBookChangeAdapter adapter = new BorrowBookChangeAdapter(getContext(),R.layout.borrow_change_find_list,borrowBookList);
                listView.setAdapter(adapter);
            }
        });
    }
}
