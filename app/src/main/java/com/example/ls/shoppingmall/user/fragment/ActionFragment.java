package com.example.ls.shoppingmall.user.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;

/**
 * Created by ls on 2017/11/16.
 */

public class ActionFragment extends BaseFragment {
    @Override
    public View initView() {
        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_action_layout,null);

        return view;
    }

    @Override
    public void initData() {

    }
}
