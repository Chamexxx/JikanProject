package com.example.jikanproject.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jikanproject.ApiService;
import com.example.jikanproject.R;
import com.example.jikanproject.RetrofitInstance;
import com.example.jikanproject.adapters.EpisodeAdapter;
import com.example.jikanproject.api_response.AnimeDetailResponse;
import com.example.jikanproject.api_response.AnimeResponse;
import com.example.jikanproject.api_response.EpisodeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeDetailActivity extends AppCompatActivity {

    private int animeId;  // Para almacenar el ID del anime

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        // Obtener el ID del anime del Intent
        animeId = getIntent().getIntExtra("anime_id", -1);

        if (animeId == -1) {
            // Si no se pasó el ID correctamente, hacer algo (por ejemplo, mostrar un mensaje de error)
            Toast.makeText(this, "Anime ID no encontrado", Toast.LENGTH_SHORT).show();
        }

        // Llamada a la API para obtener los episodios del anime (suponiendo que tienes Retrofit configurado)
        loadEpisodes(animeId);
    }

    private void loadEpisodes(int animeId) {
        // Realiza la llamada a la API para obtener los episodios del anime con el ID
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<AnimeDetailResponse> call = apiService.getAnimes(animeId);

        call.enqueue(new Callback<AnimeDetailResponse>() {
            @Override
            public void onResponse(Call<AnimeDetailResponse> call, Response<AnimeDetailResponse> response) {
                if (response.isSuccessful()) {
                    // Procesa la respuesta y muestra los episodios
                    AnimeDetailResponse animeDetailResponse = response.body();
                    if (animeDetailResponse != null) {
                        // Actualiza la UI con los episodios
                    }
                } else {
                    // Si la respuesta no fue exitosa
                    Toast.makeText(AnimeDetailActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AnimeDetailResponse> call, Throwable t) {
                // Si la llamada falla
                Toast.makeText(AnimeDetailActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
