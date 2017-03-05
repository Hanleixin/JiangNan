package com.bwei.newhan.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class Mypageradapter extends PagerAdapter {
    private Context context;
    private String[] drawables;

    public Mypageradapter(Context context, String[] drawables) {
        this.context = context;
        this.drawables = drawables;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        ViewParent parent = imageView.getParent();
        if(parent!=null){
            container.removeView(imageView);
        }
        ImageLoader.getInstance().displayImage(drawables[position % drawables.length], imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);

        container.removeView((View) object);

    }
}
