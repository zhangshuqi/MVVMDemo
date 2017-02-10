package com.qxinli.community;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qxinli.community.bean.UserInfo;
import com.qxinli.community.callback.ViewClickCallback;
import com.qxinli.community.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserInfo info;
    private MainViewModel viewModel;
    private MainViewModel viewModel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        info = new UserInfo();
        info.setUserAge(String.valueOf(30));
        info.setUsername("hahaha");
        binding.setUser(info);
        viewModel1 = new MainViewModel();
        binding.setMainViewModel(viewModel1);
        viewModel1.setCurrentSelect(0);
        binding.setViewClick(new ViewClickCallback() {
            @Override
            public void onClick(View view) {
/*
                info.setUserAge(String.valueOf(50));
                info.setUsername("呵呵呵");
                viewModel1.setCurrentSelect(1);*/
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
}
