package com.qxinli.community.bean;

/**
 * Created by Administrator on 2017/2/10 0010.
 */
public class MultiItemTypeBean {
    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private int layoutRes;
    // item 的type
    private int itemViewType;
    // normal 0 , head 1  foot 2
    private int itemType;
}
