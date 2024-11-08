package com.example.jikanproject.api_response;

import java.util.List;

public class AnimeDetailResponse {
    private int mal_id;
    private String url;
    private String title;
    private String title_english;
    private String title_japanese;
    private String synopsis;
    private List<String> genres;
    private List<String> producers;
    private List<String> studios;
    // Otros campos según necesites

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
