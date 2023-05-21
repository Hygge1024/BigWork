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
    private List<String> songTitles;
    private OnItemClickListener listener;
    public SongAdapter(List<String> songTitles, OnItemClickListener listener) {
        this.songTitles = songTitles;
        this.listener = listener;
    }
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view, listener);
    }
    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        String songTitle = songTitles.get(position);
        holder.songTitleTextView.setText(songTitle);
    }

    @Override
    public int getItemCount() {
        return songTitles.size();
    }

    public String getItem(int position) {
        return songTitles.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

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
