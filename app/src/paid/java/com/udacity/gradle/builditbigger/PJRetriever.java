package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.pjdisplay.PJDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Code author: PulakDebasish
 */

public class PJRetriever extends AsyncTask<Void, Void, String> {

    private Context activityContext;
    private static MyApi myApiService = null;
    private ProgressBar pjProgress;

    PJRetriever(Context activityContext, ProgressBar pjProgress) {
        this.activityContext = activityContext;
        this.pjProgress = pjProgress;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (pjProgress != null)
            pjProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        try {
            return myApiService.showPJ().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(final String joke) {
        super.onPostExecute(joke);
        hideProgressBar();
        startIntent();
    }

    private void startIntent(String joke) {
        if (joke != null) {
            activityContext.startActivity(
                    new Intent(activityContext, PJDisplayActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra(PJDisplayActivity.JOKE_TAG, joke));
        }
    }

    private void hideProgressBar() {
        if (pjProgress != null)
            pjProgress.setVisibility(View.GONE);
    }
}