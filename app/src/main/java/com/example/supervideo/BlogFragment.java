package com.example.supervideo;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.supervideo.base.BaseFragment;

/**
 * Created by 15110 on 2019/5/14.
 */

public class BlogFragment extends BaseFragment {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private static final int MAX_VALUE=100;
    private static final String BLOG_URL="https://www.baidu.com";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initView() {
        mWebView=bindViewId(R.id.webview1);
        mProgressBar=bindViewId(R.id.blog_progress);
        WebSettings settings=mWebView.getSettings();//用来设置WebView的属性，比如说支持js脚本，页面放大缩小
        settings.setBuiltInZoomControls(true);//设置页面可放大缩小
        settings.setJavaScriptEnabled(true);//支持JavaScript脚本
        mProgressBar.setMax(MAX_VALUE);
        mWebView.loadUrl(BLOG_URL);
        mWebView.setWebChromeClient(mWebChromeClient);
    }
    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress==MAX_VALUE){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };

    @Override
    protected void initData() {

    }


}
