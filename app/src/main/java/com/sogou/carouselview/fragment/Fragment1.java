package com.sogou.carouselview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sogou.carouselview.CarouselAdapter;
import com.sogou.carouselview.CarouselViewPager;
import com.sogou.carouselview.DotView;
import com.sogou.carouselview.R;

import java.util.ArrayList;

/**
 * Created by lianghenghui on 2015/12/23.
 */
public class Fragment1 extends Fragment {

    private CarouselViewPager carouselViewPager;
    private DotView dotView;
    private View rootView;
    private final int[] images = {R.drawable.a, R.drawable.c,
            R.drawable.d, R.drawable.e};
    private ArrayList<ImageView> imageList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment1, container, false);
        imageList = new ArrayList<>();
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(i);
            imageList.add(imageView);
        }
        initWidget();
        loadCarouselView();
        return rootView;
    }

    private void initWidget() {
        carouselViewPager = (CarouselViewPager) rootView.findViewById(R.id.carousel_viewpager);
        dotView = (DotView) rootView.findViewById(R.id.dot_view);
    }

    private void loadCarouselView() {
        carouselViewPager.setAdapter(new CarouselAdapter(imageList));
        dotView.setNum(imageList.size());
        carouselViewPager.setDotView(dotView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
