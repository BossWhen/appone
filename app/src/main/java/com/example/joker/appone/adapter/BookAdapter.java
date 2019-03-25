package com.example.joker.appone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joker.appone.R;
import com.example.joker.appone.demo.Book;

import java.util.List;
/*
 * 书籍信息查找适配器
 * */
public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    private ViewHolder holder;
    public BookAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context,textViewResourceId,objects);
        resourceId =textViewResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.booId = (TextView)convertView.findViewById(R.id.book_id);
        holder.bookName = (TextView)convertView.findViewById(R.id.book_name);
        holder.bookAuthor = (TextView)convertView.findViewById(R.id.book_author);
        holder.bookUnitprice = (TextView)convertView.findViewById(R.id.book_unitprice);
        holder.bookReserve = (TextView)convertView.findViewById(R.id.book_pricture);
        holder.booId.setText(book.getBookid()+"");
        holder.bookName.setText(book.getBookname());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookUnitprice.setText(book.getUnitprice());
        holder.bookReserve.setText(book.getReserve()+"");
        return convertView;
    }
    class ViewHolder{
        TextView booId;
        TextView bookName;
        TextView bookAuthor;
        TextView bookUnitprice;
        TextView bookReserve ;
    }
}
