package com.example.joker.appone.fragement.root;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.adapter.ReaderChangeAdapter;
import com.example.joker.appone.demo.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderChange  extends Fragment {
    private List<Reader> readerList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_change,container,false);
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button readerFindLoad = (Button) getActivity().findViewById(R.id.reader_change_load);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        readerFindLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ListView listView = (ListView)getActivity().findViewById(R.id.reader_change_list);
                readerList.clear();
                EditText readerFindInput = getActivity().findViewById(R.id.reader_change_input);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String current_sql_sel = "SELECT * FROM reader where readerid like '%"+readerFindInput.getText().toString()+"%'";
                Cursor cursor = db.rawQuery(current_sql_sel, null);
                if (cursor.moveToFirst()){
                    do {
                        String  name = cursor.getString(cursor.getColumnIndex("name"));
                        int  readerid = cursor.getInt(cursor.getColumnIndex("readerid"));
                        String  sex = cursor.getString(cursor.getColumnIndex("sex"));
                        int  age = cursor.getInt(cursor.getColumnIndex("age"));
                        int  phone = cursor.getInt(cursor.getColumnIndex("phone"));
                        Reader reader = new Reader(readerid,  name,  age,  sex,  phone);
                        readerList.add(reader);
                    }while (cursor.moveToNext());
                }
                ReaderChangeAdapter adapter = new ReaderChangeAdapter(getContext(),R.layout.reader_change_list,readerList);
                listView.setAdapter(adapter);
            }
        });
    }
}