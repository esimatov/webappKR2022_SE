package com.simatov.webapp.models;

public enum Type {
    GAMING ("Игровой"),
    WORKING ("Рабочий");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type='" + type + '\'' +
                '}';
    }
}
