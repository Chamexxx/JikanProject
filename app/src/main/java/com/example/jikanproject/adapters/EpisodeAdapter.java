package com.example.jikanproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jikanproject.R;
import com.example.jikanproject.models.Episode;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private List<Episode> episodeList;

    public EpisodeAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.episodeTitle.setText(episode.getTitle());
        holder.episodeDate.setText(episode.getAirDate());
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {

        TextView episodeTitle, episodeDate;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeTitle = itemView.findViewById(R.id.episodeTitle);
            episodeDate = itemView.findViewById(R.id.episodeDate);
        }
    }
    public void updateEpisodes(List<Episode> episodes) {
        this.episodeList.clear();
        this.episodeList.addAll(episodes);
        notifyDataSetChanged();
    }
}