package com.bushrdd.phoneusage.activity;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bushrdd.phoneusage.R;
import com.bushrdd.phoneusage.data.VideoData;
import com.bushrdd.phoneusage.view.GridSpaceItemDecoration;
import com.bushrdd.phoneusage.view.VideosListViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";

    private RecyclerView mListView;
    private VideosListViewAdapter mAdapter;
    private TextToSpeech mTextToSpeech;

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
        mAdapter.setRecyclerviewItemListener(mListener);
        mListView.setAdapter(mAdapter);

        mTextToSpeech = new TextToSpeech(this, mOnInitListener);
    }


    private final VideosListViewAdapter.IRecyclerviewItemListener mListener = new VideosListViewAdapter.IRecyclerviewItemListener() {
        @Override
        public void click(int position) {

        }

        @Override
        public void read(String text) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    };

    private TextToSpeech.OnInitListener mOnInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                //设置首选语言为中文,注意,语言可能是不可用的,结果将指示此
                int result = mTextToSpeech.setLanguage(Locale.CHINA);
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    //语言数据丢失或不支持该语言。
                    Log.e(TAG, "语言数据丢失或不支持该语言!");
                }
            } else {
                // 初始化失败
                Log.e(TAG, "朗读功能初始化失败!");
            }
        }
    };

    public void opSetting(View view) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTextToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        mTextToSpeech.shutdown(); // 关闭,释放资源
    }
}