package com.example.todolistf.modul.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TaskTableHandler;
import com.example.todolistf.modul.CreateTask.CreateTaskActivity;
import com.example.todolistf.modul.ShowTask.ShowTaskActivity;
import com.example.todolistf.utils.RecyclerViewTodoList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View, View.OnClickListener {
    RecyclerView rvTasks;
    TextView tvTitlePage;
    FloatingActionButton fabCreateTask;
    RecyclerViewTodoList mAdapter;
    final String titlePage = "Your To Do List";
    static final int REQUEST_CODE = 901;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this, new TaskTableHandler(getContext()));
        mPresenter.start();

        initView();
        return fragmentView;
    }

    private void initView() {

        tvTitlePage = fragmentView.findViewById(R.id.tvTitlePage);
        tvTitlePage.setText(titlePage);

        fabCreateTask = fragmentView.findViewById(R.id.fabCreateTask);
        fabCreateTask.setOnClickListener(this);

        rvTasks = fragmentView.findViewById(R.id.rvTasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(activity));
        mPresenter.getDataSet();
    }

    public void showData(final ArrayList<Task> data) {
        mAdapter = new RecyclerViewTodoList(data);
        rvTasks.setAdapter(mAdapter);
        RecyclerViewTodoList.setOnItemClickListener(new RecyclerViewTodoList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                redirectToShow(id);
            }

            @Override
            public void onSelected(int position, boolean isChecked) {
                String id = data.get(position).getId();
                System.out.println("checked " + id);
                mPresenter.setDoneTask(id, isChecked);
            }
        });
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectCreateTask() {
        Intent intent = new Intent(activity, CreateTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToShow(String id) {
        Intent intent = new Intent(activity, ShowTaskActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == fabCreateTask.getId()) {
            redirectCreateTask();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDataSet();
    }
}
