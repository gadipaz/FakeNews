package gadi.fake.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import gadi.fake.news.model.Article;
import gadi.fake.news.service.ArticleRepository;

public class ArticlesListViewModel extends AndroidViewModel {

    private LiveData<List<Article>> articlesListObservable;
    private ArticleRepository articleRepository;

    @Inject
    public ArticlesListViewModel(@NonNull ArticleRepository articleRepository, @NonNull Application application) {
        super(application);

        this.articleRepository = articleRepository;
        refreshNews();
    }

    public void refreshNews(){
        articlesListObservable = articleRepository.getArticlesList();
    }

    /**
     * Expose the LiveData articles query so the UI can observe it.
     */
    public LiveData<List<Article>> getProjectListObservable() {
        return articlesListObservable;
    }
}
