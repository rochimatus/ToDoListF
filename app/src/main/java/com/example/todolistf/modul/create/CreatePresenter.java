package com.example.todolistf.modul.create;

/**
 * Created by fahrul on 13/03/19.
 */

public class CreatePresenter implements CreateContract.Presenter{
    private final CreateContract.View view;



    public CreatePresenter(CreateContract.View view) {
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
