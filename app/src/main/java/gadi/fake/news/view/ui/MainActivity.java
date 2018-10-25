package gadi.fake.news.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import gadi.fake.news.R;
import gadi.fake.news.model.Article;
import gadi.fake.news.model.NewsApiResponse;
import gadi.fake.news.service.NewsApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            ArticlesListFragment fragment = new ArticlesListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ArticlesListFragment.TAG).commit();
        }
    }

    /** Shows the project detail fragment */
    public void show(Article article) {
        ArticleDetailsFragment articleDetailsFragment = ArticleDetailsFragment.forArticle(article);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("article")
                .replace(R.id.fragment_container,
                        articleDetailsFragment, null).commit();
    }
}
