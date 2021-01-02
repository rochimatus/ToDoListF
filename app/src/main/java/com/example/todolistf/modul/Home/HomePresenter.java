package com.example.todolistf.modul.Home;

import com.example.todolistf.data.model.Task;

import java.util.ArrayList;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;



    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}


    @Override
    public ArrayList<Task> getDataSet() {
        return null;
    }

    @Override
    public void setDoneTask(String id) {

    }
}
