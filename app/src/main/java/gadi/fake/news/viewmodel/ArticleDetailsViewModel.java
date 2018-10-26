package gadi.fake.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import javax.inject.Inject;

import gadi.fake.news.model.Article;
import gadi.fake.news.service.ArticleRepository;

public class ArticleDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<Article> articleObservable;

    @Inject
    public ArticleDetailsViewModel(@NonNull ArticleRepository articleRepository, @NonNull Application application) {
        super(application);
        this.articleObservable = new MutableLiveData<>();
        //articleObservable.setValue(article);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<Article> getArticleObservable() {
        return articleObservable;
    }

}
