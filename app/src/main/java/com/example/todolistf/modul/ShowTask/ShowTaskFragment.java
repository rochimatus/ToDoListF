package com.example.todolistf.modul.ShowTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TaskTableHandler;
import com.example.todolistf.modul.EditTask.EditTaskActivity;
import com.example.todolistf.modul.Home.HomeActivity;

;import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowTaskFragment extends BaseFragment<ShowTaskActivity, ShowTaskContract.Presenter> implements ShowTaskContract.View {

    String id;
    Button btnBack;
    Button btnEdit;
    Button btnDelete;
    CheckBox cbFinished;
    TextView tvTitle;
    TextView tvDate;
    TextView tvDescription;
    static final int REQUEST_CODE = 901;

    public ShowTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_show, container, false);
        mPresenter = new ShowTaskPresenter(this, new TaskTableHandler(getActivity()));
        mPresenter.start();

        tvTitle = fragmentView.findViewById(R.id.tvTitle);
        tvDescription = fragmentView.findViewById(R.id.tv_description);
        tvDate = fragmentView.findViewById(R.id.tv_datetime);
        cbFinished = fragmentView.findViewById(R.id.cbFinisihed);

        btnBack = fragmentView.findViewById(R.id.btBack);
        btnEdit = fragmentView.findViewById(R.id.btEdit);
        btnDelete = fragmentView.findViewById(R.id.btDelete);

        id = getActivity().getIntent().getStringExtra("ID");
        mPresenter.loadData(id);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setBtBackCLick();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtEditClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtDeleteClick();
            }
        });
        cbFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.setFinishTask(isChecked);
                redirectToHome(0);
            }
        });
        return fragmentView;
    }

    private void setBtDeleteClick() {
        mPresenter.deleteData();
        activity.finish();
    }

    private void setBtEditClick() {
        redirectToEdit();
    }

    private void setBtBackCLick() {
        mPresenter.setFinishTask(true);
    }

    @Override
    public void setPresenter(ShowTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showData(Task task) {
        tvTitle.setText(task.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tvDate.setText(simpleDateFormat.format(task.getDate()));
        tvDescription.setText(task.getDescription());
        cbFinished.setChecked(task.isFinished());
    }

    @Override
    public void redirectToHome(int status) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("status", status);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void redirectToEdit() {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
        activity.finish();
    }

}
