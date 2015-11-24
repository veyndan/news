package com.veyndan.news.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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

//        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
//                new int[] { android.R.attr.actionBarSize });
//        int actionBarHeight = (int) styledAttributes.getDimension(0, 0);
//        Log.d(TAG, "Toolbar height: " + pxToDp(actionBarHeight, this)); // 56dp
//        styledAttributes.recycle();

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        Log.d(TAG, "Width: " + pxToDp(metrics.widthPixels, this)); // 360dp
//        Log.d(TAG, "Height: " + pxToDp(metrics.heightPixels, this)); // 640dp
    }

    public static float pxToDp(float px, Context context){
        return px / context.getResources().getDisplayMetrics().density;
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
