package gadi.fake.news.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import gadi.fake.news.R;
import gadi.fake.news.model.Article;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add articles list fragment if this is first creation
        if (savedInstanceState == null) {
            ArticlesListFragment fragment = new ArticlesListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ArticlesListFragment.TAG).commit();
        }
    }

    /** Shows the project detail fragment */
    public void show(Article article) {
        ArticleDetailsFragment articleDetailsFragment = ArticleDetailsFragment.forArticle(article);

        //Show article
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("article")
                .replace(R.id.fragment_container,
                        articleDetailsFragment, null).commit();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
