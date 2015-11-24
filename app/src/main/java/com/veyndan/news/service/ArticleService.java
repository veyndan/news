//package com.veyndan.news.service;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.util.Log;
//
//import com.veyndan.news.model.Feed;
//import com.veyndan.news.util.LogUtils;
//
//import java.util.List;
//
//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.Response;
//import retrofit.Retrofit;
//import retrofit.SimpleXmlConverterFactory;
//import retrofit.http.GET;
//
//public class ArticleService extends IntentService {
//    private static final String TAG = LogUtils.makeLogTag(ArticleService.class);
//
//    public static final String ACTION_DOWNLOAD_FEED = "action_download_feed";
//
//    public ArticleService() {
//        super(TAG);
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        switch (intent.getAction()) {
//            case ACTION_DOWNLOAD_FEED:
//                downloadFeed();
//                break;
//        }
//    }
//
//    private void downloadFeed() {
//        String url = "http://techcrunch.com";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .build();
//        ApiEndpointInterface apiService = retrofit.create(ApiEndpointInterface.class);
//        apiService.getFeed().enqueue(new Callback<Feed>() {
//            @Override
//            public void onResponse(Response<Feed> response, Retrofit retrofit) {
//                List<Feed.Article> articles = response.body().articles;
//                for (int i = 0; i < articles.size(); i++) {
//                    Feed.Article article = articles.get(i);
//                    Log.d(TAG, article.title);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i(TAG, t.getMessage());
//            }
//        });
//    }
//
//    private interface ApiEndpointInterface {
//        @GET("/feed/")
//        Call<Feed> getFeed();
//    }
//}
