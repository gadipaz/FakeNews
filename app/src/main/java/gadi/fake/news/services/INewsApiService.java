package gadi.fake.news.services;

import gadi.fake.news.models.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface INewsApiService {

    @GET("top-headlines?sources=bbc-news")
    Call<NewsApiResponse> getTopHeadlines(@Query("apiKey") String apiKey);

}
