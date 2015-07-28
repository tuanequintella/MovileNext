package com.movile.recyclerviewsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.movile.recyclerviewsample.R;
import com.movile.recyclerviewsample.adapter.RecyclerAdapter;
import com.movile.recyclerviewsample.model.Content;

import java.util.ArrayList;
import java.util.List;

public class LinearVerticalRecyclerActivity extends AppCompatActivity {

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_recycler_activity);

        configureRecyclerView();
        populateRecyclerView();
    }

    private void configureRecyclerView() {
        RecyclerView view = (RecyclerView) findViewById(R.id.vertical_recycler);
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new RecyclerAdapter(this, R.layout.content_linear_vertical_item);
        view.setAdapter(mAdapter);
    }

    private void populateRecyclerView() {
        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            contents.add(new Content("title " + i, "description " + i));
        }
        mAdapter.updateContents(contents);
    }

}
