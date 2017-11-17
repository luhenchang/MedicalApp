package com.example.ls.shoppingmall.user.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.fragment.ActionFragment;
import com.example.ls.shoppingmall.user.fragment.MessageFragment;
import com.example.ls.shoppingmall.user.fragment.UpdateTextResult;
import com.example.ls.shoppingmall.user.fragment.ViewPagerFragmentAdapter;
import com.example.ls.shoppingmall.utils.MyViewPager;
import com.example.ls.shoppingmall.utils.layoututils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements UpdateTextResult{
    private TabLayout mTa;
    private TextView tv_edetor;
    private ViewPager mViewPager;
    private String[] title = {
            "活动公告",
            "我的消息"};
    private List<BaseFragment> mList;
    private ViewPagerFragmentAdapter adapter;
    EditMyFragmentItem editMyFragmentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message);
        initView();
        initData();
        setData();
    }
   //这里回调用来更新我编辑和取消按钮的
    @Override
    public void updateText(String flag, String text) {
        tv_edetor.setText(text);
    }
    //接口用来通知碎片我点击编辑了让去执行显示check
    public interface EditMyFragmentItem {
        void EditeItem();
    }

    private void setData() {

        adapter = new ViewPagerFragmentAdapter(mList, title, getSupportFragmentManager());
        //1.TabLayout关联Viewpager
        mTa.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition(), true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //2.设置ViewPager关联TabLayout
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTa));

        //设置tablLayout的标签来自于PagerAdapter
        mTa.setTabsFromPagerAdapter(adapter);
        mViewPager.setAdapter(adapter);
    }

    private void initData() {
        mList = new ArrayList<>();
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setEdeterInterface(MessageActivity.this);
        ActionFragment actionFragment = new ActionFragment();
        editMyFragmentItem = messageFragment;
        mList.add(messageFragment);
        mList.add(actionFragment);
    }

    private void initView() {
        mTa = (TabLayout) findViewById(R.id.at_message_tb);
        mViewPager = (ViewPager) findViewById(R.id.ac_message_vp);
        tv_edetor= (TextView) findViewById(R.id.tv_edetor);
        tv_edetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMyFragmentItem.EditeItem();
            }
        });
    }
}