package com.example.ls.shoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.utils.MyViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Welcome extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] mImgIds;
    @Bind(R.id.myvp_guide)
    MyViewPager myvpGuide;
    private MyViewPager mViewPager;
    private ImageView[] mPoints;
    private boolean misScrolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();
    }
    private void initView() {
        mImgIds = new int[]{R.drawable.viewpager1, R.drawable.viewpager2, R.drawable.viewpager3};
        mViewPager = (MyViewPager) findViewById(R.id.myvp_guide);
    }

    private void initData() {
        //设置切换效果
//        mViewPager.setTransitionEffect(TransitionEffect.Stack);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(Welcome.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mViewPager.setObjectForPosition(imageView, position);
                return imageView;
            }
        });
        mViewPager.addOnPageChangeListener(this);
    }

    public void jump(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                misScrolled = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                misScrolled = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !misScrolled) {
                    startActivity(new Intent(this, MainActivity.class));
                    Welcome.this.finish();
                }
                misScrolled = true;
                break;
        }
    }
}
