package com.veyndan.news.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veyndan.news.R;
import com.veyndan.news.adapter.ArticleAdapter;
import com.veyndan.news.model.Feed;
import com.veyndan.news.util.LogUtils;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;
import retrofit.http.GET;

public class MainFragment extends Fragment {
    private static final String TAG = LogUtils.makeLogTag(MainFragment.class);

    private Context context;
    private RecyclerView recyclerView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Feed.Article> articles;
        if (savedInstanceState != null) {
            articles = null;
        } else {
            articles = null;
            downloadFeed();
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        ArticleAdapter adapter = new ArticleAdapter(context, articles);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void downloadFeed() {
        String url = "http://techcrunch.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ApiEndpointInterface apiService = retrofit.create(ApiEndpointInterface.class);
        apiService.getFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Response<Feed> response, Retrofit retrofit) {
                List<Feed.Article> articles = response.body().articles;
                recyclerView.setAdapter(new ArticleAdapter(context, articles));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private interface ApiEndpointInterface {
        @GET("/feed/")
        Call<Feed> getFeed();
    }
}
