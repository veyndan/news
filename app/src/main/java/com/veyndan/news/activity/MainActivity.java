package com.veyndan.news.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyndan.news.R;
import com.veyndan.news.adapter.ArticleAdapter;
import com.veyndan.news.model.Article;
import com.veyndan.news.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);

    private static final float SCREEN_WIDTH = 1080f;
    private static final float SCREEN_HEIGHT = 1920f;
    private static final float MIN_ARTICLE_HEIGHT = 816f;
    private static final float MAX_ARTICLE_HEIGHT = SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Facebook");
        }

        // 25dp StatusBarHeight
        // 56dp ActionBarHeight
        // 360dp:640dp 1080px:1920px 9:16 ScreenWidth:ScreenHeight DONE
        // 3:4 Article:Category DONE
        // 6:19 Profile:Image DONE
        // 5 Article max lines

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);
        final ArticleAdapter adapter = new ArticleAdapter(this, getArticles(), 1f);
        recyclerView.setAdapter(adapter);

        setupCategory(10);

        recyclerView.setPivotY(MIN_ARTICLE_HEIGHT);

        // Scale 1f is MIN_ARTICLE_HEIGHT

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float yOffset, heightOffset, scaleOffset;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        yOffset = event.getRawY();
                        heightOffset = v.getHeight() * v.getScaleY();
                        scaleOffset = v.getScaleY();
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        float scale = scaleOffset * ((heightOffset + yOffset - event.getRawY()) / heightOffset);
                        if (scale >= 1f && scale <= MAX_ARTICLE_HEIGHT / MIN_ARTICLE_HEIGHT) {
                            v.setScaleX(scale);
                            v.setScaleY(scale);
                            recyclerView.setAdapter(new ArticleAdapter(MainActivity.this, getArticles(), scale));
                        }
                        return false;
                    case MotionEvent.ACTION_UP:
                        float s = v.getScaleY() < 1.6f ? 1f : MAX_ARTICLE_HEIGHT / MIN_ARTICLE_HEIGHT;
                        v.setScaleX(s);
                        v.setScaleY(s);
                        recyclerView.setAdapter(new ArticleAdapter(MainActivity.this, getArticles(), s));
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    private void scaleAnimation(RecyclerView recyclerView, float from, float to) {
        ScaleAnimation animation = new ScaleAnimation(
                from, to,
                from, to,
                Animation.RELATIVE_TO_SELF, (float) 0.5,
                Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setFillAfter(true);
        recyclerView.setAnimation(animation);

    }

    private void setupCategory(int index) {
        Article article = getArticles().get(index);
        ImageView categoryImg = (ImageView) findViewById(R.id.category_img);
        TextView categoryPublisher = (TextView) findViewById(R.id.category_publisher);
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);
        Glide.with(this).load(article.getImg()).into(categoryImg);
        categoryPublisher.setText(article.getPublisher());
        categoryTitle.setText(article.getTitle());
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
