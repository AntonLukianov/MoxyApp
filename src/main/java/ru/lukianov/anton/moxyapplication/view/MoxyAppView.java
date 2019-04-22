package ru.lukianov.anton.moxyapplication.view;

import com.arellomobile.mvp.MvpView;

import org.json.simple.JSONObject;

public interface MoxyAppView extends MvpView {

    void setViewArray(JSONObject jsonObject);

}
