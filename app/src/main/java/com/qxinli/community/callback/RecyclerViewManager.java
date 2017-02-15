package com.qxinli.community.callback;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListAdapter;

import com.qxinli.community.view.RefreshRecyclerView;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public interface RecyclerViewManager {
    //加载状态
    public void onLoading();
    public void onEmpty();
    public void onError();
    public void onSuccess();
    public void refreshEnd();
    public void refreshStart();
    void setDivider(Drawable divider);
    public void loadMoreEnd();
    public void loadMoreError();
    public void noMoreData(int size);
    public void LoadMoreStart();
    public void addHeader(View headView);
    boolean hasNoHeader();
 //   void  setMyListener(RefreshRecyclerView.MyListViewListener listener);
    void setNoMoreData(boolean isNoMoreData);
}
