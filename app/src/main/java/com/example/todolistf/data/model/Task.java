package com.example.todolistf.data.model;

import com.example.todolistf.base.BaseModel;

import java.util.Date;

public class Task extends BaseModel {
    private String id;
    private String title;
    private Date date;
    private String description;
    private boolean isFinished;

    public Task(String id, String title, Date date, String description, boolean isFinished) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.isFinished = isFinished;
    }

    public Task(String title, Date date, String description, boolean isFinished) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.isFinished = isFinished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
