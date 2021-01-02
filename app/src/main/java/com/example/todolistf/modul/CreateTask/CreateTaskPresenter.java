package com.example.todolistf.modul.CreateTask;

/**
 * Created by fahrul on 13/03/19.
 */

public class CreateTaskPresenter implements CreateTaskContract.Presenter{
    private final CreateTaskContract.View view;



    public CreateTaskPresenter(CreateTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performStore(String title, String description, String date) {
        //storing data to db
        view.redirectToHome(0);
    }
}
