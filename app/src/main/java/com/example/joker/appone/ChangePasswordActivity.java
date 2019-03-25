package com.example.joker.appone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        dbHelper = new MyDatabaseHelper(this,"BMS.db",null ,1);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        username = name;
        TextView userName = (TextView)findViewById(R.id.changepassword_username);
        userName.setText(name);
        Button save = (Button)findViewById(R.id.changepassword_save);
        Button cancel = (Button)findViewById(R.id.changepassword_cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                TextView userName = (TextView)findViewById(R.id.changepassword_username);
                EditText userPassword = (EditText)findViewById(R.id.changepassword_password);
                EditText userNewPassword = (EditText)findViewById(R.id.changepassword_new_password);
                Cursor cursor = db.query("users",null,null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String  username = cursor.getString(cursor.getColumnIndex("username"));
                        String  userpassword = cursor.getString(cursor.getColumnIndex("userpassword"));
                        if (userName.getText().toString().equalsIgnoreCase(username)){
                            if (userPassword.getText().toString().equalsIgnoreCase(userpassword)){
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("userpassword",userNewPassword.getText().toString());
                                db.update("users",contentValues,"username = ?",new String []{userName.getText().toString()});
                                contentValues.clear();
                                Toast.makeText(view.getContext(),"修改成功！！",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(view.getContext(),"原密码错误！！",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }while (cursor.moveToNext());
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
