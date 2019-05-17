package com.example.supervideo;

import android.support.v4.app.Fragment;

import com.example.supervideo.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by 15110 on 2019/5/14.
 */
//为了创建Fragment
public class FragmentManagerWrapper {
    private volatile static FragmentManagerWrapper mInstance=null;//饿汉式单例模式
    public static FragmentManagerWrapper getInstance(){
        if (mInstance==null){
            synchronized (FragmentManagerWrapper.class){
                if (mInstance==null){//双重锁，防止有些没有new到的对象
                    mInstance=new FragmentManagerWrapper();
                }
            }
        }
        return mInstance;
    }
    //为了创建Fragment
    private HashMap<String,Fragment> mHashMap=new HashMap<>();//如果isobtain=true就将Fragment放入HashMap中
    public Fragment createFragment(Class<?> clazz){
        return createFragment(clazz,true);
    }
    public Fragment createFragment(Class<?> clazz,boolean isobtain){
        Fragment fragment=null;
        String className=clazz.getName();
        if (mHashMap.containsKey(className)){
            fragment=mHashMap.get(className);
        }else {
            try {
                fragment= (BaseFragment) Class.forName(className).newInstance();//反射
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (isobtain){
            mHashMap.put(className,fragment);
        }
        return fragment;
    }
}
