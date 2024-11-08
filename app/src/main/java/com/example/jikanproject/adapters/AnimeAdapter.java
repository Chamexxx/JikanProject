package com.example.jikanproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jikanproject.metodos.AnimeData;
import com.example.jikanproject.R;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<AnimeData> animeList;
    private OnItemClickListener onItemClickListener;

    public AnimeAdapter(List<AnimeData> animeList) {
        this.animeList = animeList;
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        AnimeData anime = animeList.get(position);
        holder.titleTextView.setText(anime.getTitle());
        holder.episodesTextView.setText("Episodios: " + anime.getEpisodes());

        // Cargar la imagen con Glide
        Glide.with(holder.imageView.getContext())
                .load(anime.getImages().getJpg().getImage_url())
                .into(holder.imageView);

        // Configurar el clic en el item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(anime.getMalId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList != null ? animeList.size() : 0;
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, episodesTextView;
        ImageView imageView;

        public AnimeViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            episodesTextView = itemView.findViewById(R.id.episodesTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    // Interfaz para manejar el clic en un item
    public interface OnItemClickListener {
        void onItemClick(int animeId);
    }

    // Setter para el listener
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
