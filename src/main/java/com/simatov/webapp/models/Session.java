package com.simatov.webapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    boolean active;
    private int time, type;
    private String  comment;
    private Date sdate;
    private Date fdate;

    @OneToOne(fetch = FetchType.LAZY)
    private Computer computer;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Session() {
    }

    public Session(boolean active, int time, int type, String comment, User user, Computer computer) {
        this.active = active;
        this.time = time;
        this.type = type;
        this.sdate = new Date();
        Timer timer = new Timer(sdate, time);
        this.fdate = timer.getFinishDate();
        this.comment = comment;
        this.user = user;
        this.computer = computer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getComputerId() {
        return this.getComputer().getId();
    }

    public String getComputerName() {
        return this.getComputer().getName();
    }

    public Long getUserId() {
        return this.getUser().getId();
    }

    public String getUserName() {
        return this.getUser().getName();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }
}
