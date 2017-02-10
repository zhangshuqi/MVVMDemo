package com.qxinli.community.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class BaseRecyclerViewHolder<T>extends RecyclerView.ViewHolder{
    public ViewGroup rootView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        rootView = (ViewGroup) itemView;
        ButterKnife.bind(this,rootView);
    }


    public  void assignDatasAndEvents(Activity context, T data, int position,boolean isLast,int  viewType){
        if (data==null)
            return;
    }
    public  void assignHeadOrFootDatasAndEvents(Activity context){

    }
}
