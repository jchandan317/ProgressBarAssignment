package com.assignment.progressbarassignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private EventBus mEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowProgress = findViewById(R.id.btnProgressBar);
        Button btnHi = findViewById(R.id.btnHi);
        mProgressBar = findViewById(R.id.progress);

        btnShowProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // starting new Async Task
                new DownloadFileFromURL(new UpdateListener() {
                    @Override
                    public void updateProgressBar(int value) {
                        if (mEventBus != null && mEventBus.isRegistered(MainActivity.this)) {
                            mEventBus.postSticky(value);
                        }
                    }
                }).execute();
            }
        });

        btnHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hi User", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mEventBus == null) mEventBus = EventBus.getDefault();
        if (mEventBus != null && !mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mEventBus != null)
            mEventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(@NonNull Integer value) {
        mProgressBar.setProgress(mProgressBar.getProgress() + value);
    }


}
