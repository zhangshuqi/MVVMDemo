package com.qxinli.community.base;

import android.databinding.BaseObservable;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public  interface BaseBindingPresenter<T> {
    public void onItemClick(T itemData);
}
