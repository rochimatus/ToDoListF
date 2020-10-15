package com.example.todolistf.modul.home;

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


}
