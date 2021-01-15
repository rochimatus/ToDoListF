package com.example.todolistf.modul.EditTask;

import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TableHandler;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
    private final TableHandler tableHandler;
    private Task task;


    public EditTaskPresenter(EditTaskContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {

    }

    public void loadData(String id) {
        task = (Task) tableHandler.readById(id);
        view.showData(task);
    }

    @Override
    public void updateData(String id, String title, String description, Date date) {
        //proses update data
        //if login success call redirect to profile
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);

        tableHandler.update(task);
        view.redirectToHome(0);
    }
}
