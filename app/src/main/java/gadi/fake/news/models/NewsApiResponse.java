package gadi.fake.news.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("articles")
    private List<Article> articles;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("totalResults")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Article> getResults() {
        return articles;
    }

    public void setResults(List<Article> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
