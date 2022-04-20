package com.simatov.webapp.models;


import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Timer {

    private Date startDate;
    private int time;
    private Date finishDate;

    public Timer(Date date, int time) {
        this.startDate = date;
        this.time = time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.roll(Calendar.MINUTE, time);
        this.finishDate = calendar.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean active(Date date) {
        if (this.finishDate.after(date)) return true;
        else return false;
    }

    public static void main(String[] args) {
        Timer timer = new Timer(new Date(),1);
        System.out.println(timer.getStartDate());
        System.out.println(timer.getFinishDate());

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 1) {
            System.out.println(timer.active(new Date()));
        }
    }
}
