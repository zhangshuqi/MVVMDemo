package com.qxinli.community;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qxinli.community.base.recyclerview.MultiTypeBindingAdapter;
import com.qxinli.community.databinding.ActivityListBinding;

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
        mData= new ArrayList();
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        Logger.init("QXINLI_ANDROID")         // default PRETTYLOGGER or use just init()
                .methodCount(4)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL);      // default LogLevel.FULL
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        binding.setLayoutManager(new LinearLayoutManager(this));
        List<ListViewHolder> data = new ArrayList<>();
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        mData.addAll(data);
   /*     SingleTypeBindingAdapter singleTypeBindingAdapter = new SingleTypeBindingAdapter(this, data, R.layout.item_list);
        singleTypeBindingAdapter.setPresenter(new BaseBindingPresenter<ListViewHolder>() {
            @Override
            public void onItemClick(ListViewHolder itemData) {
                Logger.d(itemData.getName());
            }
        });*/
        MultiTypeBindingAdapter multiTypeBindingAdapter  = new MultiTypeBindingAdapter(this, mData){
            @Override
            public int getMyItemViewType(int position, ArrayMap<Integer, Integer> multiTypeMap) {
                if (position%2==0) {
                    multiTypeMap.put(ITEM_VIEW_NORMAL_TYPE, R.layout.item_list);
                    return ITEM_VIEW_NORMAL_TYPE;
                }else {
                    multiTypeMap.put(552552, R.layout.item_list2);
                    return 552552;
                }
            }
        };

        multiTypeBindingAdapter.addSingleHeadConfig(0,R.layout.head_item1,new ListViewHolder2("张小胖1111","020202"));
      //  multiTypeBindingAdapter.setSingleFootConfig(222,R.layout.head_item2,new ListViewHolder3("张小胖3333","121212"));
        multiTypeBindingAdapter.setMultiFootConfig(new MultiTypeBindingAdapter.AdapterTypeConfig() {
            @Override
            public Map<Integer, Integer> getTypeConfigKeyAndRes() {
                Map<Integer,Integer>map = new HashMap();
                map.put(222,R.layout.head_item2);
                map.put(1111,R.layout.head_item2);
                map.put(3333,R.layout.head_item2);
                return map;
            }

            @Override
            public List getTypeConfigData() {
                List list = new ArrayList();
                list.add(new ListViewHolder3("张小胖3333","121212"));
                list.add(new ListViewHolder3("张小胖4444","121212"));
                list.add(new ListViewHolder3("张小胖5555","121212"));
                return list;
            }
        });
        binding.setAdapter(multiTypeBindingAdapter);
    }

}
