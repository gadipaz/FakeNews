package gadi.fake.news.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import gadi.fake.news.model.Article;
import gadi.fake.news.model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleRepository {

    private final static String API_KEY = "07b141e2a7874dd8a7545918950416eb";
    private NewsApiService newsApiService;
    private static ArticleRepository articleRepository;

    private ArticleRepository() {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApiService = retrofit.create(NewsApiService.class);
    }

    public synchronized static ArticleRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (articleRepository == null) {
            if (articleRepository == null) {
                articleRepository = new ArticleRepository();
            }
        }
        return articleRepository;
    }

    public LiveData<List<Article>> getArticlesList() {
        final MutableLiveData<List<Article>> data = new MutableLiveData<>();

        newsApiService.getTopHeadlines(API_KEY).enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                List<Article> articles = response.body().getResults();
                data.setValue(articles);
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                //Log.e(TAG, throwable.toString());
            }
        });

        return data;
    }
}
