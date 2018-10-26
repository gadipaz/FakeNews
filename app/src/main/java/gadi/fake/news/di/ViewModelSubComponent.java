package gadi.fake.news.di;

import dagger.Subcomponent;
import gadi.fake.news.viewmodel.ArticleDetailsViewModel;
import gadi.fake.news.viewmodel.ArticlesListViewModel;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    ArticlesListViewModel articlesListViewModel();
    ArticleDetailsViewModel articleDetailsViewModel();
}
