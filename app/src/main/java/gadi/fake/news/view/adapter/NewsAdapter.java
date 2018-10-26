package gadi.fake.news.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gadi.fake.news.R;
import gadi.fake.news.databinding.ArticleListItemBinding;
import gadi.fake.news.model.Article;
import gadi.fake.news.view.callback.ArticleClickCallback;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>{

    private List<? extends Article> articles;
    @Nullable
    private final ArticleClickCallback articleClickCallback;

    public NewsAdapter(@Nullable ArticleClickCallback articleClickCallback) {
        this.articleClickCallback = articleClickCallback;
    }

    public void setArticleList(final List<? extends Article> articleList) {
        if (this.articles == null) {
            this.articles = articleList;
            notifyItemRangeInserted(0, articleList.size());
        } else {
            //Notify only if there are new articles
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return NewsAdapter.this.articles.size();
                }

                @Override
                public int getNewListSize() {
                    return articleList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return articles.get(oldItemPosition).getUrl().equals(
                            articleList.get(newItemPosition).getUrl());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return articles.get(oldItemPosition).getUrl().equals(
                            articleList.get(newItemPosition).getUrl());
                }
            });
            this.articles = articleList;
            result.dispatchUpdatesTo(this);
        }
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final ArticleListItemBinding binding;

        public ArticleViewHolder(ArticleListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public NewsAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_list_item,
                        parent, false);

        binding.setCallback(articleClickCallback);

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {
        holder.binding.setArticle(articles.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
