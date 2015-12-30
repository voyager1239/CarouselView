package com.sogou.carouselview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CarouselViewPager extends ViewPager {
    private static final int MSG_WHAT_LUNBO_SCROLL = 0x0011;
    private static final long LUNBO_SCROLL_DELAY = 10000;
    private DotView dotView;

    public void setDotView(final DotView dotView) {
        this.dotView = dotView;
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                dotView.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    //stop
                    startAutoScrolling();
                } else if (state == 2 || state == 0) {
                    stopAutoScrolling();
                }
            }
        });
    }

    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_LUNBO_SCROLL:
                    scrollLunboToNext();
                    startAutoScrolling();
                    break;
            }
        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoScrolling();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoScrolling();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoScrolling();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startAutoScrolling();
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void stopAutoScrolling() {
        mainHandler.removeMessages(MSG_WHAT_LUNBO_SCROLL);
    }

    public void startAutoScrolling() {
        stopAutoScrolling();
        mainHandler.sendEmptyMessageDelayed(MSG_WHAT_LUNBO_SCROLL, LUNBO_SCROLL_DELAY);
    }

    private void scrollLunboToNext() {
        int currentItemIndex = getCurrentItem();
        setCurrentItem(++currentItemIndex, true);
    }

    public CarouselViewPager(Context context) {
        super(context);
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }
}
