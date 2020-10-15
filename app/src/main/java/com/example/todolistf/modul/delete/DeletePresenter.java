package com.example.todolistf.modul.delete;

/**
 * Created by fahrul on 13/03/19.
 */

public class DeletePresenter implements DeleteContract.Presenter{
    private final DeleteContract.View view;



    public DeletePresenter(DeleteContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(final String email, final String password){
        //proses login
        //if login success call redirect to profile
        view.redirectToProfile(email, password);
    }

}
