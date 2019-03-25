package com.example.joker.appone;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joker.appone.fragement.root.BookAdd;
import com.example.joker.appone.fragement.root.BookChange;
import com.example.joker.appone.fragement.root.BookFind;
import com.example.joker.appone.fragement.root.BorrowChange;
import com.example.joker.appone.fragement.root.ReaderAdd;
import com.example.joker.appone.fragement.root.ReaderChange;
import com.example.joker.appone.fragement.root.ReaderFind;
import com.example.joker.appone.fragement.root.Registration;

import static android.widget.Toast.LENGTH_SHORT;
/*
* 管理员登录主活动
* */
public class RootMainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    private String username;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        username = name;
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        //设置默认碎片界面
        replaceFragment(new BookFind());
        TextView username =  navigationView.getHeaderView(0).findViewById(R.id.nav_username);
        username.setText("欢迎，"+name);
        navigationView.setCheckedItem(R.id.bookFind);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bookFind){
                    replaceFragment(new BookFind());
                    Toast.makeText(RootMainActivity.this,"点击了查询书籍",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.bookAdd){
                    replaceFragment(new BookAdd());
                    Toast.makeText(RootMainActivity.this,"点击了添加书籍",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.bookInfoChange){
                    replaceFragment(new BookChange());
                    Toast.makeText(RootMainActivity.this,"点击了书籍信息修改",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.userFind){
                    replaceFragment(new ReaderFind());
                    Toast.makeText(RootMainActivity.this,"点击了查询用户",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.userAdd){
                    replaceFragment(new ReaderAdd());
                    Toast.makeText(RootMainActivity.this,"点击了添加用户",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.userInfoFind){
                    replaceFragment(new ReaderChange());
                    Toast.makeText(RootMainActivity.this,"点击了用户信息修改",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.borrowIn){
                    replaceFragment(new Registration());
                    Toast.makeText(RootMainActivity.this,"点击了借书登记",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.borrowInfoChange){
                    replaceFragment(new BorrowChange());
                    Toast.makeText(RootMainActivity.this,"点击了费用修改",Toast.LENGTH_SHORT).show();
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        RootMainActivity.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragement_root,fragment);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.changePassword:
                Intent intent1 = new Intent(RootMainActivity.this,ChangePasswordActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
                break;
            case R.id.outLogin:
                SharedPreferences sharedPreferences = getSharedPreferences("zhanghao", 0);
                sharedPreferences.edit().putBoolean("CheckBox2", false).commit();
                Intent intent = new Intent(RootMainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }


}