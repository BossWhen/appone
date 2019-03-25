package com.example.joker.appone.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joker.appone.demo.Book;
import com.example.joker.appone.demo.BorrowBook;
import com.example.joker.appone.demo.Reader;
import com.example.joker.appone.demo.Users;

/*
* 用于修改图书信息界面，监听EditText 输入后的 保存book数据
* */
public class MyTextWatcher implements TextWatcher {
    private EditText editText;
    private Book book;
    private Reader reader;
    private Users users;
    private BorrowBook borrowBook;
    private String property;
    public MyTextWatcher(EditText editText, Book book,String property) {
        this.editText = editText;
        this.book = book;
        this.property = property;
    }
    public MyTextWatcher(EditText editText, Reader reader, String property) {
        this.editText = editText;
        this.reader = reader;
        this.property = property;
    }
    public MyTextWatcher(EditText editText, BorrowBook borrowBook, String property) {
        this.editText = editText;
        this.borrowBook = borrowBook;
        this.property = property;
    }
    public MyTextWatcher(EditText editText, Users users, String property) {
        this.editText = editText;
        this.users = users;
        this.property = property;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (property.equals("bookId")){book.setBookid(editText.getText().toString());}
        if (property.equals("bookName")){book.setBookname(editText.getText().toString());}
        if (property.equals("bookAuthor")){book.setAuthor(editText.getText().toString());}
        if (property.equals("bookReserve")){book.setReserve(editText.getText().toString());}
        if (property.equals("bookUnitprice")){book.setUnitprice(editText.getText().toString());}
        if (property.equals("readerPhone")){reader.setPhone(Integer.parseInt(editText.getText().toString()));}
        if (property.equals("borrowfine")){borrowBook.setFine(editText.getText().toString());}

    }
}
