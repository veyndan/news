package com.veyndan.news.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArticleFormat {
    private static final String TAG = LogUtils.makeLogTag(ArticleFormat.class);

    public static String time(String pubDate) {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        String timeFormatted;
        try {
            Date date = dateFormat.parse(pubDate);

            long currentTime = System.currentTimeMillis();
            int minDiff = (int) ((currentTime - date.getTime()) / (1000 * 60));
            if (minDiff < 60) {
                timeFormatted = minDiff + " mins";
            } else {
                int hourDiff = minDiff / 24;
                if (hourDiff < 24) {
                    timeFormatted = hourDiff + " hrs";
                } else {
                    SimpleDateFormat outFormat =
                            new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                    timeFormatted = outFormat.format(date);
                }
            }
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
            timeFormatted = pubDate;
        }
        return timeFormatted;
    }
}
