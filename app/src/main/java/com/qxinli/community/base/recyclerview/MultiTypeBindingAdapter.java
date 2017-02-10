package com.qxinli.community.base.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.qxinli.community.bean.MultiItemTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class MultiTypeBindingAdapter<T> extends BaseDataBindingAdapter {
    public static final int ITEM_VIEW_HEAD_TYPE = 1;
    public static final int ITEM_VIEW_NORMAL_TYPE = 0;
    public static final int ITEM_VIEW_FOOT_TYPE = 2;
    protected  ArrayMap<Integer,Integer>multiTypeMap;
    private int headerCount;

    public MultiTypeBindingAdapter(Context context, List data) {
        super(context);
        mData = data;
        multiTypeMap= new ArrayMap<>();
    }
    public void setHeaderCount(int headerCount){
        this.headerCount = headerCount;
    }
    public void setHeaderCount(int headerCount,ArrayMap headMap){
        for(int i =0 ;i<headerCount;i++){
          //  multiTypeMap
        }
        this.headerCount = headerCount;
    }
    public void setMultiTypeMap(ArrayMap<Integer, Integer> multiTypeMap) {
        this.multiTypeMap = multiTypeMap;
    }
    public interface Head {
        int  setHeadItemViewTypeAndLayoutRes();
    }
    public boolean isHeaderView(int position) {
        if (headerCount <= 0) {
            return false;
        } else if (headerCount == 1) {
            return position == 0;
        } else if (headerCount > 1) {
            return position < headerCount;
        }
        return false;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (getLayoutRes(viewType) <= 0)
            throw new NullPointerException("onCreateViewHolder layout res is null");
        return new BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, getLayoutRes(viewType), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)){
            return ITEM_VIEW_HEAD_TYPE;
        }else if (isFooterView(position)){
            return  ITEM_VIEW_FOOT_TYPE;
        }
        return getMyItemViewType(position);
    }

    private int getMyItemViewType(int position) {
        return ITEM_VIEW_NORMAL_TYPE;
    }

    private boolean isFooterView(int position) {
        return false;
    }

    @Override
    public int getLayoutRes(int itemViewType) {
        return  multiTypeMap.get(itemViewType);
    }



    @Override
    public void clear() {
        super.clear();
    }

    public void add(int position, Object data, MultiItemTypeBean bean) {
        super.add(position, data);
    }
}
