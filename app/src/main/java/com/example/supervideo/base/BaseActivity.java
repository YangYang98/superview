package com.example.supervideo.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.supervideo.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();//初始化视图
        initData();//初始化数据
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected <T extends View> T bindViewId(int resId){
        return (T)findViewById(resId);
    }
    protected void setSupportActionBar(){
        mToolBar=bindViewId(R.id.toolBar);
        if (mToolBar!=null){
            setSupportActionBar(mToolBar);
        }
    }
    protected void setActionBarIcon(int resId){
        if (mToolBar!=null){
            mToolBar.setNavigationIcon(resId);
        }
    }
}
