package com.bwei.newhan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.newhan.R;
import com.bwei.newhan.adapter.Mypageradapter;
import com.bwei.newhan.home.HomeTabFragment;
import com.bwei.newhan.home.adapter.HomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {

    View view;

    ViewPager vPager,viewPager;
    TabLayout tabLayout;

    private String[] title = new String[]{
            "头条","娱乐","体育","彩票","笑话",
            "电影", "NBA","时尚","旅游","电台"};

    //图片数组
    private String[] drawables=new String[]{
            "http://img5.imgtn.bdimg.com/it/u=1746117780,563984681&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1603050921,1630138197&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=87647659,940456734&fm=23&gp=0.jpg"
    };

    List<Fragment> fragments;

    private int index;
    private LinearLayout ll;

//    //图片自动轮播的线程
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int currentItem = viewPager.getCurrentItem();
//            viewPager.setCurrentItem(currentItem+=1);
//            handler.sendEmptyMessageDelayed(0,1000);
//        }
//    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment_home,null);

        return view;
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        addPoint();
//        viewPager.setAdapter(new Mypageradapter(getActivity(),drawables));
//        viewPager.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%drawables.length);
//        handler.sendEmptyMessageDelayed(0,3000);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//            @Override
//            public void onPageSelected(int position) {
//                View childAt = ll.getChildAt(position%drawables.length);
//                childAt.setEnabled(false);
//                View childAt1 = ll.getChildAt(index%drawables.length);
//                childAt1.setEnabled(true);
//                index=position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        getData();
//    }

    private void addPoint(){
        for(int i=0;i<drawables.length;i++){
            ImageView imageView=new ImageView(getActivity());
            imageView.setBackgroundResource(R.drawable.but_news_back);
            LinearLayoutCompat.LayoutParams layoutParams=new LinearLayoutCompat.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin=20;
            imageView.setLayoutParams(layoutParams);
            if(i==0){
                imageView.setEnabled(false);
            }
            ll.addView(imageView);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取地址的方法
        findId();
        //添加TabLayout题目的方法
        initview();
        //
        initData();

    }

    /**获取相关控件地址(ID)的方法*/
    private void findId(){

        tabLayout = (TabLayout) view.findViewById(R.id.fragment_home_tablyout);
        vPager = (ViewPager) view.findViewById(R.id.vp_home_viewpager);
    }

    /**添加TabLayout题目的方法（相当于获取TabLayout视图的方法）*/
    private void initview(){

        fragments = new ArrayList<>();

        for (int i = 0; i<title.length; i++ ){

            //实例化TabLayout的对象
            HomeTabFragment homeTabFragment = new HomeTabFragment();
            //加入TabLayout相关题目
            fragments.add(homeTabFragment);
        }
    }

    /**设置相关TabLayout相关适配器及TabLayout的相关主题*/
    private void initData(){

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragments,title);
        homePagerAdapter.setList(fragments);
        vPager.setOffscreenPageLimit(5);
        vPager.setAdapter(homePagerAdapter);
        tabLayout.setupWithViewPager(vPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置文字为横向呈现

    }

}
