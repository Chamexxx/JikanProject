package com.example.jikanproject.models;
import com.google.gson.annotations.SerializedName;

public class Anime {
    private String title;
    private int episodes;

    @SerializedName("images")
    private Images images;

    // Getters
    public String getTitle() {
        return title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getImageUrl() {
        return images != null ? images.getJpg().getImageUrl() : null;
    }

    public static class Images {
        @SerializedName("jpg")
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }
    }

    public static class Jpg {
        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
