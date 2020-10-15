package com.example.todolistf.modul.show;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public class ShowPresenter implements ShowContract.Presenter{
    private final ShowContract.View view;



    public ShowPresenter(ShowContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performData(final int todoId){
        //proses query data
        //tampilkan hasil query
        view.showData(todoId, "My Title", "My Description", new Date(2020-9-11));
    }

    @Override
    public void deleteData(int todoId) {
        //proses delete

        final int deleted = 9999;
        view.redirectToHome(deleted);
    }

    @Override
    public void doneToDo(int todoId) {
        //proses update to do list

        final int updated = 1001;
        view.redirectToHome(updated);
    }

}
