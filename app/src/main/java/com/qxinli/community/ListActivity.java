package com.qxinli.community;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qxinli.community.base.BaseBindingPresenter;
import com.qxinli.community.base.recyclerview.DividerGridItemDecoration;
import com.qxinli.community.base.recyclerview.DividerItemDecoration;
import com.qxinli.community.base.recyclerview.MultiTypeBindingAdapter;
import com.qxinli.community.base.recyclerview.SingleTypeBindingAdapter;
import com.qxinli.community.databinding.ActivityListBinding;
import com.qxinli.community.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class ListActivity extends SwipeBackActivity {

    private ActivityListBinding binding;
    private ArrayMap<Integer, Integer> map;
    private List mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*     SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);*/


        mData = new ArrayList();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        DividerGridItemDecoration dividerItemDecoration = new DividerGridItemDecoration(this);
        dividerItemDecoration.setDivider(R.drawable.red_point);
        binding.setItemDecoration(dividerItemDecoration);
        GridLayoutManager manager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,true);
        binding.setLayoutManager(manager);
        List<ListViewHolder> data = new ArrayList<>();
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        data.add(new ListViewHolder("张小胖", "20"));
        mData.addAll(data);
       SingleTypeBindingAdapter multiTypeBindingAdapter = new SingleTypeBindingAdapter(this, data, R.layout.item_list);
        multiTypeBindingAdapter.setPresenter(new BaseBindingPresenter<ListViewHolder>() {
            @Override
            public void onItemClick(ListViewHolder itemData) {
                Logger.d(itemData.getName());
            }
        });
/*
        MultiTypeBindingAdapter multiTypeBindingAdapter = new MultiTypeBindingAdapter(this, mData) {
            @Override
            public int getMyItemViewType(int position, ArrayMap<Integer, Integer> multiTypeMap) {
                if (position % 2 == 0) {
                    multiTypeMap.put(ITEM_VIEW_NORMAL_TYPE, R.layout.item_list);
                    return ITEM_VIEW_NORMAL_TYPE;
                } else {
                    multiTypeMap.put(552552, R.layout.item_list2);
                    return 552552;
                }
            }
        };
*/

/*        multiTypeBindingAdapter.addSingleHeadConfig(0, R.layout.head_item1, new ListViewHolder2("张小胖1111", "020202"));
        //  multiTypeBindingAdapter.setSingleFootConfig(222,R.layout.head_item2,new ListViewHolder3("张小胖3333","121212"));
        multiTypeBindingAdapter.setMultiFootConfig(new MultiTypeBindingAdapter.AdapterTypeConfig() {
            @Override
            public Map<Integer, Integer> getTypeConfigKeyAndRes() {
                Map<Integer, Integer> map = new HashMap();
                map.put(222, R.layout.head_item2);
                map.put(1111, R.layout.head_item2);
                map.put(3333, R.layout.head_item2);
                return map;
            }

            @Override
            public List getTypeConfigData() {
                List list = new ArrayList();
                list.add(new ListViewHolder3("张小胖3333", "121212"));
                list.add(new ListViewHolder3("张小胖4444", "121212"));
                list.add(new ListViewHolder3("张小胖5555", "121212"));
                return list;
            }
        });*/
        binding.setAdapter(multiTypeBindingAdapter);


        //  recycler_view.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    String[] mPerms;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.SETTINGS_REQ_CODE) {
            //设置返回
            if (mPerms != null) {
                if (PermissionUtils.checkEachSelfPermission(this, mPerms)) {
                    Logger.d("......1.111");
                } else {
                    Logger.d("222");
                }
            }
        }
    }

}
