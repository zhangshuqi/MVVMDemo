package com.qxinli.community.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;



/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class UserInfo  extends BaseObservable{
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(com.qxinli.community.BR.username);
    }
    @Bindable
    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
        notifyPropertyChanged(com.qxinli.community.BR.userAge);
    }

    private String username;
    private String userAge;

}
