package com.example.supervideo;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.supervideo.base.BaseFragment;

/**
 * Created by 15110 on 2019/5/14.
 */

public class AboutFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
    @Override
    protected void initView() {
        TextView textView=bindViewId(R.id.tv_app_description);
        textView.setAutoLinkMask(Linkify.ALL);//表示文字中有链接可点
        textView.setMovementMethod(LinkMovementMethod.getInstance());//表示文字可以滚动
    }

    @Override
    protected void initData() {

    }


}
