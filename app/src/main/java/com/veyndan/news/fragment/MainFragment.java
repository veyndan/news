package com.veyndan.news.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.veyndan.news.R;
import com.veyndan.news.adapter.ArticleAdapter;
import com.veyndan.news.model.Article;
import com.veyndan.news.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String TAG = LogUtils.makeLogTag(MainFragment.class);

    private Context context;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);
        ArticleAdapter adapter = new ArticleAdapter(context, getArticles());
        recyclerView.setAdapter(adapter);

        ImageView categoryImg = (ImageView) view.findViewById(R.id.category_img);
        Glide.with(this).load(getArticles().get(6).getImg()).into(categoryImg);

        return view;
    }

    private List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(
                "Nominations For The 9th Annual Crunchies End Soon — Get Yours In Today",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 23:00:25 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/16496034085_687e04c6bc_k.jpg"
        ));
        articles.add(new Article(
                "Chaotic Moon Explores Biometric Tattoos For Medicine And The Military",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 21:47:00 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/screen-shot-2015-11-20-at-5-27-37-pm.png"
        ));
        articles.add(new Article(
                "Annotation And Document Management App LiquidText Releases New Features For IPad",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 21:25:13 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/screen-shot-2015-11-23-at-3-53-28-pm.png"
        ));
        articles.add(new Article(
                "Doc Brown Skypes With Back To The Future Superfans In Puente Hills, CA",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 21:06:19 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2014/10/toygwpw-imgur.gif"
        ));
        articles.add(new Article(
                "11 Awesome Gifts For Your Favorite 20-Something",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 20:52:35 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/71twi5ithl-_sl1500_.jpg"
        ));
        articles.add(new Article(
                "PlayStation 4 And Xbox One Bundles Available At $299 For Black Friday",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 21:25:13 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2014/08/screen-shot-2014-08-20-at-3-28-54-pm.png"
        ));
        articles.add(new Article(
                "Slack Is Having A Panic Attack",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 19:54:14 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2014/10/slack-large.png"
        ));
        articles.add(new Article(
                "Dropbox Now Lets You Edit PDF Files While On The Go",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 19:33:26 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/dropbox-adobe.png"
        ));
        articles.add(new Article(
                "FAA Task Force Proposes Registration Procedure For Drones Weighing More Than 250 Grams",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 19:09:04 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/08/ghost_drone.jpg"
        ));
        articles.add(new Article(
                "WordPress.com Goes Open Source And Gets A Desktop App",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 19:08:02 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/screen-shot-2015-11-23-at-19-20-34.png"
        ));

        articles.add(new Article(
                "The Myth Of Autonomous Vehicles’ New Craze: Ethical Algorithms",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Mon, 23 Nov 2015 19:00:41 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/robokey.jpg"
        ));
        return articles;
    }
}
