package com.example.ls.shoppingmall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.ls.shoppingmall.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 发现页面的Fragment
 */

public class CommunityFragment extends BaseFragment {
    private TextView mtv;

    @Override
    public View initView() {
        Log.e(TAG, "发现页面的Fagemnt被初始化了");
        mtv = new TextView(mContext);
        mtv.setGravity(Gravity.CENTER);
        mtv.setTextSize(26);
        return mtv;
    }

    @Override
    public void initData() {
        Log.e(TAG, "发现页面的Fragment的UI背初始化了");
        mtv.setText("发现页面内容");


    }
}
