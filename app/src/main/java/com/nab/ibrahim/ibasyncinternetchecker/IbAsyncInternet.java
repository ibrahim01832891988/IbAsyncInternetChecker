package com.nab.ibrahim.ibasyncinternetchecker;
import java.util.ArrayList;

public class IbAsyncInternet {
    private static IbAsyncInternet ibAsyncInternet;
    ArrayList<onIbAsyncInternetChanged> registeredIbAsync_InternetListener = new ArrayList<onIbAsyncInternetChanged>();
    public static void initService() {
        if (ibAsyncInternet == null) {
            ibAsyncInternet = new IbAsyncInternet();
        }
    }
    public static IbAsyncInternet getIbAsyncInternetService() {
        return ibAsyncInternet;
    }
    public void registerIbAsyncInternetListener(onIbAsyncInternetChanged ibInternetListener) {
        this.registeredIbAsync_InternetListener.add(ibInternetListener);
    }
    public void notifyAboutIbrahimsAsyncInternetChanged(boolean flag) {
        for (onIbAsyncInternetChanged ibInternetListener : registeredIbAsync_InternetListener) {
            ibInternetListener.onInternetChanged(flag);
        }
    }
    public interface onIbAsyncInternetChanged {
        void onInternetChanged(boolean flag);
    }
}
