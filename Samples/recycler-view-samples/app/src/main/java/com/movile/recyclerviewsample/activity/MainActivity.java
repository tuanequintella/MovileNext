package com.movile.recyclerviewsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.movile.recyclerviewsample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        configureContent();
    }

    private void configureContent() {
        Button linearVerticalAction = (Button) findViewById(R.id.main_linear_vertical_recycler_action);
        linearVerticalAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinearVerticalRecyclerActivity.class));
            }
        });

        Button linearHorizontalAction = (Button) findViewById(R.id.main_linear_horizontal_recycler_action);
        linearHorizontalAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinearHorizontalRecyclerActivity.class));
            }
        });

        Button gridAction = (Button) findViewById(R.id.main_grid_recycler_action);
        gridAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GridRecyclerActivity.class));
            }
        });
    }

}
