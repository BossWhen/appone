package com.example.joker.appone;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
/*
* 登录主活动
* */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private CheckBox SavePassword;
    private CheckBox AutoLogin;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //第一次登录时 向数据库添加管理员账号

        dbHelper = new MyDatabaseHelper(this,"BMS.db",null ,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String current_sql_sel = "SELECT * FROM users where username = 'root'";
        Cursor cursor = db.rawQuery(current_sql_sel, null);
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("username","root");
            values.put("userpassword","123456");
            db.insert("users",null,values);
            values.clear();
        }


        EditText user = (EditText)findViewById(R.id.user);
        EditText userPassword = (EditText)findViewById(R.id.user_password);
        SavePassword = (CheckBox) findViewById(R.id.save_password);
        AutoLogin = (CheckBox) findViewById(R.id.auto_login);
        sharedPreferences = getSharedPreferences("zhanghao", 0);
        //记住密码
        boolean  isCheckBox1 =sharedPreferences.getBoolean("CheckBox1", false);
        //自动登录
        boolean  isCheckBox2 =sharedPreferences.getBoolean("CheckBox2", false);

        String names=sharedPreferences.getString("username","");
        String passwords=sharedPreferences.getString("userpassword", "");
        //记住密码和账号
        if(isCheckBox1){
            if(!names.equals("")&&!passwords.equals("")){
                user.setText(names);
                userPassword.setText(passwords);
                SavePassword.setChecked(true);
            }else{
                if(!names.equals("")){
                    user.setText(names);
                }
            }
        }
        //自动登录操作
        if(isCheckBox2){
            dbHelper.getWritableDatabase();
            Cursor cursor1 = db.query("users",null,null,null,null,null,null);
            int x=0;
            if (cursor1.moveToFirst()){
                do{
                    String name = cursor1.getString(cursor.getColumnIndex("username"));
                    String password = cursor1.getString(cursor.getColumnIndex("userpassword"));
                    if (user.getText().toString().equals(name)){
                        if((userPassword.getText().toString().equals(password))){
                            //登录成功
                            // 判断是否为管理员登录
                            x = 2;
                            Intent intent = new Intent();
                            if (user.getText().toString().equals("root")){
                                intent = new Intent(LoginActivity.this,RootMainActivity.class);
                            }else {
                                intent = new Intent(LoginActivity.this,UserMainActivity.class);
                            }

                            intent.putExtra("username",name);
                            startActivity(intent);
                            finish();
                        }else{
                            x=1;
                        }
                    }
                }while (cursor1.moveToNext());
            }
            if(x == 0){
                Toast.makeText(LoginActivity.this,"没有该用户",Toast.LENGTH_SHORT).show();
            }
            if(x ==1 ){
                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
            if(x ==2 ){
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        }

        Button login = (Button)findViewById(R.id.login);
        Button forget = (Button)findViewById(R.id.forget);
        login.setOnClickListener(this);
        forget.setOnClickListener(this);
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击登录操作
            case R.id.login:
                EditText user = (EditText)findViewById(R.id.user);
                EditText userPassword = (EditText)findViewById(R.id.user_password);
                dbHelper.getWritableDatabase();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("users",null,null,null,null,null,null);
                int x=0;
                if (cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("username"));
                        String password = cursor.getString(cursor.getColumnIndex("userpassword"));
                        if (user.getText().toString().equals(name)){
                            if((userPassword.getText().toString().equals(password))){
                                //登录成功
                                x = 2;
                                Intent intent = new Intent();
                                if (user.getText().toString().equals("root")){
                                    intent = new Intent(LoginActivity.this,RootMainActivity.class);
                                }else {
                                    intent = new Intent(LoginActivity.this,UserMainActivity.class);
                                }
                                intent.putExtra("username",name);
                                startActivity(intent);
                                finish();
                            }else{
                                x=1;
                            }
                        }
                    }while (cursor.moveToNext());
                }
                if(x == 0){
                    Toast.makeText(LoginActivity.this,"没有该用户",Toast.LENGTH_SHORT).show();
                }
                if(x ==1 ){
                    Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }
                if(x ==2 ){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }
                //点击 记住密码 CheckBox
                if(SavePassword.isChecked()){
                    sharedPreferences.edit().putBoolean("CheckBox1", true).commit();
                    sharedPreferences.edit().putString("username", user.getText().toString()).commit();
                    sharedPreferences.edit().putString("userpassword", userPassword.getText().toString()).commit();

                }else{
                    sharedPreferences.edit().putBoolean("CheckBox1", false).commit();
                    sharedPreferences.edit().putString("username", user.getText().toString()).commit();
                    sharedPreferences.edit().putString("userpassword", "").commit();
                }
                //点击 自动登录 CheckBox
                if(AutoLogin.isChecked()){
                    sharedPreferences.edit().putBoolean("CheckBox2", true).commit();
                }

        }
    }
}
