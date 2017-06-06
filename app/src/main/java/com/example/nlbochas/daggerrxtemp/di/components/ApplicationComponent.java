package com.example.nlbochas.daggerrxtemp.di.components;

import android.content.Context;

import com.example.nlbochas.daggerrxtemp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
