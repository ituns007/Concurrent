package org.ituns.android.concurrent;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class AsyncTask {
    private static final String TAG = "AsyncTask";

    private ExecutorService mExecutorService;

    public AsyncTask() {
        mExecutorService = new ThreadPoolExecutor(
                0, 64,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()
        );
    }

    public boolean execute(Runnable runnable) {
        ExecutorService service = mExecutorService;
        if(service == null) {
            Log.e(TAG, "executor:service is null.");
            return false;
        }

        try {
            service.execute(runnable);
            return true;
        } catch (Throwable e) {
            Log.e(TAG, "execute:exception", e);
            return false;
        }
    }

    public void release() {
        ExecutorService service = mExecutorService;
        if(service != null) {
            service.shutdown();
            mExecutorService = null;
        }
    }
}
