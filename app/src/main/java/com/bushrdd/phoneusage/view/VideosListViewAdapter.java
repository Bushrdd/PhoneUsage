package com.bushrdd.phoneusage.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bushrdd.phoneusage.R;
import com.bushrdd.phoneusage.data.VideoData;

import java.util.List;

public class VideosListViewAdapter extends RecyclerView.Adapter<VideosListViewAdapter.ViewHolder> {
    private final String TAG = "VideosListViewAdapter";

    private List<VideoData> mData;
    private IRecyclerviewItemListener mRecyclerviewItemListener;

    public VideosListViewAdapter(List<VideoData> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //绑定布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);//防止宽高失效

        //实例化ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerviewItemListener!=null){
                    mRecyclerviewItemListener.read((String) holder.textView.getText());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.video_list_item_name_view);
            textView = itemView.findViewById(R.id.video_list_item_name);
        }
    }

    public interface IRecyclerviewItemListener {
        void click(int position);
        void read(String text);
    }

    public void setRecyclerviewItemListener(IRecyclerviewItemListener recyclerviewItemListener) {
        mRecyclerviewItemListener = recyclerviewItemListener;
    }
}
