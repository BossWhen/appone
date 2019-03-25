package com.example.joker.appone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joker.appone.R;
import com.example.joker.appone.demo.Reader;

import java.util.List;

public class ReaderAdapter extends ArrayAdapter<Reader> {
    private int resourceId;
    private ViewHolder holder;
    public ReaderAdapter(Context context, int textViewResourceId, List<Reader> objects){
        super(context,textViewResourceId,objects);
        resourceId =textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Reader reader = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        holder = new ViewHolder();
        holder.readerId = (TextView)convertView.findViewById(R.id.reader_id);
        holder.name = (TextView)convertView.findViewById(R.id.reader_name);
        holder.age = (TextView)convertView.findViewById(R.id.reader_age);
        holder.sex = (TextView)convertView.findViewById(R.id.reader_sex);
        holder.phone = (TextView)convertView.findViewById(R.id.reader_phone);
        holder.readerId.setText(reader.getReaderid()+"");
        holder.name.setText(reader.getName());
        holder.age.setText(reader.getAge()+"");
        holder.sex.setText(reader.getSex());
        holder.phone.setText(reader.getPhone()+"");
        return convertView;
    }
    class ViewHolder{
        TextView readerId;
        TextView name;
        TextView age;
        TextView sex;
        TextView phone ;
    }
}
