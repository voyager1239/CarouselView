package com.sogou.carouselview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sogou.carouselview.R;

/**
 * Created by lianghenghui on 2015/12/23.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        TextView tv_tabName = (TextView) rootView.findViewById(R.id.tv_tabName);

        Bundle bundle = getArguments();

        tv_tabName.setText("438914-fvf-sdfk;kz");

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

}
