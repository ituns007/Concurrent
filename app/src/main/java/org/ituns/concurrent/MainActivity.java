package org.ituns.concurrent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.ituns.android.concurrent.AsyncTask;
import org.ituns.android.concurrent.BackTask;
import org.ituns.android.concurrent.MainTask;

public class MainActivity extends AppCompatActivity {
    private BackTask backTask;
    private MainTask mainTask;
    private AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backTask = new BackTask();
        mainTask = new MainTask();
        asyncTask = new AsyncTask();

        backTask.postDelayed(() -> {
            Log.i("MainActivity", "Back Task Executed.");
        }, 5000);

        backTask.postDelayed(() -> {
            Log.i("MainActivity", "Main Task Executed.");
        }, 2500);

        asyncTask.execute(() -> {
            Log.i("MainActivity", "Async Task Executed.");
        });
    }
}
