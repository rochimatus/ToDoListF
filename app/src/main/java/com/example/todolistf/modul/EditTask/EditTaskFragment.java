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
import com.example.todolistf.modul.Home.HomeActivity;

;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    EditText etTitle;
    EditText etDescription;
    EditText etDate;
    Button btnCancel;
    Button btnUpdate;
    int id;
    static final int REQUEST_CODE = 901;

    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_show, container, false);
        mPresenter = new EditTaskPresenter(this);
        mPresenter.start();

        etTitle = fragmentView.findViewById(R.id.title_field);
        etDescription = fragmentView.findViewById(R.id.description_field);
        etDate = fragmentView.findViewById(R.id.date_field);
        btnCancel = fragmentView.findViewById(R.id.bt_cancel);
        btnUpdate = fragmentView.findViewById(R.id.bt_save);

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
        return fragmentView;
    }

    private void setBtUpdateClick() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        String date = etDate.getText().toString();
        mPresenter.performUpdate(id, title, description, date);
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
}
