package com.veyndan.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyndan.news.R;
import com.veyndan.news.model.Article;
import com.veyndan.news.util.ArticleFormat;
import com.veyndan.news.util.LogUtils;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(ArticleAdapter.class);

    private Context context;
    private List<Article> articles;

    public ArticleAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.publisher.setText(article.getPublisher());
        holder.pubDate.setText(ArticleFormat.time(article.getPubDate()));
        Glide.with(context).load(article.getPublisherImg()).into(holder.publisherImg);
        Glide.with(context).load(article.getImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView publisherImg;
        TextView pubDate;
        ImageView image;
        TextView title;
        TextView publisher;

        public ViewHolder(View itemView) {
            super(itemView);
            publisherImg = (ImageView) itemView.findViewById(R.id.article_publisherImg);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            publisher = (TextView) itemView.findViewById(R.id.article_publisher);
            pubDate = (TextView) itemView.findViewById(R.id.article_pubDate);
        }
    }
}
