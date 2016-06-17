package com.daijia.floatactionbuttonexpand;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daijia.library.FABBaseDialog;

/**
 * Created by flb on 16/6/16.
 */
public class ToolBarActivity extends AppCompatActivity {

    FABBaseDialog fabBaseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fabBaseDialog = new FABBaseDialog(this,fab,R.layout.toolbar);
        fabBaseDialog.setOnClickListener(R.id.image, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabBaseDialog.dismiss();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabBaseDialog.showAsToolBar();
            }
        });


    }

}
