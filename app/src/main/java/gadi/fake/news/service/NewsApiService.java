package gadi.fake.news.service;

import java.util.List;

import gadi.fake.news.model.Article;
import gadi.fake.news.model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines?sources=techcrunch")
    Call<NewsApiResponse> getTopHeadlines(@Query("apiKey") String apiKey);

}
