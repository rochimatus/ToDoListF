package com.example.todolistf.modul.EditTask;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;
import com.example.todolistf.data.model.Task;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome(int status);
        void showData(Task task);
        void setId(String id);
    }

    interface Presenter extends BasePresenter {
        void loadData(String id);
        void updateData(String id, String title, String description, Date date);
    }
}
