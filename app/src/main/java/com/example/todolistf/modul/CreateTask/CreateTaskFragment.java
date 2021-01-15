package com.example.todolistf.modul.CreateTask;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.source.local.TaskTableHandler;
import com.example.todolistf.modul.Home.HomeActivity;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by fahrul on 13/03/19.
 */

public class CreateTaskFragment extends BaseFragment<CreateTaskActivity, CreateTaskContract.Presenter> implements CreateTaskContract.View, View.OnClickListener{
    EditText etTitle;
    EditText etDescription;
    EditText etDate;
    Button btnCancel;
    Button btnSave;
    SimpleDateFormat simpleDateFormat;
    //Date date;
    DatePickerDialog.OnDateSetListener dateSetListener;

    static final int REQUEST_CODE = 901;

    public CreateTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_create, container, false);
        mPresenter = new CreateTaskPresenter(this, new TaskTableHandler(getContext()));
        mPresenter.start();

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        etTitle = fragmentView.findViewById(R.id.etToDo);
        etDescription = fragmentView.findViewById(R.id.etDescription);
        etDate = fragmentView.findViewById(R.id.etDate);

        btnCancel = fragmentView.findViewById(R.id.btCancel);
        btnSave = fragmentView.findViewById(R.id.btSave);
        etDate.setOnClickListener(this);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //date = new Date(year, month, dayOfMonth);
                String dates = simpleDateFormat.format(calendar.getTime());
                        //DateFormat.getDateInstance().format(calendar.getTime());
                etDate.setText(dates);
            }
        };
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return fragmentView;
    }


    public void setBtSaveClick(){
        try {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            Date date = simpleDateFormat.parse(etDate.getText().toString());
            mPresenter.saveData(title, description, date);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
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

    @Override
    public void onClick(View v) {
        if(v.getId() == btnCancel.getId())
            redirectToHome(0);
        else if(v.getId() == btnSave.getId())
            setBtSaveClick();
        else if(v.getId() == etDate.getId())
            showDatePicker();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog, dateSetListener, year, month, day);

        pickerDialog.show();
    }

}
