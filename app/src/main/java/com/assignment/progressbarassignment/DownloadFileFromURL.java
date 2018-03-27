package com.assignment.progressbarassignment;

import android.os.AsyncTask;

/**
 * Created by Chandan Jha on 27/03/18.
 */

public class DownloadFileFromURL extends AsyncTask<Void, Integer, Void> {

    private UpdateListener mUpdateListener;

    DownloadFileFromURL(UpdateListener updateListener) {
        mUpdateListener = updateListener;
    }

    @Override
    protected Void doInBackground(Void... params) {

        int i = 0;
        while (i <= 10) {
            try {
                Thread.sleep(1000);
                publishProgress(i);
                i++;
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mUpdateListener.updateProgressBar(1);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mUpdateListener.finishUpdate();
    }
}
