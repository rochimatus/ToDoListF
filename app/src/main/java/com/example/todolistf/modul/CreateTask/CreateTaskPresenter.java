package com.example.todolistf.modul.CreateTask;

import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TableHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public class CreateTaskPresenter implements CreateTaskContract.Presenter{
    private final CreateTaskContract.View view;
    private final TableHandler tableHandler;



    public CreateTaskPresenter(CreateTaskContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(String title, String description, Date date) {
        //storing data to db
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Task task = new Task(title, date, description, false);
        tableHandler.create(task);
        view.redirectToHome(0);
    }

}
