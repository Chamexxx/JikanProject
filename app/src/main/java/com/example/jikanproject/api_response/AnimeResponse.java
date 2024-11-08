package com.example.jikanproject.api_response;
import com.example.jikanproject.metodos.AnimeData;

import java.util.List;

public class AnimeResponse {
    private List<AnimeData> data;

    public List<AnimeData> getData() {
        return data;
    }

    public void setData(List<AnimeData> data) {
        this.data = data;
    }
}