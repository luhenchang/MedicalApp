package com.example.ls.shoppingmall.home.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.adapter.LastInforAdapter;
import com.example.ls.shoppingmall.home.bean.LastInformationBean;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class LastInformationActivity extends AppCompatActivity {
    private RecyclerView mRecylerViwe;
    private LastInformationBean bean;
    private LastInforAdapter lastAdapter;
    private TwinklingRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_last_information);
        initView();
        initData();
        setData();
    }

    private void setData() {
        mRecylerViwe.setAdapter(lastAdapter);
        mRecylerViwe.setNestedScrollingEnabled(false);
    }

    private void initData() {
        bean = new LastInformationBean();
        lastAdapter = new LastInforAdapter(this, bean);
        for (int i = 0; i < 3; i++) {
            bean.mList1.add("第1个Item=" + i);
            bean.mList2.add("第2个Item=" + i);
            bean.mList3.add("第3个Item=" + i);
            bean.mList4.add("第4个Item=" + i);

        }
        lastAdapter.notifyDataSetChanged();

    }

    private void initView() {
        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        mRecylerViwe = (RecyclerView) findViewById(R.id.activity_last_infor_rv);
        mRecylerViwe.setLayoutManager(new LinearLayoutManager(this));
        //下拉刷新
        refreshLayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            //上啦加载
            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });

    }
}
