package com.example.ls.shoppingmall.shoppingcat.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.ls.shoppingmall.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 购物车页面的Fragment
 */

public class ShoppingCarFragment extends BaseFragment {
    private TextView mtv;

    @Override
    public View initView() {
        Log.e(TAG, "购物车的Fagemnt被初始化了");
        mtv = new TextView(mContext);
        mtv.setGravity(Gravity.CENTER);
        mtv.setTextSize(26);
        return mtv;
    }

    @Override
    public void initData() {
        Log.e(TAG, "购物车的Fragment的UI背初始化了");
        mtv.setText("购物车面内容");


    }
}
