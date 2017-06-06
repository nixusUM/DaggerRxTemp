package com.example.nlbochas.daggerrxtemp.mapper;

import com.example.nlbochas.daggerrxtemp.mvp.model.Cake;
import com.example.nlbochas.daggerrxtemp.mvp.model.CakesResponse;
import com.example.nlbochas.daggerrxtemp.mvp.model.MyCake;
import com.example.nlbochas.daggerrxtemp.mvp.model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CakeMapper {

    @Inject
    public CakeMapper() {
    }

    public List<MyCake> mapCakes(Storage storage, CakesResponse response) {
        List<MyCake> cakeList = new ArrayList<>();
        if (response != null) {
            List<Cake> cakesResponses = response.getCakes();
            if (cakesResponses != null) {
                for (Cake cake : cakesResponses) {
                    MyCake myCake = new MyCake();
                    myCake.setId(cake.getId());
                    myCake.setDetailDescription(cake.getDetailDescription());
                    myCake.setImageUrl(cake.getImage());
                    myCake.setPreviewDescription(cake.getPreviewDescription());
                    myCake.setTitle(cake.getTitle());
                    storage.addCake(myCake);
                    cakeList.add(myCake);
                }
            }
        }
        return cakeList;
    }
}
