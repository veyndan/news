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
    private RecyclerView recyclerView;
    private List<Article> articles;

    public ArticleAdapter(Context context, RecyclerView recyclerView, List<Article> articles) {
        this.context = context;
        this.recyclerView = recyclerView;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView publisherImg;
        ImageView image;
        TextView title;
        TextView publisher;

        public ViewHolder(final View itemView) {
            super(itemView);
            publisherImg = (ImageView) itemView.findViewById(R.id.article_profile_img);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            publisher = (TextView) itemView.findViewById(R.id.article_publisher);
        }
    }
}
