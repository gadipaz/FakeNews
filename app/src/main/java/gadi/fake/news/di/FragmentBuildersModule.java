package gadi.fake.news.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gadi.fake.news.view.ui.ArticleDetailsFragment;
import gadi.fake.news.view.ui.ArticlesListFragment;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ArticleDetailsFragment contributeArticleDetailsFragment();

    @ContributesAndroidInjector
    abstract ArticlesListFragment contributeArticlesListFragment();
}
