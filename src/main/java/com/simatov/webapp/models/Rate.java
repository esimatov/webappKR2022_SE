package com.simatov.webapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String comment;
    private double workingCompPrice;
    private double gamingCompPrice;

    public Rate() {
    }

    public Rate(String name, String comment, double workingCompPrice, double gamingCompPrice) {
        this.name = name;
        this.comment = comment;
        this.workingCompPrice = workingCompPrice;
        this.gamingCompPrice = gamingCompPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getWorkingCompPrice() {
        return workingCompPrice;
    }

    public void setWorkingCompPrice(double workingCompPrice) {
        this.workingCompPrice = workingCompPrice;
    }

    public double getGamingCompPrice() {
        return gamingCompPrice;
    }

    public void setGamingCompPrice(double gamingCompPrice) {
        this.gamingCompPrice = gamingCompPrice;
    }
}


