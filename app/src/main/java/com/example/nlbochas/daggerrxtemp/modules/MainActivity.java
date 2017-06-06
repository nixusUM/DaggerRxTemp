package com.example.nlbochas.daggerrxtemp.modules;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nlbochas.daggerrxtemp.R;
import com.example.nlbochas.daggerrxtemp.base.BaseActivity;
import com.example.nlbochas.daggerrxtemp.di.components.DaggerCakeComponent;
import com.example.nlbochas.daggerrxtemp.di.module.CakeModule;
import com.example.nlbochas.daggerrxtemp.modules.adapter.CakesAdapter;
import com.example.nlbochas.daggerrxtemp.modules.details.DetailActivity;
import com.example.nlbochas.daggerrxtemp.mvp.model.MyCake;
import com.example.nlbochas.daggerrxtemp.mvp.presenter.CakePresenter;
import com.example.nlbochas.daggerrxtemp.mvp.view.MainView;
import com.example.nlbochas.daggerrxtemp.utils.Utilities;

import java.util.List;

import javax.inject.Inject;
import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView, CakesAdapter.OnCakeClickListener {

    @Bind(R.id.cake_list) protected RecyclerView cakeList;
    @Inject protected CakePresenter presenter;
    private CakesAdapter cakesAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initCakeList();
        loadCakes();
    }

    private void loadCakes() {
        if (Utilities.isOnline(this)) {
            presenter.getCakes();
        } else {
            presenter.getSavedCakes();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reload:
                loadCakes();
                return true;
            case R.id.action_about:
                showAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
      showToastMessage("^_^");
    }

    @Override
    public void resolveDaggerDependencies() {
        DaggerCakeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .cakeModule(new CakeModule(this))
                .build().inject(this);
    }

    private void initCakeList() {
        cakeList.setHasFixedSize(true);
        cakeList.setVerticalScrollBarEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cakesAdapter = new CakesAdapter(getLayoutInflater());
        cakesAdapter.setCakeClickListener(this);
        cakeList.setLayoutManager(layoutManager);
        cakeList.setAdapter(cakesAdapter);
    }

    @Override
    public void onCakeLoaded(List<MyCake> myCakes) {
        cakesAdapter.setMyCakeList(myCakes);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void showToastMessage(String message) {
        showToast(message);
    }

    @Override
    public void clearCakeList() {
        cakesAdapter.clearCakeList();
    }

    @Override
    public void onClick(View v, MyCake myCake, int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.CAKE, myCake);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "cakeImageAnimation");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
