package com.example.nlbochas.daggerrxtemp.di.components;

import com.example.nlbochas.daggerrxtemp.di.module.CakeModule;
import com.example.nlbochas.daggerrxtemp.di.scope.PerActivity;
import com.example.nlbochas.daggerrxtemp.modules.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = CakeModule.class, dependencies = ApplicationComponent.class)
public interface CakeComponent {

    void inject(MainActivity activity);
}