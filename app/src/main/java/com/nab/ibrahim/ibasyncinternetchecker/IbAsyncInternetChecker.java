package com.nab.ibrahim.ibasyncinternetchecker;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class IbAsyncInternetChecker extends Service implements IbAsyncInternet.onIbAsyncInternetChanged {
  private   Handler handler;
  private   TimerTask timerTask;
  private   Timer timer;

    public IbAsyncInternetChecker() {
    }

    @Override
    public void onCreate() {
        IbAsyncInternet.initService();
        IbAsyncInternet.getIbAsyncInternetService();
        IbAsyncInternet.getIbAsyncInternetService().registerIbAsyncInternetListener(this);
        // internetHandler.registerListener(this);
        //  timer = new Timer();
        handler = new Handler();
        timer = new Timer();

        //problem
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new My().execute();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

        };
        timer.schedule(timerTask, 0, 4000);

        super.onCreate();
    }


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            onTaskRemoved(intent);
        }
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent intent = new Intent(getApplicationContext(), this.getClass());
        intent.setPackage(this.getPackageName());
        startService(intent);
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onInternetChanged(boolean flag) {

    }


    private class My extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean reachable = false;
            try {
                Process p1 = Runtime.getRuntime().exec("ping -c 3 google.com");
                int returnVal = p1.waitFor();
                reachable = (returnVal == 0);
            } catch (IOException e) {
                //  Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (InterruptedException e) {
                //  Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            if (reachable) {

            }
            return reachable;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            IbAsyncInternet.getIbAsyncInternetService().notifyAboutIbrahimsAsyncInternetChanged(aBoolean);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
