package gadi.fake.news.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import gadi.fake.news.R;
import gadi.fake.news.databinding.FragmentArticleListBinding;
import gadi.fake.news.di.Injectable;
import gadi.fake.news.model.Article;
import gadi.fake.news.view.adapter.NewsAdapter;
import gadi.fake.news.view.callback.ArticleClickCallback;
import gadi.fake.news.viewmodel.ArticlesListViewModel;

public class ArticlesListFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static final String TAG = "ProjectListFragment";
    private NewsAdapter newsAdapter;
    private FragmentArticleListBinding binding;
    private ArticlesListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false);

        newsAdapter = new NewsAdapter(articleClickCallback);
        binding.projectList.setAdapter(newsAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticlesListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(ArticlesListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getArticlesListObservable().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if (articles != null) {
                    binding.setIsLoading(false);
                    newsAdapter.setArticleList(articles);
                }
            }
        });
    }

    private final ArticleClickCallback articleClickCallback = new ArticleClickCallback() {
        @Override
        public void onClick(Article article) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(article);
            }
        }
    };

    @Override
    public void onResume() {
        viewModel.refreshNews();
        super.onResume();
    }
}
