package com.example.todolistf.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.modul.show.ShowActivity;

;

/**
 * Created by fahrul on 13/03/19.
 */

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    View view;
    TextView tvTitle;
    static final int REQUEST_CODE = 901;

    public HomeFragment(int status) {
        showMessage(status);
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
        tvTitle.setText("Your To Do List");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewChangeClick();
            }
        });

        return fragmentView;
    }

    public void setViewChangeClick(){
        redirectToShow();
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
    public void redirectToShow() {
        Intent intent = new Intent(activity, ShowActivity.class);
        //kode untuk mengirim id dari to do yang dipilih
        startActivity(intent);
        activity.finish();
    }


}
