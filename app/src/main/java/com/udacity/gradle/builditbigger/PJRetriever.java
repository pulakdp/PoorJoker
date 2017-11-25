package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Code author: PulakDebasish
 */

public class PJRetriever extends AsyncTask <Context, Void, String> {

    private Context activityContext;
    //Can't do 'private static MyApi myApiService = null;' :(  I must be missing something really silly
    @Override
    protected String doInBackground(Context... contexts) {
        activityContext = contexts[0];
        return null;
    }
}
