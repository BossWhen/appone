package com.example.joker.appone.fragement.root;

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
import android.widget.ListView;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.adapter.BookAdapter;
import com.example.joker.appone.demo.Book;

import java.util.ArrayList;
import java.util.List;
/*
 * 管理员操作 书籍信息查找碎片
 * */
public class BookFind extends Fragment {
    private List<Book> bookList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_find,container,false);
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bookFindLoad = (Button) getActivity().findViewById(R.id.book_find_load);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        bookFindLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ListView listView = (ListView)getActivity().findViewById(R.id.book_find_list);
                bookList.clear();
                EditText bookFindInput = getActivity().findViewById(R.id.book_find_input);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String current_sql_sel = "SELECT * FROM book where bookname like '%"+bookFindInput.getText().toString()+"%'";
                Cursor cursor = db.rawQuery(current_sql_sel, null);
                if (cursor.moveToFirst()){
                    do {
                        String  id = cursor.getString(cursor.getColumnIndex("id"));
                        String  bookid = cursor.getString(cursor.getColumnIndex("bookid"));
                        int  typeid = cursor.getInt(cursor.getColumnIndex("typeid"));
                        String  bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                        String  author = cursor.getString(cursor.getColumnIndex("autor"));
                        String  pulish = cursor.getString(cursor.getColumnIndex("publish"));
                        String  unitprice = cursor.getString(cursor.getColumnIndex("unitprice"));
                        String  pricture = cursor.getString(cursor.getColumnIndex("pricture"));
                        String  publishdate = cursor.getString(cursor.getColumnIndex("publishdate"));
                        String  des = cursor.getString(cursor.getColumnIndex("des"));
                        String  reserve = cursor.getString(cursor.getColumnIndex("reserve"));
                        Book book = new Book(id,bookid,typeid,bookname,author,pulish,unitprice,pricture,publishdate,des,reserve);
                        bookList.add(book);
                    }while (cursor.moveToNext());
                }
                BookAdapter adapter1 = new BookAdapter(getContext(),R.layout.book_find_list,bookList);
                listView.setAdapter(adapter1);
            }
        });
    }
}
