package com.example.joker.appone.demo;

public class BorrowBook {
    int readerid;
    int borrowid;
    int bookid;
    String borrowdate;
    String returndate;
    String fine;

    public BorrowBook(int readerid, int borrowid, int bookid, String borrowdate, String returndate, String fine) {
        this.readerid = readerid;
        this.borrowid = borrowid;
        this.bookid = bookid;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.fine = fine;
    }

    public int getReaderid() {
        return readerid;
    }

    public void setReaderid(int readerid) {
        this.readerid = readerid;
    }

    public int getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(int borrowid) {
        this.borrowid = borrowid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(String borrowdate) {
        this.borrowdate = borrowdate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}

