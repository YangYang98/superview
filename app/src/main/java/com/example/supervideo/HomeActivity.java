package com.example.supervideo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.supervideo.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem mPreItem;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.menu);
        setTitle("首页");
        mDrawerLayout=bindViewId(R.id.drawer_layout);
        mNavigationView=bindViewId(R.id.navigation_view);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //TODO
        mPreItem=mNavigationView.getMenu().getItem(0);
        mPreItem.setCheckable(true);
        initFragment();
        handleNavigationViewItem();
    }

    @Override
    protected void initData() {
        //TODO
    }
    private void initFragment(){
        mCurrentFragment=FragmentManagerWrapper.getInstance().createFragment(HomeFragment.class);
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commit();
    }
    private void handleNavigationViewItem() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mPreItem!=null){
                    mPreItem.setCheckable(false);
                }
                switch (item.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        mToolBar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        mToolBar.setTitle(R.string.blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(AboutFragment.class);
                        mToolBar.setTitle(R.string.about_title);
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mPreItem=item;
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment=FragmentManagerWrapper.getInstance().createFragment(clazz);
        if (fragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
        }else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content,fragment).commitAllowingStateLoss();
        }
        mCurrentFragment=fragment;
    }
}
