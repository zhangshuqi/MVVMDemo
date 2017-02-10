package com.qxinli.community.base;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;


import com.qxinli.community.callback.RefreshableAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RefreshableAdapter {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOT = 2;
    private Activity activity;
    public List<T> mDatas;
    private View mHeaderView;
    private View mFootView;
    private OnItemClickListener mListener;

    public BaseRecyclerViewAdapter(Activity activity, List<T> mDatas) {
        this.activity = activity;
        this.mDatas = mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFootView(View footView) {
        mFootView = null;
        if (mDatas != null && mDatas.size() != 0) {
            notifyItemRemoved(mHeaderView != null ? mDatas.size() + 2 : mDatas.size() + 1);
        }
        mFootView = footView;
        notifyItemInserted(mHeaderView != null ? mDatas.size() + 2 : mDatas.size() + 1);
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderView != null && position == 0;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        int itemCount = getItemCount();

        return mFootView != null && position >= itemCount - 1;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public View getFootView() {
        return mFootView;
    }

    public void addDatas(ArrayList<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isBottomView(position)) {
            return TYPE_FOOT;
        } else {
            return getItemMyViewType(position);

        }
    }
    public int getItemMyViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            BaseRecyclerViewHolder headerViewHolder = new BaseRecyclerViewHolder(mHeaderView);
            headerViewHolder.assignHeadOrFootDatasAndEvents(activity);
            return headerViewHolder;
        } else if (mFootView != null && viewType == TYPE_FOOT) {
            BaseRecyclerViewHolder footViewHolder = new BaseRecyclerViewHolder(mFootView);
            footViewHolder.assignHeadOrFootDatasAndEvents(activity);
            return footViewHolder;
        } else {
            return onCreateMyViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_FOOT) return;
        if (!(viewHolder instanceof BaseRecyclerViewHolder))
            return;
        final int pos = getRealPosition(viewHolder);
        final T data = mDatas.get(pos);
        BaseRecyclerViewHolder myViewHolder = (BaseRecyclerViewHolder) viewHolder;
        boolean isLast = mDatas.size() == (mFootView != null ? pos : pos - 1);
        myViewHolder.assignDatasAndEvents(activity, data, pos, isLast, getItemViewType(position));
        if (mListener != null) {
            myViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, data);
                }
            });
        }
    }

    // 解决gridmanager 的head 问题
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOT
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFootView == null) {
            return mDatas.size();
        } else if (mHeaderView != null && mFootView != null) {
            return mDatas.size() + 2;
        } else {
            return mDatas.size() + 1;
        }
    }

    public abstract BaseRecyclerViewHolder onCreateMyViewHolder(ViewGroup parent, final int viewType);


    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }


    @Override
    public void refresh(List newData) {
        if (newData == null) {
            mDatas.clear();
            notifyDataSetChanged();
            return;
        }
        if (mDatas == null) {
            mDatas = newData;
            notifyDataSetChanged();
        } else {
            mDatas.clear();
            mDatas.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(List newData) {
        if (newData == null) {
            return;
        }
        if (mDatas == null) {
            mDatas = newData;
            notifyDataSetChanged();
        } else {
            mDatas.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        if (mDatas != null) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void delete(int position) {
        if (mDatas != null && position < mDatas.size()) {
            mDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    public List getListData() {
        return mDatas;
    }

    @Override
    public void add(Object object) {

    }
}