package com.example.bigwork;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<String> songTitles; // 歌曲标题列表
    private OnItemClickListener listener;// 项目点击监听器
    // SongAdapter 的构造函数
    public SongAdapter(List<String> songTitles, OnItemClickListener listener) {
        this.songTitles = songTitles;
        this.listener = listener;
    }
    // 创建歌曲项的视图持有者
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 填充歌曲项布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        // 使用填充的视图和点击监听器创建新的 SongViewHolder
        return new SongViewHolder(view, listener);
    }
    // 将数据绑定到视图持有者
    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        String songTitle = songTitles.get(position);
        holder.songTitleTextView.setText(songTitle);
    }
    // 返回项目数量
    @Override
    public int getItemCount() {
        return songTitles.size();
    }
    // 获取指定位置的项目
    public String getItem(int position) {
        return songTitles.get(position);
    }
    // 定义项目点击监听器的接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // 歌曲项的视图持有者
    public static class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView songTitleTextView;
        public SongViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            songTitleTextView = itemView.findViewById(R.id.songTitle);

            // 设置点击事件监听器
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                        Log.e("点击事件","我点击了SongAdapter");
                    }
                }
            });
        }
    }

}
