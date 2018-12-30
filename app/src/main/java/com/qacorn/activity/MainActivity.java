package com.qacorn.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qacorn.R;
import com.qacorn.adapter.AnimationCategoryAdapter;
import com.qacorn.standard.OnItemClickListener;
import com.qacorn.standard.StandardActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends StandardActivity {

    private List<String> animationList = new ArrayList<>();
    private RecyclerView animation_recycler;
    private AnimationCategoryAdapter animationCategoryAdapter;

    @Override
    protected void initializeView() {

        setContentView(R.layout.activity_main);
        animation_recycler = findViewById(R.id.animation_recycler);
    }


    @Override
    protected void initializeData() {
        animationList.add("SplashActivity");
        animationList.add("MusicPlayActivity");

        animation_recycler.setLayoutManager(new LinearLayoutManager(this));
        animationCategoryAdapter = new AnimationCategoryAdapter(this,animationList);
        animation_recycler.setAdapter(animationCategoryAdapter);
    }

    @Override
    protected void registerClickListener() {
        animationCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this,SplashActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,MusicPlayActivity.class));
                        break;
                }
            }
        });
    }
}
