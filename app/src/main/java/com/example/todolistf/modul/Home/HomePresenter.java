package com.example.todolistf.modul.Home;

import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.model.User;
import com.example.todolistf.data.source.local.TableHandler;
import com.example.todolistf.data.source.session.SessionRepository;
import com.example.todolistf.data.source.session.UserSessionRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;
    private final TableHandler tableHandler;
    private final SessionRepository sessionRepository;
    private final FirebaseAuth mAuth;

    public HomePresenter(HomeContract.View view, TableHandler tableHandler, SessionRepository sessionRepository) {
        this.view = view;
        this.tableHandler = tableHandler;
        this.sessionRepository = sessionRepository;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {
        String email = ((User)sessionRepository.getSessionData()).getEmail();
        view.setEmail(email);
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

    @Override
    public void performLogout() {
        sessionRepository.destroy();
        mAuth.signOut();
        view.redirectLogin();
    }
}
