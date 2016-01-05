package com.sogou.carouselview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sogou.carouselview.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lianghenghui on 2015/12/30.
 */
public class MenuRightFragment extends Fragment {
    private View mView;
    private ListView mList;
    private ListAdapter mAdapter;
    private List<String> menuContent = Arrays.asList("聊天", "发现", "通讯录", "朋友圈", "订阅号");
    @Override
    public void onDestroyView() {

        super.onDestroyView();

        if(mView != null){
            if(mView.getParent()!=null) {
                ((ViewGroup) (mView.getParent())).removeView(mView);
            }
        }
    }

    public MenuRightFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            initView(inflater,container);
        }
        return mView;
    }

    public void initView(LayoutInflater inflater,ViewGroup container){
        mView = inflater.inflate(R.layout.right_menu_fragment, container,false);
        mList = (ListView)mView.findViewById(R.id.right_list);
        mAdapter = new ArrayAdapter(getActivity(),R.layout.left_menu_list_item,menuContent);
        mList.setAdapter(mAdapter);
    }
}
