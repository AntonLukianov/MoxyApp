package ru.lukianov.anton.moxyapplication.presenter;

import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ru.lukianov.anton.moxyapplication.model.JsonReader;
import ru.lukianov.anton.moxyapplication.view.MoxyAppView;


@InjectViewState
public class MoxyAppPresenter extends MvpPresenter<MoxyAppView> {

    public MoxyAppPresenter(){

        final String url = "https://prnk.blob.core.windows.net/tmp/JSONSample.json";
        new AsyncTask<Void, Void, Void>(){
            JSONObject jsonObject = null;

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    jsonObject= JsonReader.readJsonFromUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    // pass
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                getViewState().setViewArray(jsonObject);
            }
        }.execute();
    }
    }


