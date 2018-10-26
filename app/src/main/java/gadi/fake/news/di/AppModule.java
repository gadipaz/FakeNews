package gadi.fake.news.di;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gadi.fake.news.service.NewsApiService;
import gadi.fake.news.viewmodel.ArticleViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton
    @Provides
    NewsApiService provideNewsApiService() {
        return new Retrofit.Builder()
                .baseUrl(NewsApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ArticleViewModelFactory(viewModelSubComponent.build());
    }
}
