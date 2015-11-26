package com.veyndan.news.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyndan.news.R;
import com.veyndan.news.model.Article;
import com.veyndan.news.util.LogUtils;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(ArticleAdapter.class);

    private static final int TYPE_TEXT_IMAGE = 0;
    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;

    private Context context;
    private List<Article> articles;

    public ArticleAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        switch (viewType) {
            case TYPE_TEXT_IMAGE:
                layout = R.layout.article_text_image;
                break;
            case TYPE_TEXT:
                layout = R.layout.article_text;
                break;
            case TYPE_IMAGE:
                layout = R.layout.article_image;
                break;
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        Article article = articles.get(position);
        if (article.getImg() == null) {
            return TYPE_TEXT;
        } else if (article.getPublisherImg() == null) {
            return TYPE_IMAGE;
        }
        return TYPE_TEXT_IMAGE;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articles.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_TEXT_IMAGE:
                holder.title.setText(article.getTitle());
                holder.publisher.setText(article.getPublisher());
                Glide.with(context).load(article.getImg()).into(holder.image);
                Glide.with(context).load(article.getPublisherImg()).into(holder.publisherImg);
                break;
            case TYPE_TEXT:
                holder.title.setText(article.getTitle());
                holder.publisher.setText(article.getPublisher());
                Glide.with(context).load(article.getPublisherImg()).into(holder.publisherImg);
                break;
            case TYPE_IMAGE:
                Spannable text = new SpannableString(article.getPublisher() + (article.getPublisher().contains(" in ") ? "" : "\n") + article.getTitle());
                text.setSpan(new StyleSpan(Typeface.BOLD), 0, article.getPublisher().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.title.setText(text);
                Glide.with(context).load(article.getImg()).into(holder.image);
                break;
        }
    }

    @Override
    public int getItemCount() {
//        return 1;
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView publisherImg;
        ImageView image;
        TextView title;
        TextView publisher;

        private static final float SCREEN_WIDTH = 1080f;
        private static final float SCREEN_HEIGHT = 1920f;
        private static final float UNSCALED_ARTICLE_HEIGHT = 816f;

        public ViewHolder(View itemView) {
            super(itemView);
            publisherImg = (ImageView) itemView.findViewById(R.id.article_profile_img);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            publisher = (TextView) itemView.findViewById(R.id.article_publisher);

            itemView.setOnTouchListener(new View.OnTouchListener() {
                float yOffset = -1; // Distance from top of screen to finger
                float articleHeight;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO set defaults in onBindViewHolder for each article as otherwise recycling view params
                    // TODO increase width of all visible items

                    Log.d(TAG, "event.getAction(): " + event.getAction());

                    // For some reason action_down is not called first time on action_down
                    if (event.getAction() == MotionEvent.ACTION_DOWN || yOffset == -1) {
                        yOffset = event.getRawY();
                        articleHeight = v.getHeight();
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            float updateHeight = yOffset - event.getRawY() + articleHeight;

//                            Log.d(TAG, "yOffset: " + yOffset + "\tevent.getRawY(): " + event.getRawY());

//                            if (updateHeight >= (UNSCALED_ARTICLE_HEIGHT + 1) && updateHeight <= SCREEN_HEIGHT) {
                                FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) updateHeight);
                                parentParams.topMargin = (int) (SCREEN_HEIGHT - updateHeight);
                                ((RecyclerView) v.getParent()).setLayoutParams(parentParams);

                                RecyclerView.LayoutParams itemParams = (RecyclerView.LayoutParams) v.getLayoutParams();
                                itemParams.width = (int) (updateHeight * (SCREEN_WIDTH / SCREEN_HEIGHT));
                                v.setLayoutParams(itemParams);
//                            } else {
                                // If not in range then update where pivot is e.g. if requested
                                // height too small then pivot continues to move so any movement
                                // up will result in the article getting bigger instead of having
                                // to pass the original pivot point again
//                                yOffset = event.getRawY();
//                            }
                            return true;
                        case MotionEvent.ACTION_UP:
//                            float endScale;
//                        if (v.getScaleX() < 1.5f) {
//                            endScale = 1f / v.getScaleX();
//                        } else {
//                            endScale = screenHeight / (v.getScaleX() * unscaledArticleHeight);
//                        }
//                        scaleView(v, 1f, endScale, event.getRawX() / (screenHeight - unscaledArticleHeight));
//                        yOffset = -1;
                            return false;
                        default:
                            return true;
                    }
                }
            });
        }
    }
}
