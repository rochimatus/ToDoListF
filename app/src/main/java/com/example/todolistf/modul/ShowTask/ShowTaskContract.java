package com.example.todolistf.modul.ShowTask;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;
import com.example.todolistf.data.model.Task;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public interface ShowTaskContract {
    interface View extends BaseView<Presenter> {
        void showData(Task task);
        void redirectToHome(int status);
        void redirectToEdit();
    }

    interface Presenter extends BasePresenter {
        void loadData(String id);
        void deleteData();
        void setFinishTask(boolean isFinished);
    }
}
