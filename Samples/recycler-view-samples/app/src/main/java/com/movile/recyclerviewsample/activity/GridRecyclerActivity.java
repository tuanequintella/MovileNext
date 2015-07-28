package com.movile.recyclerviewsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.movile.recyclerviewsample.R;
import com.movile.recyclerviewsample.adapter.RecyclerAdapter;
import com.movile.recyclerviewsample.model.Content;

import java.util.ArrayList;
import java.util.List;

public class GridRecyclerActivity extends AppCompatActivity {

    private static final int NUMBER_OF_COLUMNS = 3;

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
        view.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));

        mAdapter = new RecyclerAdapter(this, R.layout.content_grid_item);
        view.setAdapter(mAdapter);
    }

    private void populateRecyclerView() {
        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            contents.add(new Content("title " + i, "description " + i));
        }
        mAdapter.updateContents(contents);
    }

}
