package com.qxinli.community.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qxinli.community.R;
import com.qxinli.community.base.recyclerview.BaseDataBindingAdapter;
import com.qxinli.community.callback.RecyclerViewManager;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class RefreshRecyclerView extends FrameLayout   {
    public RefreshRecyclerView(Context context) {
        super(context);
    }
  /*  public RecyclerView lvContent;
    public SwipeToRefreshCompat swipeRefreshLayout;
    private MyRecyclerViewListener recyclerViewListener;
    private View footView;
    private BaseDataBindingAdapter baseRecyclerAdapter;
    private LoadingAndRetryManager pageManager;
    private String emptyText;
    private boolean isLoadMore;
    private boolean isNoMoreData;
    private TextView tvMoreText;
    private View viewLine;

    public RefreshRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        initView(context);
        initData(context);
        initEvent(context);
    }

    private void initEvent(Context context) {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (recyclerViewListener != null) {
                    recyclerViewListener.onRefresh(lvContent);
                }

            }
        });

        lvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isNoMoreData && !isLoadMore) {
                    if (isSlideToBottom(recyclerView)) {
                        refreshEnd();
                        if (recyclerViewListener != null) {
                            isLoadMore = true;
                            recyclerViewListener.onLoadMore(lvContent);
                        }
                    }
                }
            }
        });
    }
    public void setFootView(View view) {
        footView = view;
        tvMoreText = (TextView) view.findViewById(R.id.tv_listview_foot_state_dec);
        viewLine = view.findViewById(R.id.view_line);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(Gravity.CENTER_HORIZONTAL);
        view.setLayoutParams(params);
        baseRecyclerAdapter.setFootView(view);
    }

    public void setViewLineVisibility(boolean b) {
        if (viewLine != null) viewLine.setVisibility(b ? VISIBLE : GONE);
    }

    private void initData(Context context) {
        lvContent.setHasFixedSize(true);
        //设置空白,错误,加载中的页面

       // pageManager.showContent();
    }

    private void initView(Context context) {
        ViewGroup rootView = (ViewGroup) View.inflate(context, R.layout.view_, null);
        swipeRefreshLayout = (MySwipeToRefresh) rootView.findViewById(R.id.myswipe);
        lvContent = (RecyclerView) rootView.findViewById(R.id.lv_content);
        this.addView(rootView);

    }

    public void setItemAnimator(DefaultItemAnimator defaultItemAnimator) {
        lvContent.setItemAnimator(defaultItemAnimator);
    }

    public interface MyRecyclerViewListener {
        public abstract void onRefresh(RecyclerView listView);

        public abstract void onLoadMore(RecyclerView listView);

        public abstract void onRetry();

    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        lvContent.setLayoutManager(manager);
    }

    private boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public void setRecyclerViewListener(MyRecyclerViewListener listener) {
        this.recyclerViewListener = listener;
    }

    //加载状态
    @Override
    public void onLoading() {
        if (pageManager != null) {
            pageManager.showLoading();
        }
    }

    @Override
    public void onEmpty() {
        if (pageManager != null) {
            pageManager.showEmpty();
        }
    }

    @Override
    public void onError() {
        if (pageManager != null) {
            pageManager.showRetry();
        }
    }

    @Override
    public void onSuccess() {
        if (pageManager != null) {
            pageManager.showContent();
        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {


    }

    @Override
    public void setExAdapter(BaseExpandableListAdapter adapter) {

    }

    @Override
    public void refreshEnd() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setDivider(Drawable divider) {
        //  mListView.setDivider(divider);
    }

    @Override
    public void refreshStart() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

    }

    @Override
    public void loadMoreEnd() {
        isLoadMore = false;
        footView.setVisibility(VISIBLE);
        tvMoreText.setText("加载中...");
    }


    @Override
    public void loadMoreError() {
        isLoadMore = false;
        footView.setVisibility(VISIBLE);
        tvMoreText.setText("加载出错");
    }

    @Override
    public void noMoreData(int size) {
        isLoadMore = false;
        // footView.setText("没有了");

        if (size < 3) {
            footView.setVisibility(GONE);
        } else {
            tvMoreText.setText("没有了");
        }

        isNoMoreData = true;

    }

    @Override
    public void LoadMoreStart() {
        footView.setVisibility(VISIBLE);

        tvMoreText.setText("加载中...");


    }

    @Override
    public void addHeader(View headView) {
        if (baseRecyclerAdapter != null) {
            baseRecyclerAdapter.setHeaderView(headView);

        }
    }

    @Override
    public boolean hasNoHeader() {
        return baseRecyclerAdapter.getHeaderView() == null;
    }

    @Override
    public void setMyListener(MySimpleListview.MyListViewListener listener) {

    }

    @Override
    public void setNoMoreData(boolean isMoreData) {
        isNoMoreData = isMoreData;
    }
*/
}
