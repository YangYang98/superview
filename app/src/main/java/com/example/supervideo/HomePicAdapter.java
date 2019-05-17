package com.example.supervideo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 15110 on 2019/5/16.
 */

public class HomePicAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mDes=new int[]{
            R.string.a_name,R.string.b_name,R.string.c_name,R.string.d_name,R.string.e_name
    };
    private int[] mImg=new int[]{
            R.drawable.loopview1,R.drawable.loopview2,R.drawable.loopview3,R.drawable.loopview4,R.drawable.loopview5
    };
    public HomePicAdapter(Activity activity){
        mContext=activity;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_pic_item,null);
        TextView textView=(TextView)view.findViewById(R.id.tv_dec);
        ImageView imageView=(ImageView)view.findViewById(R.id.iv_img);
        imageView.setImageResource(mImg[position]);
        textView.setText(mDes[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
