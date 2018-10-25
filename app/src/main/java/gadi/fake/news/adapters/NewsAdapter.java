package gadi.fake.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gadi.fake.news.R;
import gadi.fake.news.models.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MovieViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(Article item);
    }

    private List<Article> articles;
    private final OnItemClickListener listener;
    private int rowLayout;
    private Context context;

    public NewsAdapter(List<Article> articles, int rowLayout, Context context, OnItemClickListener listener) {
        this.articles = articles;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView movieImage;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = v.findViewById(R.id.movies_layout);
            movieImage = v.findViewById(R.id.movie_image);
            movieTitle = v.findViewById(R.id.title);
            data = v.findViewById(R.id.date);
            movieDescription = v.findViewById(R.id.description);
            rating = v.findViewById(R.id.rating);
        }
    }

    @Override
    public NewsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = articles.get(position).getUrlToImage();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(articles.get(position));
            }
        });
        holder.movieTitle.setText(articles.get(position).getTitle());
        //holder.data.setText(articles.get(position).getDescription());
        holder.movieDescription.setText(articles.get(position).getDescription());
        //holder.rating.setText(articles.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
