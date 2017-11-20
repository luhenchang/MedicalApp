package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.adapter.SymptomSelectAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class SymptomSelectActivity extends AppCompatActivity {
    private ListView symptom_lv;
    private SymptomSelectAdapter mSyAdapter;
    private List<String> mData;
    private TwinklingRefreshLayout mSwipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_symptom_select);
        initView();
        initData();
        setAdapters();
    }

    private void setAdapters() {
        symptom_lv.setAdapter(mSyAdapter);

    }

    private void initData() {
        mData = new ArrayList<>();
        mSyAdapter = new SymptomSelectAdapter(this, mData);
        mData.add("眼睛疼痛");
        mData.add("眼睛算样");
        mData.add("眼睛干涩");
        mData.add("眼睛枯萎");
        mData.add("眼睛刺痛");
        mData.add("眼睛失明");
        mData.add("眼睛近视");
        mData.add("眼睛原始");
        mData.add("眼睛模糊");
        mData.add("眼睛枯萎");
        mData.add("眼睛刺痛");
        mData.add("眼睛失明");
        mData.add("眼睛近视");
        mData.add("眼睛原始");
        mData.add("眼睛模糊");
        mSyAdapter.notifyDataSetChanged();

    }

    private void initView() {
        mSwipeLayout= (TwinklingRefreshLayout) findViewById(R.id.ac_searchtoserver_swp);
        //下拉刷新
        mSwipeLayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener() {
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
        symptom_lv = (ListView) findViewById(R.id.symptom_lv);
        symptom_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SymptomSelectActivity.this,PerfectInformationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
}
