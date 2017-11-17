package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.HelloWorld;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.utils.layoututils.MyFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerfectInformationActivity extends AppCompatActivity {
    private MyFlowLayout myFlowLayout;
    private String[] strData;
    private String mPerfectInfor;
    private TextView mActivity_next_tv;
    private HashMap<Integer, View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfect_information);
        initView();
        initData();
        setData();

    }

    private void setData() {
        viewList = new HashMap<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 15, 20, 5);
        final int count = 0;
        for (int i = 0; i < strData.length; i++) {
            final TextView view = new TextView(this);
            view.setText(strData[i]);
            view.setTextSize(15);
            view.setTag(false);
            view.setTag(R.id.tv_item1, i);
            final int finalI = i;
            view.setTextColor(Color.parseColor("#555555"));
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selected_red_shapter));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean flag = (boolean) v.getTag();
                    int nuber = (int) v.getTag(R.id.tv_item1);
                    if (viewList.size() > 0) {
                        for (int j = 0; j <viewList.size(); j++) {
                           TextView viewss= (TextView) viewList.get(0);
                            mPerfectInfor = null;
                            viewss.setBackgroundDrawable(PerfectInformationActivity.this.getResources().getDrawable(R.drawable.selected_red_shapter));
                            viewss.setTextColor(Color.parseColor("#555555"));

                        }
                    }
                    if (!flag) {
                        viewList.put(0, v);
                        mPerfectInfor = strData[nuber];
                        view.setBackgroundDrawable(PerfectInformationActivity.this.getResources().getDrawable(R.drawable.selected_red_shapter_ager));
                        view.setTextColor(Color.WHITE);
                        view.setTag(true);
                    } else {
                        mPerfectInfor = null;
                        view.setBackgroundDrawable(PerfectInformationActivity.this.getResources().getDrawable(R.drawable.selected_red_shapter));
                        view.setTextColor(Color.parseColor("#555555"));
                        view.setTag(false);
                    }
                }
            });
            myFlowLayout.addView(view, params);
        }
    }

    private void initData() {
        strData = new String[7];
        strData[0] = "比呢个主意";
        strData[1] = "病症二很tent";
        strData[2] = "病症三东南";
        strData[3] = "病症";
        strData[4] = "病症三东南在东莞是垃圾烦死了";
        strData[5] = "love";
        strData[6] = "love你有个你";


    }

    private void initView() {
        myFlowLayout = (MyFlowLayout) findViewById(R.id.perfect_flowlayout);
        mActivity_next_tv = (TextView) findViewById(R.id.activity_next_tv);
        mActivity_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPerfectInfor != null) {
                    mActivity_next_tv.setText(mPerfectInfor);
                    startActivity(new Intent(PerfectInformationActivity.this, LastInformationActivity.class));
                }
            }
        });
    }

}
