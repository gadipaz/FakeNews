package gadi.fake.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import gadi.fake.news.model.Article;

public class ArticleDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<Article> articleObservable;

    @Inject
    public ArticleDetailsViewModel(@NonNull Application application) {
        super(application);
        this.articleObservable = new MutableLiveData<>();
    }
    public void setArticle(Article article){
        articleObservable.setValue(article);
    }

    /**
     * Expose the LiveData article so the UI can observe it.
     */
    public LiveData<Article> getArticleObservable() {
        return articleObservable;
    }

}
