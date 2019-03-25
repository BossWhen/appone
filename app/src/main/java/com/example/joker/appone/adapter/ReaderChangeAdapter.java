package com.example.joker.appone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.demo.Reader;
import com.example.joker.appone.demo.Users;
import com.example.joker.appone.tools.MyTextWatcher;

import java.util.List;

public class ReaderChangeAdapter extends ArrayAdapter<Reader> {
    private int resourceId;
    private ViewHolder holder;
    private MyDatabaseHelper dbHelper;

    public ReaderChangeAdapter(Context context, int textViewResourceId, List<Reader> objects){
        super(context,textViewResourceId,objects);
        resourceId =textViewResourceId;
    }
    @Nullable
    @Override
    public Reader getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        final Reader reader = getItem(position);
        final Users users = new Users();
        String password = "";
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.readerId = (TextView)convertView.findViewById(R.id.reader_change_id);
        holder.name = (TextView)convertView.findViewById(R.id.reader_change_name);
        holder.age = (TextView)convertView.findViewById(R.id.reader_change_age);
        holder.sex = (TextView)convertView.findViewById(R.id.reader_change_sex);
        holder.phone = (EditText)convertView.findViewById(R.id.reader_change_phone);
        holder.password = (EditText)convertView.findViewById(R.id.reader_change_password);
        holder.readerId.setText(reader.getReaderid()+"");
        holder.name.setText(reader.getName());
        holder.age.setText(reader.getAge()+"");
        holder.sex.setText(reader.getSex());
        holder.phone.setText(reader.getPhone()+"");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("users",null,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String  username = cursor.getString(cursor.getColumnIndex("username"));
                String  userpassword = cursor.getString(cursor.getColumnIndex("userpassword"));
                if (holder.readerId.getText().toString().equalsIgnoreCase(username)){
                    users.setUserpassword(userpassword);
                }
            }while (cursor.moveToNext());
        }
        holder.phone.addTextChangedListener(new MyTextWatcher(holder.phone,reader,"readerPhone"));
        holder.password.setText(users.getUserpassword());
        holder.readerChangeSave = (Button) convertView.findViewById(R.id.reader_change_save);
        holder.ReaderChangeDelet = (Button) convertView.findViewById(R.id.reader_change_delet);
        final View finalConvertViewSave = convertView;
        holder.readerChangeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.phone = (EditText)finalConvertViewSave.findViewById(R.id.reader_change_phone);
                holder.password = (EditText)finalConvertViewSave.findViewById(R.id.reader_change_password);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("phone",Integer.parseInt(holder.phone.getText().toString()));
                db.update("reader",contentValues,"readerid = ?",new String []{reader.getReaderid()+""});
                reader.setPhone(Integer.parseInt(holder.phone.getText().toString()));
                contentValues.clear();
                contentValues.put("userpassword",holder.password.getText().toString());
                db.update("users",contentValues,"username=?",new String[]{reader.getReaderid()+""});
                contentValues.clear();
                Toast.makeText(finalConvertViewSave.getContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
        final View finalConvertView = convertView;
        holder.ReaderChangeDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView info_delet =(TextView) finalConvertView.findViewById(R.id.reader_info_delet);
                new AlertDialog.Builder(view.getContext()).setTitle("确认删除吗？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("reader","readerid = ?",new String[]{reader.getReaderid()+""});
                                db.delete("users","username=?",new String[]{reader.getReaderid()+""});
                                info_delet.setText("已经删除当前书籍，请刷新列表");
                            }
                        })
                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView readerId;
        TextView name;
        TextView age;
        TextView sex;
        EditText phone ;
        EditText password;
        Button readerChangeSave;
        Button ReaderChangeDelet;
    }
}
