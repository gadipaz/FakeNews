package gadi.fake.news.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import gadi.fake.news.model.Article;
import gadi.fake.news.model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ArticleRepository {

    private final static String API_KEY = "07b141e2a7874dd8a7545918950416eb";
    private NewsApiService newsApiService;
    private static ArticleRepository articleRepository;

    @Inject
    public ArticleRepository(NewsApiService newsApiService) {

        this.newsApiService = newsApiService;
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
