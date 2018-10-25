package gadi.fake.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import gadi.fake.news.model.Article;
import gadi.fake.news.service.ArticleRepository;

public class ArticlesListViewModel extends AndroidViewModel {

    private final LiveData<List<Article>> articlesListObservable;

    public ArticlesListViewModel(@NonNull Application application) {
        super(application);

        articlesListObservable = ArticleRepository.getInstance().getArticlesList();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Article>> getProjectListObservable() {
        return articlesListObservable;
    }
}
