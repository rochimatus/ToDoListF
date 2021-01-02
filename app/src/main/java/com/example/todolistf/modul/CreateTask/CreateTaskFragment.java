package com.example.todolistf.modul.CreateTask;

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

public class CreateTaskFragment extends BaseFragment<CreateTaskActivity, CreateTaskContract.Presenter> implements CreateTaskContract.View {
    EditText etTitle;
    EditText etDescription;
    EditText etDate;
    Button btnCancel;
    Button btnSave;

    Button btnLogin;
    static final int REQUEST_CODE = 901;

    public CreateTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_show, container, false);
        mPresenter = new CreateTaskPresenter(this);
        mPresenter.start();

        etTitle = fragmentView.findViewById(R.id.title_field);
        etDescription = fragmentView.findViewById(R.id.description_field);
        etDate = fragmentView.findViewById(R.id.date_field);

        btnCancel = fragmentView.findViewById(R.id.cancel_buttton);
        btnSave = fragmentView.findViewById(R.id.save_button);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtCancelClick();
            }
        });
        return fragmentView;
    }

    private void setBtCancelClick() {
        redirectToHome(0);
    }

    public void setBtSaveClick(){
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        String date = etDate.getText().toString();

        mPresenter.performStore(title, description, date);
    }

    @Override
    public void redirectToHome(int status) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("status", status);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(CreateTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
