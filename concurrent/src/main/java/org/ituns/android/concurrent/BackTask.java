package org.ituns.android.concurrent;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public final class BackTask {
    private static final String TAG = "BackTask";
    private static final Object mToken = new Object();

    private Handler mHandler;

    public BackTask() {
        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        mHandler = new Handler(thread.getLooper());
    }

    public boolean post(Runnable runnable) {
        if(runnable == null) {
            Log.i(TAG, "post:runnable is null.");
            return false;
        }

        Handler handler = mHandler;
        if(handler == null) {
            Log.i(TAG, "post:handler is null.");
            return false;
        }

        try {
            return handler.post(runnable);
        } catch (Throwable e) {
            Log.i(TAG, "post:exception", e);
            return false;
        }
    }

    public boolean postDelayed(Runnable runnable, long delayMillis) {
        if(runnable == null) {
            Log.i(TAG, "postDelayed:runnable is null.");
            return false;
        }

        Handler handler = mHandler;
        if(handler == null) {
            Log.e(TAG, "postDelayed:handler is null.");
            return false;
        }

        try {
            return handler.postDelayed(runnable, delayMillis);
        } catch (Throwable e) {
            Log.e(TAG, "postDelayed:exception", e);
            return false;
        }
    }

    public boolean postSingleDelayed(Runnable runnable, long delayMillis) {
        removeCallbacks(runnable);
        return postDelayed(runnable, delayMillis);
    }

    public void removeCallbacks(Runnable runnable) {
        if(runnable == null) {
            Log.i(TAG, "removeCallbacks:runnable is null.");
            return;
        }

        Handler handler = mHandler;
        if(handler == null) {
            Log.e(TAG, "removeCallbacks:handler is null.");
            return;
        }

        try {
            handler.removeCallbacks(runnable, mToken);
        } catch (Throwable e) {
            Log.e(TAG, "removeCallbacks:exception", e);
        }
    }

    public void release() {
        Handler handler = mHandler;
        if(handler != null) {
            handler.getLooper().quit();
            mHandler = null;
        }
    }
}
