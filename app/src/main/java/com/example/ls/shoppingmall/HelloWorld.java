package com.example.ls.shoppingmall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ls.shoppingmall.home.activity.LastInformationActivity;
import com.example.ls.shoppingmall.utils.layoututils.ArcProgressbar;

import java.util.Timer;
import java.util.TimerTask;

public class HelloWorld extends AppCompatActivity {

    private ArcProgressbar arcProgressbar;
    private int mProgress;
    private TextView hellowrod_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        arcProgressbar = (ArcProgressbar)findViewById(R.id.arcProgressbar);
        hellowrod_tv = (TextView) findViewById(R.id.hellowrod_tv);
        hellowrod_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelloWorld.this, LastInformationActivity.class));
            }
        });
        mProgress = 1;
        arcProgressbar.reset();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (0 < (mProgress / 3.6) && (mProgress / 3.6) <= 59) {
                    arcProgressbar.setmArcColor(Color.parseColor("#fb7673"));
                    hellowrod_tv.setTextColor(Color.parseColor("#fb7673"));
                } else if (60 < (mProgress / 3.6) && (mProgress / 3.6) <= 79) {
                    arcProgressbar.setmArcColor(Color.parseColor("#fb7673"));
                    hellowrod_tv.setTextColor(Color.parseColor("#fb7673"));
                } else if (80 < (mProgress / 3.6) && (mProgress / 3.6) <= 100) {
                    arcProgressbar.setmArcColor(Color.parseColor("#fb7673"));
                    hellowrod_tv.setTextColor(Color.parseColor("#fb7673"));
                }
                if (msg.what == 0x1223) {
                    arcProgressbar.setProgress(mProgress * (1));
                    hellowrod_tv.setText(("" + (int) (mProgress / 3.6)+"%"));
                } else if (msg.what == 0x1224) {
                    hellowrod_tv.setText("%");
                }
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();

                if (mProgress < (int) (((float) 360 / 100) * 90)) {
                    msg.what = 0x1223;
                    mProgress += 10;
                } else {
                    msg.what = 0x1224;
                    this.cancel();
                }
                handler.sendMessage(msg);
            }
        }, 0, 5);
    }
}
