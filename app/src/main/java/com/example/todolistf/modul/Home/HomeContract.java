package com.example.todolistf.modul.Home;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;
import com.example.todolistf.data.model.Task;

import java.util.ArrayList;

/**
 * Created by fahrul on 13/03/19.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showMessage(int status);
        void redirectToShow(String id);
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
        void setDoneTask(String id);
    }
}
