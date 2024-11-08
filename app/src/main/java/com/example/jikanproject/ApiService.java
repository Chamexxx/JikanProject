package com.example.jikanproject;
import com.example.jikanproject.api_response.AnimeDetailResponse;
import com.example.jikanproject.api_response.AnimeResponse;
import com.example.jikanproject.api_response.EpisodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;


public interface ApiService {
    @GET("anime")
    Call<AnimeResponse> getAnimes(@Query("limit") int limit, @Query("page") int page);

    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getEpisodes(@Path("id") int animeId);

    @GET("anime/{id}")
    Call<AnimeDetailResponse> getAnimes(@Path("id") int animeId);
}

