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
import android.widget.TextView;
import android.widget.Toast;

import com.example.joker.appone.MyDatabaseHelper;
import com.example.joker.appone.R;
import com.example.joker.appone.demo.BorrowBook;
import com.example.joker.appone.tools.AmountEditText;
import com.example.joker.appone.tools.MyTextWatcher;

import java.util.List;

public class BorrowBookUserAdapter extends ArrayAdapter<BorrowBook> {
    private int resourceId;
    private ViewHolder holder;
    public BorrowBookUserAdapter(Context context, int textViewResourceId, List<BorrowBook> objects){
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
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.bookid = (TextView)convertView.findViewById(R.id.borrow_bookid_user);
        holder.readerid = (TextView)convertView.findViewById(R.id.borrow_readerid_user);
        holder.borrowid = (TextView)convertView.findViewById(R.id.borrow_borrowid_user);
        holder.borrowdate = (TextView)convertView.findViewById(R.id.borrow_borrowdate_user);
        holder.returndate = (TextView)convertView.findViewById(R.id.borrow_returndate_user);
        holder.fine = (TextView)convertView.findViewById(R.id.borrow_fine_user);
        holder.bookid.setText(borrowBook.getBookid()+"");
        holder.readerid.setText(borrowBook.getReaderid()+"");
        holder.borrowid.setText(borrowBook.getBorrowid()+"");
        holder.borrowdate.setText(borrowBook.getBorrowdate());
        holder.returndate.setText(borrowBook.getReturndate());
        holder.fine.setText(borrowBook.getFine());
        return convertView;
    }

    class ViewHolder{
        TextView readerid;
        TextView borrowid;
        TextView bookid;
        TextView borrowdate;
        TextView returndate;
        TextView fine;
    }
}
