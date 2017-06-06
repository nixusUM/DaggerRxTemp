package com.example.nlbochas.daggerrxtemp.mvp.view;

import com.example.nlbochas.daggerrxtemp.mvp.model.MyCake;

import java.util.List;

public interface MainView extends BaseView {
    void onCakeLoaded(List<MyCake> myCakes);
    void onShowDialog(String message);
    void onHideDialog();
    void showToastMessage(String message);
    void clearCakeList();
}
