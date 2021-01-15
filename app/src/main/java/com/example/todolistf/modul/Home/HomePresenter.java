package com.example.todolistf.modul.Home;

import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TableHandler;

import java.util.ArrayList;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;
    private final TableHandler tableHandler;



    public HomePresenter(HomeContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {

    }


    @Override
    public void getDataSet() {
        ArrayList<Task> data = tableHandler.readAll();
        view.showData(data);
    }

    @Override
    public void setDoneTask(String id, boolean isChecked) {
        Task chosenTask = (Task) tableHandler.readById(id);

        chosenTask.setFinished(isChecked);
        tableHandler.update(chosenTask);
    }
}
