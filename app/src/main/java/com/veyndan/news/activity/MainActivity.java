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
        getSupportActionBar().setTitle("Facebook");

        // 25dp StatusBarHeight
        // 56dp ActionBarHeight
        // 360dp:640dp 9:16 ScreenWidth:ScreenHeight DONE
        // 3:4 Article:Category DONE
        // 6:19 Profile:Image DONE
        // 5 Article max lines
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
