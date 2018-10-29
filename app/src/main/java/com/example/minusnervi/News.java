package com.example.minusnervi;

/**
 * Created by Администратор on 29.10.2018.
 */

public class News {

    private String name;
    private String description;
    private Long date;

    public News(){

    }

    public News(String name, String description, Long date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getDate() {
        return date;
    }
}

