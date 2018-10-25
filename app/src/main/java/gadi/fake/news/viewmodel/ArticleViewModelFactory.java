package gadi.fake.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import gadi.fake.news.model.Article;

public class ArticleViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private Article article;


    public ArticleViewModelFactory(Application application, Article article) {
        this.application = application;
        this.article = article;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ArticleDetailsViewModel(application, article);
    }
}
