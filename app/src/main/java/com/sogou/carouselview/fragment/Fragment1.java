package com.sogou.carouselview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
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
    private Button button;
    private int topMargin = 0;
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
        button = (Button) rootView.findViewById(R.id.bt_click);
        carouselViewPager = (CarouselViewPager) rootView.findViewById(R.id.carousel_viewpager);
        dotView = (DotView) rootView.findViewById(R.id.dot_view);
        final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)button.getLayoutParams();

        topMargin= lp.topMargin;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button, "accelerate", 0.0f, 1.0f)
                        .setDuration(500);
                objectAnimator.start();
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float val = (float) animation.getAnimatedValue();
                        lp.setMargins(lp.leftMargin, (int) (topMargin + val * 80),
                                lp.rightMargin, lp.bottomMargin);
                        button.setLayoutParams(lp);
                    }
                });
            }
        });
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
