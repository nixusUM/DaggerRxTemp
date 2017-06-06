package com.example.nlbochas.daggerrxtemp.mvp.presenter;

import com.example.nlbochas.daggerrxtemp.api.CakeApiService;
import com.example.nlbochas.daggerrxtemp.base.BasePresenter;
import com.example.nlbochas.daggerrxtemp.mapper.CakeMapper;
import com.example.nlbochas.daggerrxtemp.mvp.model.CakesResponse;
import com.example.nlbochas.daggerrxtemp.mvp.model.Storage;
import com.example.nlbochas.daggerrxtemp.mvp.view.MainView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class CakePresenter extends BasePresenter<MainView> implements Observer<CakesResponse> {

    @Inject protected CakeApiService cakeApiService;
    @Inject protected CakeMapper cakeMapper;
    @Inject protected Storage storage;


    @Inject
    public CakePresenter() {
    }

    public void getCakes() {
        getView().onShowDialog("Loading cakes...");
        Observable<CakesResponse> cakesResponseObservable = cakeApiService.getCakes();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().showToastMessage("Cakes loaded");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().showToastMessage("Error when loading cakes" + ":" + e.getMessage());
    }

    @Override
    public void onNext(CakesResponse cakesResponse) {
        getView().clearCakeList();
        getView().onCakeLoaded(cakeMapper.mapCakes(storage, cakesResponse));
    }

    public void getSavedCakes() {
        getView().clearCakeList();
        getView().onCakeLoaded(storage.getSavedCakes());
    }
}
