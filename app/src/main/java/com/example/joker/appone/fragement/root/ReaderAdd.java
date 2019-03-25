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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReaderAdd extends Fragment {
    private MyDatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        Button button = getActivity().findViewById(R.id.reader_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup readerAddSex = (RadioGroup) getView().findViewById(R.id.reader_nex);
                RadioButton readerAddSexSelected =  getView().findViewById(readerAddSex.getCheckedRadioButtonId());
                EditText readerId = (EditText)getView().findViewById(R.id.reader_add_id);
                EditText readerName = (EditText)getView().findViewById(R.id.reader_add_name);
                EditText readerAge = (EditText)getView().findViewById(R.id.reader_add_age);
                EditText readerPhone = (EditText)getView().findViewById(R.id.reader_add_phone);
                if (readerId.getText().toString().trim().equals("")|| readerName.getText().toString().trim().equals("")||readerAge.getText().toString().trim().equals("")||readerPhone.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"请将信息填完整！！！",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    int roll = 0;
                    Cursor cursor = db.query("reader",null,null,null,null,null,null,null);
                    if (cursor.moveToFirst()){
                        do {
                            String  readerid = cursor.getString(cursor.getColumnIndex("readerid"));
                            if (readerId.getText().toString().equalsIgnoreCase(readerid)){roll =1;}
                        }while (cursor.moveToNext());
                    }
                    if (roll == 0){
                        ContentValues values = new ContentValues();
                        values.put("readerid",readerId.getText().toString());
                        values.put("name",readerName.getText().toString());
                        values.put("age",readerAge.getText().toString());
                        values.put("sex",readerAddSexSelected.getText().toString());
                        values.put("phone",readerPhone.getText().toString());
                        db.insert("reader",null,values);
                        values.clear();
                        values.put("username",readerId.getText().toString());
                        values.put("userpassword","000000");
                        db.insert("users",null,values);
                        Toast.makeText(getActivity(),"成功添加"+readerName.getText(),Toast.LENGTH_SHORT).show();
                    }else {
                        if (roll == 1){Toast.makeText(getActivity(),"用户账号:"+readerId.getText()+"已经存在!!!",Toast.LENGTH_SHORT).show();}
                    }
                }
                Toast.makeText(getActivity(),readerAddSexSelected.getText(),Toast.LENGTH_SHORT).show();
            }
        });







    }

}
