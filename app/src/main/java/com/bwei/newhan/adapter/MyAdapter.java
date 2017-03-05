package com.bwei.newhan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.newhan.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList list;

    public MyAdapter(Context context,ArrayList list){

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){

            view = View.inflate(context, R.layout.item_lv,null);

        }

        ImageView im = (ImageView) view.findViewById(R.id.im);
        TextView tv = (TextView) view.findViewById(R.id.tv);

        im.setImageResource(R.mipmap.ic_launcher);
        tv.setText(list.get(i).toString());
        return view;
    }
}
