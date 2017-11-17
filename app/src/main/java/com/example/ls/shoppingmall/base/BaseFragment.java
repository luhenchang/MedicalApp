package com.example.ls.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ls on 2017/11/8.
 * 作用基类Fragment
 * 首页：
 * 分类：
 * 发现：
 * 购物车：
 * 用户中心：
 */

public abstract class  BaseFragment extends Fragment {
    protected Context mContext;

    /*
    * 当该类被系统创建时候被回调。
    *
    * */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /*
    *抽象类，有孩子实现
    *
    * */
    public abstract View initView();

    /**
     * 当Activity被创建之后调用这个方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    /*
    * 当子类需要联网请求数据的时候，可以重写这个方法，给方法中联网请求。
    * */
    public abstract void initData();





}
