package com.example.joker.appone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.joker.appone.RootMainActivity;
import com.example.joker.appone.demo.Book;
import com.example.joker.appone.tools.MyTextWatcher;

import java.util.List;
/*
 * 书籍信息修改适配器
 * */
public class BookChangeAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    private ViewHolder holder;
    private MyDatabaseHelper dbHelper;
    public BookChangeAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context,textViewResourceId,objects);
        resourceId =textViewResourceId;
    }

    @Nullable
    @Override
    public Book getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Book book = getItem(position);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.bookId = (EditText)convertView.findViewById(R.id.book_id_change);
        holder.bookName = (EditText)convertView.findViewById(R.id.book_name_change);
        holder.bookAuthor = (EditText)convertView.findViewById(R.id.book_author_change);
        holder.bookUnitprice = (EditText)convertView.findViewById(R.id.book_unitprice_change);
        holder.bookReserve = (EditText)convertView.findViewById(R.id.book_pricture_change);
        holder.bookDelet = (Button) convertView.findViewById(R.id.book_change_delet);
        holder.bookSave = (Button) convertView.findViewById(R.id.book_change_save);
        holder.bookId.setText(book.getBookid()+"");
        holder.bookName.setText(book.getBookname());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookUnitprice.setText(book.getUnitprice());
        holder.bookReserve.setText(book.getReserve());
        holder.bookId.addTextChangedListener(new MyTextWatcher(holder.bookId,book,"bookId"));
        holder.bookName.addTextChangedListener(new MyTextWatcher(holder.bookName,book,"bookName"));
        holder.bookAuthor.addTextChangedListener(new MyTextWatcher(holder.bookAuthor,book,"bookAuthor"));
        holder.bookUnitprice.addTextChangedListener(new MyTextWatcher(holder.bookId,book,"bookUnitprice"));
        holder.bookReserve.addTextChangedListener(new MyTextWatcher(holder.bookId,book,"bookReserve"));
        final View finalConvertViewSave = convertView;
        holder.bookSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.bookId = (EditText)finalConvertViewSave.findViewById(R.id.book_id_change);
                holder.bookName = (EditText)finalConvertViewSave.findViewById(R.id.book_name_change);
                holder.bookAuthor = (EditText)finalConvertViewSave.findViewById(R.id.book_author_change);
                holder.bookUnitprice = (EditText)finalConvertViewSave.findViewById(R.id.book_unitprice_change);
                holder.bookReserve = (EditText)finalConvertViewSave.findViewById(R.id.book_pricture_change);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                int roll = 0;
                Cursor cursor = db.query("book",null,null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String  bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                        String  bookAddId1 = cursor.getString(cursor.getColumnIndex("bookid"));
                        if (holder.bookName.getText().toString().equalsIgnoreCase(bookname)){roll =1;}
                        if (holder.bookId.getText().toString().equalsIgnoreCase(bookAddId1)){roll =2;}
                    }while (cursor.moveToNext());
                }
                ContentValues contentValues = new ContentValues();
                if (roll == 0 ){
                    contentValues.put("bookid",holder.bookId.getText().toString());
                    contentValues.put("bookname",holder.bookName.getText().toString());
                    book.setBookid(holder.bookId.getText().toString());
                    book.setBookname(holder.bookName.getText().toString());
                }
                if (!holder.bookAuthor.getText().toString().equalsIgnoreCase(book.getAuthor())){
                    contentValues.put("autor",holder.bookAuthor.getText().toString());
                    book.setAuthor(holder.bookAuthor.getText().toString());
                    roll = 0;
                }
                if (!holder.bookUnitprice.getText().toString().equalsIgnoreCase(book.getUnitprice())){
                    contentValues.put("unitprice",holder.bookUnitprice.getText().toString());
                    book.setUnitprice(holder.bookUnitprice.getText().toString());
                    roll = 0;
                }
                if (!holder.bookReserve.getText().toString().equalsIgnoreCase(book.getReserve())){
                    contentValues.put("reserve",holder.bookReserve.getText().toString());
                    book.setReserve(holder.bookReserve.getText().toString());
                    roll = 0;
                }
                if (!(contentValues.size() == 0)){
                    db.update("book",contentValues,"id = ?",new String[]{book.getId()});
                    if (roll == 0){Toast.makeText(view.getContext(),"保存成功",Toast.LENGTH_SHORT).show();}
                    if (roll == 1){Toast.makeText(view.getContext(),"书名:"+holder.bookName.getText()+"已经存在!!!",Toast.LENGTH_SHORT).show();}
                    if (roll == 2){Toast.makeText(view.getContext(),"书的编号:"+holder.bookId.getText()+"已经存在!!!",Toast.LENGTH_SHORT).show();}
                }
            }
        });
        final View finalConvertView = convertView;
        holder.bookDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView info_delet =(TextView) finalConvertView.findViewById(R.id.info_delet);
                new AlertDialog.Builder(view.getContext()).setTitle("确认删除吗？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("book","bookname = ?",new String[]{book.getBookname()});
                                info_delet.setText("已经删除当前书籍，请刷新列表");
                                Toast.makeText(getContext(),holder.bookId.getText(),Toast.LENGTH_SHORT).show();
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
        EditText bookId;
        EditText bookName;
        EditText bookAuthor;
        EditText bookUnitprice;
        EditText bookReserve ;
        Button bookDelet;
        Button bookSave;
    }
}
