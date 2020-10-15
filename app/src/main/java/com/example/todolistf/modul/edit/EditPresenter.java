package com.example.todolistf.modul.edit;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditPresenter implements EditContract.Presenter{
    private final EditContract.View view;



    public EditPresenter(EditContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performUpdate(int id, String title, String description, String date) {
        //proses update data
        //if login success call redirect to profile
        view.redirectToHome(0);
    }
}
