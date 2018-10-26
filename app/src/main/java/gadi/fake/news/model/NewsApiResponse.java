package gadi.fake.news.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {
    //TODO: not in use yet
    @SerializedName("page")
    private int page;
    @SerializedName("articles")
    private List<Article> articles;
    //TODO: not in use yet
    @SerializedName("total_results")
    private int totalResults;
    //TODO: not in use yet
    @SerializedName("totalResults")
    private int totalPages;

    public List<Article> getResults() {
        return articles;
    }

}
