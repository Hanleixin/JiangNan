package com.bwei.newhan.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.newhan.R;
import com.bwei.newhan.home.bean.NewsContext;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class HomeListAdapter extends BaseAdapter {

    private Context context;

    private DisplayImageOptions options;

    private int TYPE_1 = 0;
    private int TYPE_2 = 1;/**展示listview两种模式的代号*/

    private ArrayList<NewsContext> newsContexts = new ArrayList<>();

    private boolean isNeedClear = false;//下拉刷新时清空历史的变量

    public HomeListAdapter(Context context){
        this.context = context;

        //初始化图片（图片还未加载完成时预先展示的图片）
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .considerExifParams(true)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    @Override
    public int getCount() {
        return newsContexts.size();
    }

    @Override
    public NewsContext getItem(int position) {
        return newsContexts.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    @Override
//    public int getViewTypeCount() {//多条目的总共种类数量
//
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {//判断加载何种的item
//
//        NewsContext news = getItem(position);
//
//        if(news.getImgextra() != null && news.getImgextra().size() > 0){
//
//            return  TYPE_2;
//        }
//        else{
//            return TYPE_1;
//        }
//    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;

        if(convertview==null){

//            if(getItemViewType(position) == TYPE_1){

                viewHolder = new ViewHolder();

                convertview = View.inflate(context, R.layout.home_listview_item,null);

                //获取TYPE_1 ImageView的ID
                viewHolder.im = (ImageView) convertview.findViewById(R.id.im_list_imageview);

                //获取TYPE_1 TextView的ID
                viewHolder.tv1 = (TextView) convertview.findViewById(R.id.tv_homelist_textview);
                viewHolder.tv2 = (TextView) convertview.findViewById(R.id.tv2_homelist_textview);

                convertview.setTag(viewHolder);//绑定
            }
//            else{
//
//                viewHolder2 = new ViewHolder2();
//
//                //添加T
//                convertview = View.inflate(context,R.layout.home_listview_item2,null);
//
//                //获取TYPE_2 ImageView的ID
//                viewHolder2.imageView = (ImageView) convertview.findViewById(R.id.type_2_im);
//                viewHolder2.imageView2 = (ImageView) convertview.findViewById(R.id.type_2_im2);
//                viewHolder2.imageView3 = (ImageView) convertview.findViewById(R.id.type_2_im3);
//
//                //获取TYPE_2 TextView的ID
//                viewHolder2.textView1 = (TextView) convertview.findViewById(R.id.type_2_tv);
//                viewHolder2.textView2 = (TextView) convertview.findViewById(R.id.type_2_tv2);
//                viewHolder2.textView3 = (TextView) convertview.findViewById(R.id.type_2_tv3);
//
//                convertview.setTag(viewHolder2);//绑定
//            }
//        }
//        else{
//
//            if(getItemViewType(position) == TYPE_1){
//
//                viewHolder = (ViewHolder) convertview.getTag();
//            }
//            else{
//
//                viewHolder2 = (ViewHolder2) convertview.getTag();
//            }
//        }
//
//        if(getItemViewType(position) == TYPE_1){

            String url = getItem(position).getImgsrc();
            ImageLoader.getInstance().displayImage(url,viewHolder.im,options);

            viewHolder.tv1.setText(getItem(position).getTitle());
            viewHolder.tv2.setText(getItem(position).getSource()+"    评价： "+getItem(position).getReplyCount());
//        }
//        else{
//
//            String url = getItem(position).getImgsrc();
//            ImageLoader.getInstance().displayImage(url,viewHolder2.imageView,options);
//            String url2 = getItem(position).getImgextra().get(0).getImgsrc();
//            ImageLoader.getInstance().displayImage(url2,viewHolder2.imageView2,options);
//            String url3 = getItem(position).getImgextra().get(1).getImgsrc();
//            ImageLoader.getInstance().displayImage(url3,viewHolder2.imageView3,options);
//
//            viewHolder2.textView1.setText(getItem(position).getTitle());
//            viewHolder2.textView2.setText(getItem(position).getSource());
//            viewHolder2.textView3.setText("评价： "+getItem(position).getReplyCount());
//        }

        return convertview;
    }

    public void addData(ArrayList<NewsContext> datas,boolean isNeedClear){

        if(datas != null){

            if(isNeedClear){

                newsContexts.clear();
            }

            newsContexts.addAll(datas);
        }
    }

    static class ViewHolder{

        TextView tv1,tv2;
        ImageView im;
    }

    static class ViewHolder2{

        TextView textView1,textView2,textView3;
        ImageView imageView,imageView2,imageView3;
    }

}
