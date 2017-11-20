package com.example.ls.shoppingmall.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.fragment.CommunityFragment;
import com.example.ls.shoppingmall.home.fragment.HomeFragment;
import com.example.ls.shoppingmall.shoppingcat.fragment.ShoppingCarFragment;
import com.example.ls.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    /*
    * 装多个碎片实例
    * */
    private ArrayList<BaseFragment> mFragments;
    private int position;
    //上次显示的fragment
    private BaseFragment tempFragment;

    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       // ViewColor.setColor(this, Color.RED);
        ButterKnife.bind(this);
        /*初始化Frangment
        * */
        initFragment();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_community://发现
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 1;
                        break;
                    case R.id.rb_user://用户中心
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置的区不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                //第一个参数是上一次显示的
                //第二个参数是当前正要显示的
                switchFragment(tempFragment, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    /**
     * 切换碎片
     *
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transation.hide(fromFragment);
                    }
                    transation.add(R.id.frameLayout,nextFragment).commit();
                }else{
                    //隐藏当前的Fragmeent
                    if(fromFragment!=null){
                        transation.hide(fromFragment);
                    }
                    transation.show(nextFragment).commit();
                }
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    private BaseFragment getFragment(int position) {
        if (mFragments != null && mFragments.size() > 0) {
            BaseFragment baseFragment = mFragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /*
    * 添加需要按照顺序
    * */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ShoppingCarFragment());
        mFragments.add(new CommunityFragment());
        mFragments.add(new UserFragment());
    }
}
