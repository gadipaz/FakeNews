package gadi.fake.news.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.List;

import gadi.fake.news.R;
import gadi.fake.news.databinding.FragmentArticleDetailsBinding;
import gadi.fake.news.databinding.FragmentArticleListBinding;
import gadi.fake.news.model.Article;
import gadi.fake.news.view.adapter.NewsAdapter;
import gadi.fake.news.view.callback.ArticleClickCallback;
import gadi.fake.news.viewmodel.ArticleDetailsViewModel;
import gadi.fake.news.viewmodel.ArticleViewModelFactory;
import gadi.fake.news.viewmodel.ArticlesListViewModel;

public class ArticleDetailsFragment extends Fragment {

    private Article article;
    private FragmentArticleDetailsBinding binding;

    public void setArticle(Article article){
        this.article = article;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_details, container, false);
        binding.setIsLoading(true);
        // Create and set the adapter for the RecyclerView.
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArticleDetailsViewModel viewModel = ViewModelProviders.of(this, new ArticleViewModelFactory(getActivity().getApplication(), article)).get(ArticleDetailsViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(ArticleDetailsViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getArticleObservable().observe(this, new Observer<Article>() {
            @Override
            public void onChanged(@Nullable Article article) {
                if (article != null) {
                    binding.setArticle(article);
                    //binding.setIsLoading(false);
                    //newsAdapter.setArticleList(articles);
                }
            }
        });
    }

    /** Creates project fragment for specific article */
    public static ArticleDetailsFragment forArticle(Article article) {
        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        fragment.setArticle(article);
        return fragment;
    }

}
