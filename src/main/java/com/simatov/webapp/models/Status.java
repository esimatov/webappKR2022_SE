package com.simatov.webapp.models;

public enum Status {

    FREE ("Свободен"),
    BUSY ("Занят"),
    FIX ("В обслуживании");

    private String title;

    Status(String s) {
        this.title = s;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Status{" +
                "title='" + title + '\'' +
                '}';
    }
}
