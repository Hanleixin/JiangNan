package com.bwei.newhan.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwei.newhan.R;
import com.bwei.newhan.home.adapter.HomeListAdapter;
import com.bwei.newhan.home.bean.NewsContext;
import com.bwei.newhan.home.model.ApplicationConstants;
import com.bwei.newhan.home.model.CallbackNewsData;
import com.bwei.newhan.home.model.HttpUtist;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class HomeTabFragment extends Fragment implements PullToRefreshListView.OnRefreshListener2<ListView>, CallbackNewsData<NewsContext> {

    View view;
    private String mParam;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int currentPage = 0;//页数

    private PullToRefreshListView pullToRefreshListView;

    private boolean isNeedClaer = false;//下拉刷新时清空历史的变量

    private HomeListAdapter pullAdapter;//listview的适配器

    private String path;

    private static final String TAG=HomeTabFragment.class.getSimpleName();

    NewsContext news;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.hometab_item,null);

        //获取相关控件id的方法
        initView();

        //添加关于PullToRefresh相关适配器的方法
        initData();

        path = ApplicationConstants.getUrl(mParam2,currentPage);
        HttpUtist.loadDataFromServer(path,NewsContext.class,this);

        return view;
    }

    /**
     * 获取相关控件的ID
     * 并添加PullToRefresh的两种模式（上拉刷新，下拉加载）*/
    private void initView() {

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_home);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(this);
    }

    /**
     * 添加关于PullToRefresh的相关适配器*/
    private void initData() {

        pullAdapter = new HomeListAdapter(getActivity());
        pullToRefreshListView.setAdapter(pullAdapter);
    }

    public static HomeTabFragment newInstance(String paraml, String param2){

        HomeTabFragment homeTabFragment = new HomeTabFragment();

        //传值
        Bundle bundle = new Bundle();

        bundle.putString(ARG_PARAM1,paraml);
        bundle.putString(ARG_PARAM2,param2);

        homeTabFragment.setArguments(bundle);

        return homeTabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){

            mParam = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void success(ArrayList<NewsContext> newsContents) {

        pullAdapter.addData(newsContents,isNeedClaer);
        pullAdapter.notifyDataSetChanged();//刷新
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

        currentPage = 0;
        isNeedClaer = true;
        path = ApplicationConstants.getUrl(mParam2,currentPage);
        HttpUtist.loadDataFromServer(path,NewsContext.class,this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        currentPage =+ 5;
        isNeedClaer = false;
        path = ApplicationConstants.getUrl(mParam2,currentPage);
        HttpUtist.loadDataFromServer(path,NewsContext.class,this);
    }
}
