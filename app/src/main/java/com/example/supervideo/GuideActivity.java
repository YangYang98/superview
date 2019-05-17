package com.example.supervideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private List<View> mViewList;
    private ViewPager mViewPager;
    private ImageView[] mDotList;
    private int mLastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initViewPager();
        initDots();
    }

    private void initView() {
        mViewList=new ArrayList<View>();
        LayoutInflater inflater=LayoutInflater.from(this);
        mViewList.add(inflater.inflate(R.layout.guide_one_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_two_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_three_layout,null));
    }
    private void initViewPager(){
        mViewPager= (ViewPager) findViewById(R.id.viewPage);
        MyPagerAdapter adapter=new MyPagerAdapter(mViewList,this);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initDots(){
        LinearLayout dotLayout=(LinearLayout) findViewById(R.id.ll_dots_layout);
        mDotList=new ImageView[mViewList.size()];
        for (int i=0;i<mViewList.size();i++){
            mDotList[i]= (ImageView) dotLayout.getChildAt(i);
            mDotList[i].setEnabled(false);
        }
        mLastPosition=0;
        mDotList[0].setEnabled(true);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //页面滚动时触发
    }

    @Override
    public void onPageSelected(int position) {
        //页面选中时调用
        setCurrentDotPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //滚动过程中调用
    }

    private void setCurrentDotPosition(int position){
        mDotList[position].setEnabled(true);
        mDotList[mLastPosition].setEnabled(false);
        mLastPosition=position;
    }
    class MyPagerAdapter extends PagerAdapter{
        private List<View> mViewList;
        private Context context;
        public MyPagerAdapter(List<View> mViewList, Context context) {
            this.mViewList=mViewList;
            this.context=context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (mViewList!=null){
                if (mViewList.size()>=0){
                    container.addView(mViewList.get(position));
                    if(position==mViewList.size()-1){
                        Button btn=(Button) findViewById(R.id.btn_start);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startHomeActivity();
                                setGuided();
                            }
                        });
                    }
                    return mViewList.get(position);
                }
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mViewList!=null){
                if (mViewList.size()>=0){
                    container.removeView(mViewList.get(position));
                }
            }
        }

        @Override
        public int getCount() {
            if (mViewList!=null){
                return mViewList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        private void startHomeActivity(){
            Intent intent=new Intent(GuideActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        private void setGuided(){
            SharedPreferences.Editor editor=getSharedPreferences("config",MODE_PRIVATE).edit();
            editor.putBoolean("isFirstIn",false);
            editor.apply();

        }
    }
}
