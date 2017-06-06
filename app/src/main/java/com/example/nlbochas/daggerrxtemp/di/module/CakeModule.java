package com.example.nlbochas.daggerrxtemp.di.module;

import com.example.nlbochas.daggerrxtemp.api.CakeApiService;
import com.example.nlbochas.daggerrxtemp.di.scope.PerActivity;
import com.example.nlbochas.daggerrxtemp.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CakeModule {

    private MainView view;

    public CakeModule(MainView view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    CakeApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(CakeApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return view;
    }
}

