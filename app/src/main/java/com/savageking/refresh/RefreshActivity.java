package com.savageking.refresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_container);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, RefreshFragment.getInstance(), RefreshFragment.getInstanceTag())
                    .commit();
        }
    }
}
