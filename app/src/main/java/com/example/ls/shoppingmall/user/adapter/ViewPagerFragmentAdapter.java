package com.example.ls.shoppingmall.user.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ls.shoppingmall.base.BaseFragment;

import java.util.List;

/**
 * Created by ls on 2017/11/16.
 */

public class ViewPagerFragmentAdapter  extends FragmentPagerAdapter {
    private List<BaseFragment> mList;
    private String[] title=new String[]{};
    public ViewPagerFragmentAdapter(List<BaseFragment> mList,String[] title,FragmentManager fm) {
        super(fm);
        this.mList = mList;
        this.title=title;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
