package com.veyndan.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyndan.news.R;
import com.veyndan.news.model.Feed;
import com.veyndan.news.util.ArticleFormat;
import com.veyndan.news.util.LogUtils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(ArticleAdapter.class);

    private Context context;
    private List<Feed.Article> articles;

    public ArticleAdapter(Context context, List<Feed.Article> articles) {
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
        if (articles != null) {
            Feed.Article article = articles.get(position);

            holder.title.setText(article.title);
            holder.subtitle.setText("TechCrunch · " + ArticleFormat.time(article.pubDate));
            Serializer ser = new Persister();
            try {
                // TODO Traverse through tree of xml tags and take the <img> element, and take the
                // <p> element(s) and/or the string without tags enclosing it.
                Feed.Article.Image image = ser.read(Feed.Article.Image.class, "<div><img url=\"http://www.wired.com/wp-content/uploads/2015/10/Simpsons.png\" alt=\"Simpsons Halloween Couch Gag Gets Ren &#038; Stimpy Treatment\" /></div><p>The legendarily bizarre John K. creates a grotesque musical tale that re-animates \"Homer's Enemy.\"</p> <p>The post <a rel=\"nofollow\" href=\"http://www.wired.com/2015/10/simpsons-couch-gag-ren-stimpy/\"><em>Simpsons</em> Halloween Couch Gag Gets <em>Ren &#038; Stimpy</em> Treatment</a> appeared first on <a rel=\"nofollow\" href=\"http://www.wired.com\">WIRED</a>.</p>");
                Glide.with(context).load(image.url).into(holder.image);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            subtitle = (TextView) itemView.findViewById(R.id.article_subtitle);
        }
    }
}
