package com.example.todolistf;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class EditActivity extends Activity {
    public void onClickSave(View view) {
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        onClickSave(view);
    }
}
