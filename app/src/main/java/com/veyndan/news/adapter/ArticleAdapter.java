package com.veyndan.news.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private static final float SCREEN_WIDTH = 1080f;
    private static final float SCREEN_HEIGHT = 1920f;
    private static final float MIN_ARTICLE_HEIGHT = 816f;
    private static final float MAX_ARTICLE_HEIGHT = SCREEN_HEIGHT;

    private float scale = 1f;

    public ArticleAdapter(Context context, List<Article> articles, float scale) {
        this.context = context;
        this.articles = articles;
        this.scale = scale;
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
        if (holder.profileTitle != null) scaleText(holder.profileTitle, 1f / scale);
        if (holder.profileImg != null) scaleImage(holder.profileImg, 1f / scale);
        if (holder.storyTitle != null) scaleText(holder.storyTitle, 1f / scale);
        if (holder.storyImg != null) scaleImage(holder.storyImg, 1f / scale);

        Article article = articles.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_TEXT_IMAGE:
                holder.profileTitle.setText(article.getPublisher());
                Glide.with(context).load(article.getPublisherImg()).into(holder.profileImg);
                holder.storyTitle.setText(article.getTitle());
                Glide.with(context).load(article.getImg()).into(holder.storyImg);
                break;
            case TYPE_TEXT:
                holder.profileTitle.setText(article.getPublisher());
                Glide.with(context).load(article.getPublisherImg()).into(holder.profileImg);
                holder.storyTitle.setText(article.getTitle());
                break;
            case TYPE_IMAGE:
                Glide.with(context).load(article.getImg()).into(holder.storyImg);
                Spannable text = new SpannableString(article.getPublisher() + (article.getPublisher().contains(" in ") ? "" : "\n") + article.getTitle());
                text.setSpan(new StyleSpan(Typeface.BOLD), 0, article.getPublisher().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.storyTitle.setText(text);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImg;
        ImageView storyImg;
        TextView storyTitle;
        TextView profileTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            profileTitle = (TextView) itemView.findViewById(R.id.article_profile_title);
            profileImg = (ImageView) itemView.findViewById(R.id.article_profile_img);
            storyTitle = (TextView) itemView.findViewById(R.id.article_story_title);
            storyImg = (ImageView) itemView.findViewById(R.id.article_story_img);
        }
    }

    private void scaleText(TextView textView, float scale) {
        textView.setTextScaleX(scale);
        textView.setScaleY(scale);
    }

    private void scaleImage(ImageView imageView, float scale) {
        imageView.setScaleX(scale);
        imageView.setScaleY(scale);
    }
}
