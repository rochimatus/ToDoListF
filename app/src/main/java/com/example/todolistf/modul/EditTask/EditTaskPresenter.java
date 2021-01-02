package com.example.todolistf.modul.EditTask;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;



    public EditTaskPresenter(EditTaskContract.View view) {
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
