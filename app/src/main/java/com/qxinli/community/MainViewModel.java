package com.qxinli.community;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class MainViewModel  extends BaseObservable{
    private int currentSelect;
    @Bindable
    public int getCurrentSelect() {
        return currentSelect;
    }

    public void setCurrentSelect(int currentSelect) {
        this.currentSelect = currentSelect;
        notifyPropertyChanged(com.qxinli.community.BR.currentSelect);
    }

}
