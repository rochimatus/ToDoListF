package com.example.todolistf.modul.ShowTask;

import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TableHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public class ShowTaskPresenter implements ShowTaskContract.Presenter{
    private final ShowTaskContract.View view;
    private TableHandler tableHandler;
    private Task task;

    public ShowTaskPresenter(ShowTaskContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {}

    @Override
    public void loadData(String id){
        task = (Task) tableHandler.readById(id);
        view.showData(task);
    }

    @Override
    public void deleteData() {
        tableHandler.delete(task);
    }

    @Override
    public void setFinishTask(boolean isFinished) {
        task.setFinished(isFinished);
        tableHandler.update(task);
    }

    @Override
    public String shareTask() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        StringBuffer sbf = new StringBuffer();
        sbf.append("[" + (task.isFinished() ? "DONE" : "NOT YET") + "]\n");
        sbf.append("Task: " + task.getTitle() + "\n");
        sbf.append(task.getDescription() + "\n");
        sbf.append("Due: " + simpleDateFormat.format(task.getDate()) + "\n");

        return sbf.toString();
    }
}
