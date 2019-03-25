package com.example.joker.appone.demo;

public class Book {
    private String id;
    private String bookid;
    private int typeid;
    private String bookname;
    private String author;
    private String pulish;
    private String unitprice;
    private String pricture;
    private String publishdate;
    private String des;
    private String reserve;
    public Book(){}
    public Book(String id,String bookid,int typeid,String bookname,String author,String pulish,String unitprice,String pricture,String publishdate,String des,String reserve){
        this.id = id;
        this.bookid = bookid;
        this.typeid = typeid;
        this.bookname = bookname;
        this.author = author;
        this.pulish = pulish;
        this.unitprice = unitprice;
        this.pricture = pricture;
        this.publishdate = publishdate;
        this.des = des;
        this.reserve = reserve;
    }
    public String getPublishdate() {
        return publishdate;
    }
    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getTypeid() {
        return typeid;
    }
    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPulish() {
        return pulish;
    }
    public void setPulish(String pulish) {
        this.pulish = pulish;
    }
    public String getUnitprice() {
        return unitprice;
    }
    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }
    public String getPricture() {
        return pricture;
    }
    public void setPricture(String pricture) {
        this.pricture = pricture;
    }
    public String getBookid() {return bookid;}
    public void setBookid(String bookid) {this.bookid = bookid; }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
