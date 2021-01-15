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
        void redirectCreateTask();
        void redirectToShow(String id);
        void redirectLogin();
        void showData(ArrayList<Task> data);
        void performLogout();
        void setEmail(String email);
    }

    interface Presenter extends BasePresenter {
        void getDataSet();
        void setDoneTask(String id, boolean isChecked);
        void performLogout();
    }
}
