package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.example.android.jokedisplay.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private getDataReceiver mGeteDataReceiver;
    private Exception mException = null;

    public interface getDataReceiver{
        void data(String s, Exception e);
    }

    public EndpointAsyncTask setDataReceiver(getDataReceiver task){
        mGeteDataReceiver = task;
        return this;
    }

    @Override
    public String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        context = params[0];
        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null){
            try {
                Toast.makeText(context, "No cloud connection", Toast.LENGTH_LONG).show();
                throw new Exception("result are null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.BOOLEAN_KEY, Constant.IS_FREE_VERSION);
            intent.putExtra(JokeActivity.KEY, result);
            context.startActivity(intent);
        }
    }

}
