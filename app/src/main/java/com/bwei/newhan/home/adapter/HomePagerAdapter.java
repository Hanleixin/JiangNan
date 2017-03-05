package com.bwei.newhan.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bwei.newhan.home.HomeTabFragment;
//import com.bwei.newstitlehanleixin.home.HomeTabFragment;

import java.util.List;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] title;
    private String[] TITLE={"T1370583240249","T1348648517839","T1348649079062","T1348648141035","T1350383429665","T1348648650048","T1348649145984","T1348650593803","T1348654204705","T1379038288239"};

    public HomePagerAdapter(FragmentManager fm, List<Fragment> list, String[] title){//T1348649475931  T1397016069906  T1348649654285
        super(fm);
        this.fragments = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeTabFragment.newInstance(title[position],TITLE[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    public  void setList(List<Fragment> list){
        this.fragments = list;
    }

}
