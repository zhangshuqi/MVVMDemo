package com.qxinli.community;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qxinli.community.base.BaseBindingPresenter;
import com.qxinli.community.base.recyclerview.MultiTypeBindingAdapter;
import com.qxinli.community.base.recyclerview.SingleTypeBindingAdapter;
import com.qxinli.community.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    private ArrayMap<Integer, Integer> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init("QXINLI_ANDROID")         // default PRETTYLOGGER or use just init()
                .methodCount(4)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL);      // default LogLevel.FULL
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        binding.setLayoutManager(new LinearLayoutManager(this));
        List<ListViewHolder> data = new ArrayList<>();
        data.add(new ListViewHolder("张小胖","20111"));
        data.add(new ListViewHolder("张小胖","201111111"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
        data.add(new ListViewHolder("张小胖","20"));
   /*     SingleTypeBindingAdapter singleTypeBindingAdapter = new SingleTypeBindingAdapter(this, data, R.layout.item_list);
        singleTypeBindingAdapter.setPresenter(new BaseBindingPresenter<ListViewHolder>() {
            @Override
            public void onItemClick(ListViewHolder itemData) {
                Logger.d(itemData.getName());
            }
        });*/
        MultiTypeBindingAdapter multiTypeBindingAdapter  = new MultiTypeBindingAdapter(this, data);
        map= new ArrayMap<>();
        map.put(MultiTypeBindingAdapter.ITEM_VIEW_HEAD_TYPE,R.layout.head_item);
        map.put(MultiTypeBindingAdapter.ITEM_VIEW_NORMAL_TYPE,R.layout.item_list);
        multiTypeBindingAdapter.setMultiTypeMap(map);
        multiTypeBindingAdapter.setHeaderCount(1);
        binding.setAdapter(multiTypeBindingAdapter);
    }

}
