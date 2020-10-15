package com.example.todolistf.modul.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.modul.edit.EditActivity;
import com.example.todolistf.modul.home.HomeActivity;

;import java.util.Date;

public class ShowFragment extends BaseFragment<ShowActivity, ShowContract.Presenter> implements ShowContract.View {

    int id;
    Button btnDone;
    Button btnEdit;
    Button btnDelete;
    TextView tvTitle;
    TextView tvDate;
    TextView tvDescription;
    static final int REQUEST_CODE = 901;

    public ShowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_show, container, false);
        mPresenter = new ShowPresenter(this);
        mPresenter.start();

        tvTitle = fragmentView.findViewById(R.id.title);
        tvDescription = fragmentView.findViewById(R.id.tv_description);
        tvDate = fragmentView.findViewById(R.id.tv_datetime);

        btnDone = fragmentView.findViewById(R.id.bt_done);
        btnEdit = fragmentView.findViewById(R.id.bt_edit);
        btnDelete = fragmentView.findViewById(R.id.bt_delete);

        mPresenter.performData(0);

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setBtDoneCLick();
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

        return fragmentView;
    }

    private void setBtDeleteClick() {
        mPresenter.deleteData(id);
    }

    private void setBtEditClick() {
        redirectToEdit(id);
    }

    private void setBtDoneCLick() {
        mPresenter.doneToDo(id);
    }

    @Override
    public void setPresenter(ShowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showData(int id, String title, String description, Date date) {
        this.id = id;
        tvTitle.setText(title);
        tvDate.setText(date.toString());
        tvDescription.setText(description);
    }

    @Override
    public void redirectToHome(int status) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("status", status);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void redirectToEdit(int todoId) {
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra("id", todoId);
        startActivity(intent);
        activity.finish();
    }


}
