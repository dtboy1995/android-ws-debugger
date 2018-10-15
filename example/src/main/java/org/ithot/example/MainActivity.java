package org.ithot.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.ithot.debug.wslog.Debugger;

public class MainActivity extends Activity {

    static final int INTERVAL = 1000;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Debugger.print(String.valueOf(System.currentTimeMillis()));
            handler.postDelayed(this, INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        frame.setBackgroundColor(Color.WHITE);
        TextView tv = new TextView(this);
        tv.setText(Debugger.getIpAddress(this));
        tv.setTextSize(50);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.CYAN);
        frame.addView(tv);
        frame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) tv.getLayoutParams();
        p.gravity = Gravity.CENTER_HORIZONTAL;
        setContentView(frame);

        Debugger.startDebugger(this);
        handler.postDelayed(runnable, INTERVAL);
    }

    @Override
    protected void onDestroy() {
        Debugger.stopDebugger(this);
        super.onDestroy();
    }
}
