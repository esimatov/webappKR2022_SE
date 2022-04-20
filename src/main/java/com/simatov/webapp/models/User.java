package com.simatov.webapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, tel;
    private double balance;
    private String regDate;

    private int workingCompTime;
    private int gamingCompTime;

    public User() {
    }

    public User(String name, String tel, double balance, int workingCompTime, int gamingCompTime) {
        this.name = name;
        this.tel = tel;
        this.balance = balance;
        this.workingCompTime = workingCompTime;
        this.gamingCompTime = gamingCompTime;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.regDate = simpleDateFormat.format(calendar.getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getWorkingCompTime() {
        return workingCompTime;
    }

    public void setWorkingCompTime(int workingCompTime) {
        this.workingCompTime = workingCompTime;
    }

    public int getGamingCompTime() {
        return gamingCompTime;
    }

    public void setGamingCompTime(int gamingCompTime) {
        this.gamingCompTime = gamingCompTime;
    }
}
