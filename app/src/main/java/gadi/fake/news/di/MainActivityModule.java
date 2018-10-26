package gadi.fake.news.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gadi.fake.news.view.ui.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
