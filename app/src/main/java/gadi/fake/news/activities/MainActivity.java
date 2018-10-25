package gadi.fake.news.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import gadi.fake.news.R;
import gadi.fake.news.adapters.NewsAdapter;
import gadi.fake.news.models.Article;
import gadi.fake.news.models.NewsApiResponse;
import gadi.fake.news.services.INewsApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "07b141e2a7874dd8a7545918950416eb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndGetApiData();
    }

    public void connectAndGetApiData(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        INewsApiService newsApiService = retrofit.create(INewsApiService.class);

        Call<NewsApiResponse> call = newsApiService.getTopHeadlines(API_KEY);
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                List<Article> articles = response.body().getResults();
                recyclerView.setAdapter(new NewsAdapter(articles, R.layout.list_item_article, getApplicationContext(),
                        new NewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Article item) {
                                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        }));
                //Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
