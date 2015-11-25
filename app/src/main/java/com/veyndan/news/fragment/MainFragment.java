package com.veyndan.news.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
        ArticleAdapter adapter = new ArticleAdapter(context, recyclerView, getArticles());
        recyclerView.setAdapter(adapter);

        ImageView categoryImg = (ImageView) view.findViewById(R.id.category_img);
        TextView categoryPublisher = (TextView) view.findViewById(R.id.category_publisher);
        TextView categoryTitle = (TextView) view.findViewById(R.id.category_title);

        Article article = getArticles().get(10);
        Glide.with(this).load(article.getImg()).into(categoryImg);
        categoryPublisher.setText(article.getPublisher());
        categoryTitle.setText(article.getTitle());

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float xOffset = -1, yOffset = -1;
            int height, width;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // For some reason action_down is not called first time on action_down
                if (event.getAction() == MotionEvent.ACTION_DOWN || xOffset == -1 || yOffset == -1) {
                    xOffset = event.getRawX() - v.getX();
                    yOffset = event.getRawY() - v.getY();
                    ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                    height = layoutParams.height;
                    width = layoutParams.width;
                } else {
                    int updateHeight = (int) (-event.getRawY() + yOffset + 1920);
                    if (updateHeight > 816) {
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, updateHeight);
                        params.topMargin = 1920 - updateHeight;
                        v.setLayoutParams(params);

                        v.setScaleX(updateHeight / 816f);
                    }
                }
                return false;
            }
        });

        return view;
    }

    private List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(
                "Leticia Gonzales",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Just finished my first marathon! Thank you so much to friends and family for " +
                        "your love and support along the way. Couldn't have done it without you!",
                null
        ));
        articles.add(new Article(
                "Michael O'Neil",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Maddie gets to take a well-deserved break from all her standing. And in the " +
                        "front seat, too.",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/VineGifAppleWatchTNW-520x245.gif"
        ));
        articles.add(new Article(
                "Matt Brown with Jodie Watson and Tom Watson in Portland, OR",
                null,
                " - Hanging out on the back porch.",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/shutterstock_242435272-520x245.jpg"
        ));
        articles.add(new Article(
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "8 Gifts For Your Grandparents That They Won’t Just Toss In A Drawer",
                null
        ));
        articles.add(new Article(
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Vine Lets You Swipe Left To Find Similar Vines, Launches On Apple Watch",
                "http://tctechcrunch2011.files.wordpress.com/2015/11/similarvines.gif?w=150"
        ));
        articles.add(new Article(
                "The Verge",
                null,
                "The Inu is a flashy, self-folding electric scooter made for carving through cities",
                "https://cdn0.vox-cdn.com/thumbor/98E5pNvBBq6TGAr_Sht4ynuuT5Y=/0x0:2500x1667/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47732721/green-ride-inu-electric-scooter-02525.0.0.jpg"
        ));
        articles.add(new Article(
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Jeff Bezos’ Space Program Successfully Lands A Rocket",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/screen-shot-2015-11-24-at-9-21-02-am.png"
        ));
        articles.add(new Article(
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Burner now lets developers tap in using Web hooks and opens up to IFTTT",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/Screen-Shot-2015-11-24-at-9.16.39-AM-520x245.png"
        ));
        articles.add(new Article(
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Algorithm Predicts Relationship Success Through Couples’ Tone Of Voice",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/breakup.jpg"
        ));
        articles.add(new Article(
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "How Governments Will Change To Better Serve Citizens In 2016",
                null
        ));
        articles.add(new Article(
                "The Verge",
                null,
                "Review: Creed passes on the Rocky torch and recaptures the original's spirit",
                "https://cdn3.vox-cdn.com/thumbor/amlhdKxNfRmzKuANAyV7vw2zI9c=/7x0:2999x1995/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47732389/Film-Review-Creed_fiss-1.0.0.jpg"
        ));
        articles.add(new Article(
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Five things Spotify should steal from Rdio",
                "https://cdn0.vox-cdn.com/thumbor/-aqhjuesJPehEx0qAsX526-x3X0=/2x0:1016x676/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47728617/Rdio_close-up.0.jpg"
        ));
        articles.add(new Article(
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Adele just broke *NSYNC's record for single-week US album sales",
                null

        ));
        articles.add(new Article(
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Let me spoil Fallout 4 so that you can enjoy playing Fallout 4",
                "https://cdn3.vox-cdn.com/thumbor/8gHZzMBlMPtuUZzIgMSUZva9ohU=/0x0:1620x1080/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47731923/Fallout4_E3_Codsworth1_1434323959.0.0.0.png"
        ));
        return articles;
    }
}
