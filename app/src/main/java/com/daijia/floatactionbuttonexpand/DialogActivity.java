package com.daijia.floatactionbuttonexpand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daijia.library.FABBaseDialog;

/**
 * Created by flb on 16/6/16.
 */
public class DialogActivity extends AppCompatActivity {

    FABBaseDialog fabBaseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fabBaseDialog = new FABBaseDialog(this,fab,R.layout.dialog);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabBaseDialog.showAsDialog();
            }
        });


    }

}
