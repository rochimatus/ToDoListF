package com.example.todolistf.modul.EditTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.model.Task;
import com.example.todolistf.data.source.local.TaskTableHandler;
import com.example.todolistf.modul.Home.HomeActivity;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    EditText etTitle;
    EditText etDescription;
    EditText etDate;
    Button btnCancel;
    Button btnUpdate;
    SimpleDateFormat simpleDateFormat;
    String id;
    static final int REQUEST_CODE = 901;

    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit, container, false);
        mPresenter = new EditTaskPresenter(this, new TaskTableHandler(getContext()));
        mPresenter.start();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        etTitle = fragmentView.findViewById(R.id.etToDo);
        etDescription = fragmentView.findViewById(R.id.etDescription);
        etDate = fragmentView.findViewById(R.id.etDate);
        btnCancel = fragmentView.findViewById(R.id.btCancel);
        btnUpdate = fragmentView.findViewById(R.id.btSave);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtCancelClick();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtUpdateClick();
            }
        });
        id = getActivity().getIntent().getStringExtra("ID");
        mPresenter.loadData(id);
        return fragmentView;
    }

    private void setBtUpdateClick() {
        try {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            Date date = simpleDateFormat.parse(etDate.getText().toString());
            //boolean isFinished =
            mPresenter.updateData(id, title, description, date);
        } catch(Exception e) {
            System.out.println(e.getCause());
        }

    }

    public void setBtCancelClick(){
        redirectToHome(0);
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome(int status) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("status", status);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showData(Task task) {
        etTitle.setText(task.getTitle());
        etDescription.setText(task.getDescription());
        etDate.setText(simpleDateFormat.format(task.getDate()));
    }

    @Override
    public void setId(String id) {

    }
}
