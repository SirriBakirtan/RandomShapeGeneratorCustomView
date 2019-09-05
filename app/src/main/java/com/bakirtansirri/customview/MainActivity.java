package com.bakirtansirri.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bakirtansirri.customview.view.CustomView;

public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView;
    private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView=(CustomView) findViewById(R.id.customView);

        findViewById(R.id.btn_swap_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.swapColor();
            }
        });

        findViewById(R.id.btn_changeLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.changeLocation();
            }
        });

        findViewById(R.id.btn_changeArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.changeArea();
            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.doStart();
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.doStop();
            }
        });
        findViewById(R.id.btn_changeShapes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomView.changeShapes();
            }
        });
    }
}
