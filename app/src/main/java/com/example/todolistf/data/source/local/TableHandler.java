package com.example.todolistf.data.source.local;

import java.util.ArrayList;

public interface TableHandler<T> {
    void create(T t);
    T readById(String id);
    ArrayList<T> readAll();
    void update(T t);
    void delete(T t);
}