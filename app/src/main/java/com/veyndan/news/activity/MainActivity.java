package com.veyndan.news.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.veyndan.news.R;
import com.veyndan.news.util.LogUtils;

public class MainActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Technology");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
