package com.sogou.carouselview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class CarouselAdapter extends PagerAdapter {
    private List<ImageView> imageViewList;
    public CarouselAdapter(List<ImageView> appInfos) {
        this.imageViewList = appInfos;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
        // position: viewpager上的位置
        // 给container添加内容
        container.addView(imageViewList.get(position % imageViewList.size()));
        return imageViewList.get(position % imageViewList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}