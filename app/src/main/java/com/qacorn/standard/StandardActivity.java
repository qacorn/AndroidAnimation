package com.qacorn.standard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * StandardActivity
 *  @author qacorn
 *  created at 2016/4/27 15:15
 */
public abstract class StandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeData();
        registerClickListener();
    }

    protected abstract void initializeView();

    protected abstract void initializeData();

    protected abstract void registerClickListener();
}
