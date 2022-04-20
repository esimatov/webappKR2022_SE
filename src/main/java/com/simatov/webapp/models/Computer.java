package com.simatov.webapp.models;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, description, comment;

    private Status status;
    private Type type;

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "computer_game",
            joinColumns = @JoinColumn(name = "computer_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games=new ArrayList<>();

    public void addGame(Game game) {
        this.games.add(game);
        game.getComputers().add(this);
    }

    public void removeGame(Game game) {
        this.games.remove(game);
        game.getComputers().remove(this);
    }

    public Computer() {
    }

    public Computer(String name, Type type, String description, String comment) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.comment = comment;
        this.status = Status.FREE;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getStrStatus() {
        return status.getTitle();
    }
    public String getStrType() {
        return type.getType();
    }
}
