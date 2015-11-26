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
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
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

public class MainActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);

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
        // 360dp:640dp 9:16 ScreenWidth:ScreenHeight DONE
        // 3:4 Article:Category DONE
        // 6:19 Profile:Image DONE
        // 5 Article max lines

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ImageView categoryImg = (ImageView) findViewById(R.id.category_img);
        TextView categoryPublisher = (TextView) findViewById(R.id.category_publisher);
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);
        ArticleAdapter adapter = new ArticleAdapter(this, getArticles());
        recyclerView.setAdapter(adapter);

        Article article = getArticles().get(10);
        Glide.with(this).load(article.getImg()).into(categoryImg);
        categoryPublisher.setText(article.getPublisher());
        categoryTitle.setText(article.getTitle());

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float yOffset = -1;
            int height;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // For some reason action_down is not called first time on action_down
                if (yOffset == -1) {
                    ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                    height = layoutParams.height;
                    yOffset = event.getRawY() - v.getY();
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                        height = layoutParams.height;
                        yOffset = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float updateHeight = 1920f - event.getRawY() + yOffset;
//                        if (updateHeight >= 816f && updateHeight <= 1920f) {
                        v.setPivotY(816f);
                        v.setScaleX(updateHeight / 816f);
                        v.setScaleY(updateHeight / 816f);
//                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        float endScale;
                        if (v.getScaleX() < 1.5f) {
                            endScale = 1f / v.getScaleX();
                        } else {
                            endScale = 1920f / (v.getScaleX() * 816f);
                        }
                        scaleView(v, 1f, endScale);
                        break;
                }
                return false;
            }
        });

    }

    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        anim.setInterpolator(new DecelerateInterpolator());
        v.startAnimation(anim);
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
