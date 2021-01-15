package com.example.todolistf.base;

import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

//import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;

import com.example.todolistf.R;


public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btOptionMenu;
    protected ImageView ivIcon;
    protected ImageButton btBack;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;
    protected Toolbar tbDefault;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        btOptionMenu = (ImageButton) findViewById(R.id.btOptionMenu);
        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        btBack = (ImageButton) findViewById(R.id.btBack);
        vMenuBarShadow = findViewById(R.id.vMenuBarShadow);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
        tbDefault = (Toolbar) findViewById(R.id.my_toolbar);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }


}
