package com.example.joker.appone.demo;

public class Reader {
    int readerid;
    String name;
    int age;
    String sex;
    int phone;

    public Reader(int readerid, String name, int age, String sex, int phone) {
        this.readerid = readerid;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public int getReaderid() {
        return readerid;
    }

    public void setReaderid(int readerid) {
        this.readerid = readerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
