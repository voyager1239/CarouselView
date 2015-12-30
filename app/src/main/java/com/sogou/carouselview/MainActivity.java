package com.sogou.carouselview;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.sogou.carouselview.fragment.*;

public class MainActivity extends FragmentActivity {

    private RelativeLayout scroll_tab;
    private RadioGroup radioGroup_content;
    private ImageView iv_indicator;
    private ImageView iv_left;
    private ImageView iv_right;
    private ViewPager mPager;
    private LayoutInflater mInflater;
    private MyFragmentPagerAdapter mAdapter;
    private SyncHorizontalScrollView horizontalScrollView;
    public static String[] tab_title = {"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8","选项9"};
    private int indicatorWidth;
    private int currentIndicatorLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initView();
    }

    private void initWidget() {
        scroll_tab = (RelativeLayout) findViewById(R.id.scroll_tab);
        radioGroup_content = (RadioGroup) findViewById(R.id.rg_content);
        iv_indicator = (ImageView) findViewById(R.id.iv_indicator);
        iv_left = (ImageView) findViewById(R.id.iv_nav_left);
        iv_right = (ImageView) findViewById(R.id.iv_nav_right);
        mPager = (ViewPager) findViewById(R.id.pager);
        horizontalScrollView = (SyncHorizontalScrollView) findViewById(R.id.mHsv);
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        indicatorWidth = dm.widthPixels / 4;
        ViewGroup.LayoutParams cursor_Params = iv_indicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;
        iv_indicator.setLayoutParams(cursor_Params);
        horizontalScrollView.setSomeParam(scroll_tab, iv_left, iv_right, this);
        mInflater = LayoutInflater.from(this);
        initHorizontalScrollView();
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (radioGroup_content != null && radioGroup_content.getChildCount() > position) {
                    ((RadioButton) radioGroup_content.getChildAt(position)).performClick();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroup_content.getChildAt(checkedId) != null) {
                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft,
                            ((RadioButton) radioGroup_content.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);

                    //执行位移动画
                    iv_indicator.startAnimation(animation);

                    mPager.setCurrentItem(checkedId);    //ViewPager 跟随一起 切换

                    //记录当前 下标的距最左侧的距离
                    currentIndicatorLeft = ((RadioButton) radioGroup_content.getChildAt(checkedId)).getLeft();
                    //控制ScrollView跟随滑动，getChildAt(2)是根据具体情况计算的
                    horizontalScrollView.smoothScrollTo(
                            (checkedId > 1 ? ((RadioButton) radioGroup_content.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) radioGroup_content.getChildAt(2)).getLeft(), 0);
                }
            }
        });
    }


    private void initHorizontalScrollView() {
        radioGroup_content.removeAllViews();
        for (int i = 0; i < tab_title.length; i++) {
            RadioButton radioButton = (RadioButton) mInflater.inflate(R.layout.radiogroup_item, null);
            radioButton.setId(i);
            radioButton.setText(tab_title[i]);
            radioButton.setLayoutParams(new ActionBar.LayoutParams(indicatorWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup_content.addView(radioButton);
        }
    }

    public static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fg = null;
            switch (position) {
                case 0:
                    fg = new Fragment1();
                    break;
                default:
                    fg = new Fragment2();
                    break;
            }
            return fg;
        }


        @Override
        public int getCount() {
            return tab_title.length;
        }
    }
}
