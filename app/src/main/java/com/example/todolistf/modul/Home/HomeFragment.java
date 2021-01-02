package com.example.todolistf.modul.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.modul.CreateTask.CreateTaskActivity;
import com.example.todolistf.modul.ShowTask.ShowTaskActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View, View.OnClickListener {
    View view;
    TextView tvTitle;
    FloatingActionButton fabCreateTask;
    static final int REQUEST_CODE = 901;

    public HomeFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        view = fragmentView.findViewById(R.id.list1);
        tvTitle = fragmentView.findViewById(R.id.title);
        fabCreateTask = fragmentView.findViewById(R.id.fabCreateTask);
        String text = "Your to do list";
        tvTitle.setText(text);
        view.setOnClickListener(this);
        fabCreateTask.setOnClickListener(this);
        return fragmentView;
    }

    private void changeToCreateActivity() {
        Intent intent = new Intent(activity, CreateTaskActivity.class);
        activity.finish();
        startActivity(intent);
    }

    public void setViewChangeClick(String id){
        redirectToShow(id);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMessage(int status) {
        //proses cek status dan message
        Toast toast = Toast.makeText(activity, R.string.status_toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void redirectToShow(String id) {
        Intent intent = new Intent(activity, ShowTaskActivity.class);
        //kode untuk mengirim id dari to do yang dipilih
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == fabCreateTask.getId()) {
            changeToCreateActivity();
        } else if (v.getId() == view.getId()){
            redirectToShow("0");
        }
    }
}
