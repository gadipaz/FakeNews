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

//    /** Shows the project detail fragment */
//    public void show(Article project) {
//        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack("project")
//                .replace(R.id.fragment_container,
//                        projectFragment, null).commit();
//    }


//    private static final String TAG = MainActivity.class.getSimpleName();
//    private static Retrofit retrofit = null;
//    private RecyclerView recyclerView = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        connectAndGetApiData();
//    }
//
//    public void connectAndGetApiData(){
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        NewsApiService newsApiService = retrofit.create(NewsApiService.class);
//
//        Call<NewsApiResponse> call = newsApiService.getTopHeadlines("");
//        call.enqueue(new Callback<NewsApiResponse>() {
//            @Override
//            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
//                List<Article> articles = response.body().getResults();
//                recyclerView.setAdapter(new NewsAdapter(articles, R.layout.article_list_item, getApplicationContext(),
//                        new NewsAdapter.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(Article item) {
//                                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                            }
//                        }));
//                //Log.d(TAG, "Number of movies received: " + movies.size());
//
//            }
//
//            @Override
//            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
//                Log.e(TAG, throwable.toString());
//            }
//        });
//    }
}
