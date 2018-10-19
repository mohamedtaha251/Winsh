package com.winsh.winsh.sync.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.winsh.winsh.utils.JSONParser;
import com.winsh.winsh.utils.PathDrawer;

public class connectAsyncTask extends AsyncTask<Void, Void, String> {
    private ProgressDialog progressDialog;
    String url;
    Context context;
    GoogleMap mMap;

    public connectAsyncTask(String urlPass, Context context, GoogleMap mMap) {
        this.url = urlPass;
        this.context = context;
        this.mMap = mMap;

    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching route, Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        //send http request and return json string result
        return JSONParser.getJSONFromUrl(url);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.hide();
        if (result != null) {
            PathDrawer.drawPath(result, mMap);
        }
    }
}