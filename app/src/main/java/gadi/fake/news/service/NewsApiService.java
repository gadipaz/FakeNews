package gadi.fake.news.service;

import gadi.fake.news.model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    String BASE_URL = "https://newsapi.org/v2/";

    //Get top 10 news from TechChrunch
    @GET("top-headlines?sources=techcrunch")
    Call<NewsApiResponse> getTopHeadlines(@Query("apiKey") String apiKey);

}
