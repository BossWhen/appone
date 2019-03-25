package com.example.joker.appone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.joker.appone.demo.BorrowBook;
import com.example.joker.appone.tools.AmountEditText;
import com.example.joker.appone.tools.MyTextWatcher;

import java.util.List;

public class BorrowBookChangeAdapter extends ArrayAdapter<BorrowBook> {
    private int resourceId;
    private ViewHolder holder;
    private MyDatabaseHelper dbHelper;
    public BorrowBookChangeAdapter(Context context, int textViewResourceId, List<BorrowBook> objects){
        super(context,textViewResourceId,objects);
        resourceId =textViewResourceId;
    }
    @Nullable
    @Override
    public BorrowBook getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final BorrowBook borrowBook = getItem(position);
        dbHelper = new MyDatabaseHelper(getContext(),"BMS.db",null,1);
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.bookid = (TextView)convertView.findViewById(R.id.borrow_bookid);
        holder.readerid = (TextView)convertView.findViewById(R.id.borrow_readerid);
        holder.borrowid = (TextView)convertView.findViewById(R.id.borrow_borrowid);
        holder.borrowdate = (TextView)convertView.findViewById(R.id.borrow_borrowdate);
        holder.returndate = (TextView)convertView.findViewById(R.id.borrow_returndate);
        holder.fine = (AmountEditText)convertView.findViewById(R.id.borrow_fine);
        holder.save = (Button) convertView.findViewById(R.id.borrow_change_save);
        holder.bookid.setText(borrowBook.getBookid()+"");
        holder.readerid.setText(borrowBook.getReaderid()+"");
        holder.borrowid.setText(borrowBook.getBorrowid()+"");
        holder.borrowdate.setText(borrowBook.getBorrowdate());
        holder.returndate.setText(borrowBook.getReturndate());
        holder.fine.setText(borrowBook.getFine());
        holder.fine.addTextChangedListener(new MyTextWatcher(holder.fine,borrowBook,"borrowfine"));
        final View finalConvertViewSave = convertView;
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                holder.fine = (AmountEditText)finalConvertViewSave.findViewById(R.id.borrow_fine);
                if (!holder.fine.isConformRules()){
                    Toast.makeText(finalConvertViewSave.getContext(),"价格格式不对！！！",Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("fine",Integer.parseInt(holder.fine.getText().toString()));
                    db.update("borrowbook",contentValues,"borrowid = ?",new String []{borrowBook.getBorrowid()+""});
                    contentValues.clear();
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView readerid;
        TextView borrowid;
        TextView bookid;
        TextView borrowdate;
        TextView returndate;
        AmountEditText fine;
        Button save;
    }
}
