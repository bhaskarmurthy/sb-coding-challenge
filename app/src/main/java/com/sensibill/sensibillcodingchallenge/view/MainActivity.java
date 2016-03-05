package com.sensibill.sensibillcodingchallenge.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sensibill.sensibillcodingchallenge.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ReceiptsFragment.newInstance(getResources().getInteger(R.integer.column_count)))
                    .commit();
        }
    }
}
