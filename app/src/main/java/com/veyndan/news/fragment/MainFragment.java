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
        ArticleAdapter adapter = new ArticleAdapter(context, getArticles());
        recyclerView.setAdapter(adapter);

        ImageView categoryImg = (ImageView) view.findViewById(R.id.category_img);
        TextView categoryPublisher = (TextView) view.findViewById(R.id.category_publisher);
        TextView categoryTitle = (TextView) view.findViewById(R.id.category_title);

        Article article = getArticles().get(11);
        Glide.with(this).load(article.getImg()).into(categoryImg);
        categoryPublisher.setText(article.getPublisher());
        categoryTitle.setText(article.getTitle());

        return view;
    }

    private List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(
                "The whole of India can now access Facebook’s Free Basics, but where’s the banking?",
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Tue, 24 Nov 2015 18:15:44 +0000",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/Facebook-India--520x245.jpg"
        ));
        articles.add(new Article(
                "Vine update brings Apple Watch app and ‘swipe left’ gesture for finding videos you like",
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Tue, 24 Nov 2015 18:09:17 +0000",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/VineGifAppleWatchTNW-520x245.gif"
        ));
        articles.add(new Article(
                "Algorithm predicts relationship success better than human therapists",
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Tue, 24 Nov 2015 18:04:04 +0000",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/02/holding-hands-520x245.jpg"
        ));
        articles.add(new Article(
                "How technology is preserving the past",
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Tue, 24 Nov 2015 17:56:55 +0000",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/shutterstock_242435272-520x245.jpg"
        ));
        articles.add(new Article(
                "8 Gifts For Your Grandparents That They Won’t Just Toss In A Drawer",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Tue, 24 Nov 2015 17:56:13 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/grandparents.png"
        ));
        articles.add(new Article(
                "Vine Lets You Swipe Left To Find Similar Vines, Launches On Apple Watch",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Tue, 24 Nov 2015 17:51:08 +0000",
                "http://tctechcrunch2011.files.wordpress.com/2015/11/similarvines.gif?w=150"
        ));
        articles.add(new Article(
                "The Inu is a flashy, self-folding electric scooter made for carving through cities",
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Tue, 24 Nov 2015 17:36:36 +0000",
                "https://cdn0.vox-cdn.com/thumbor/98E5pNvBBq6TGAr_Sht4ynuuT5Y=/0x0:2500x1667/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47732721/green-ride-inu-electric-scooter-02525.0.0.jpg"
        ));
        articles.add(new Article(
                "Jeff Bezos’ Space Program Successfully Lands A Rocket",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Tue, 24 Nov 2015 17:27:15 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/screen-shot-2015-11-24-at-9-21-02-am.png"
        ));
        articles.add(new Article(
                "Burner now lets developers tap in using Web hooks and opens up to IFTTT",
                "The Next Web",
                "http://cdn1.tnwcdn.com/wp-content/themes/icetea/lemonsoda/icons/touch-icon-ipad-retina-precomposed.png",
                "Tue, 24 Nov 2015 17:26:47 +0000",
                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/11/Screen-Shot-2015-11-24-at-9.16.39-AM-520x245.png"
        ));
        articles.add(new Article(
                "Algorithm Predicts Relationship Success Through Couples’ Tone Of Voice",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Tue, 24 Nov 2015 17:03:59 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/breakup.jpg"
        ));
        articles.add(new Article(
                "How Governments Will Change To Better Serve Citizens In 2016",
                "TechCrunch",
                "http://1.gravatar.com/blavatar/d9ea925a71f82f06a1e6224298f7fe80?s=96&#038;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png",
                "Tue, 24 Nov 2015 17:00:49 +0000",
                "https://tctechcrunch2011.files.wordpress.com/2015/11/govsun.jpg"
        ));
        articles.add(new Article(
                "Review: Creed passes on the Rocky torch and recaptures the original's spirit",
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Tue, 24 Nov 2015 16:46:13 +0000",
                "https://cdn3.vox-cdn.com/thumbor/amlhdKxNfRmzKuANAyV7vw2zI9c=/7x0:2999x1995/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47732389/Film-Review-Creed_fiss-1.0.0.jpg"
        ));
        articles.add(new Article(
                "Five things Spotify should steal from Rdio",
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Tue, 24 Nov 2015 16:44:40 +0000",
                "https://cdn0.vox-cdn.com/thumbor/-aqhjuesJPehEx0qAsX526-x3X0=/2x0:1016x676/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47728617/Rdio_close-up.0.jpg"
        ));
        articles.add(new Article(
                "Adele just broke *NSYNC's record for single-week US album sales",
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Tue, 24 Nov 2015 16:41:12 +0000",
                "https://cdn3.vox-cdn.com/thumbor/2aqtFkU4h1tQqpcoVschIyd3zQI=/217x0:1392x783/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47727679/Screen_Shot_2015-11-19_at_10.52.44_AM.0.0.png"
        ));
        articles.add(new Article(
                "Let me spoil Fallout 4 so that you can enjoy playing Fallout 4",
                "The Verge",
                "https://cdn2.vox-cdn.com/community_logos/34086/verge-fv.png",
                "Tue, 24 Nov 2015 15:58:42 +0000",
                "https://cdn3.vox-cdn.com/thumbor/8gHZzMBlMPtuUZzIgMSUZva9ohU=/0x0:1620x1080/800x536/cdn0.vox-cdn.com/uploads/chorus_image/image/47731923/Fallout4_E3_Codsworth1_1434323959.0.0.0.png"
        ));
        return articles;
    }
}
