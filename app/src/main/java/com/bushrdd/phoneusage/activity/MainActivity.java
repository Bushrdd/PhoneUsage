package com.bushrdd.phoneusage.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bushrdd.phoneusage.R;
import com.bushrdd.phoneusage.data.VideoData;
import com.bushrdd.phoneusage.view.GridSpaceItemDecoration;
import com.bushrdd.phoneusage.view.VideosListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mListView;
    private VideosListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.main_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mListView.setLayoutManager(layoutManager);
        // mListView.addItemDecoration(new GridSpaceItemDecoration( 20));
        mListView.addItemDecoration(new GridSpaceItemDecoration(40, 20));
        List<VideoData> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new VideoData(i, "哈哈哈"));
        }
        mAdapter = new VideosListViewAdapter(list);
        mListView.setAdapter(mAdapter);
    }

    public void opSetting(View view) {
    }
}