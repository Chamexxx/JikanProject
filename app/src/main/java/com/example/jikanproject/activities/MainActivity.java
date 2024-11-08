package com.example.jikanproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jikanproject.api_response.AnimeResponse;
import com.example.jikanproject.ApiService;
import com.example.jikanproject.R;
import com.example.jikanproject.RetrofitInstance;
import com.example.jikanproject.adapters.AnimeAdapter;
import com.example.jikanproject.metodos.AnimeData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private List<AnimeData> animeList = new ArrayList<>();
    private ProgressBar progressBar;
    private boolean isLoading = false;
    private int page = 1; // Página inicial
    private static final int PAGE_SIZE = 6; // Número de animes por página
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de vistas
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar); // Agregar el progress bar en el layout XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimeAdapter(animeList);
        recyclerView.setAdapter(adapter);

        // Inicialización de Retrofit
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        // Cargar la primera página de animes
        loadAnimeData(page);

        // Configurar el scroll listener para cargar más datos al final
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading && isLastItemDisplaying(recyclerView)) {
                    // Si no estamos cargando y hemos llegado al final de la lista, cargar más
                    page++;
                    loadAnimeData(page);
                }
            }
        });

        // Configurar el OnClickListener para cada anime
        adapter.setOnItemClickListener(new AnimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int animeId) {
                // Crear un Intent para abrir la actividad de detalles
                Intent intent = new Intent(MainActivity.this, AnimeDetailActivity.class);
                intent.putExtra("anime_id", animeId); // Pasar el animeId
                startActivity(intent);
            }
        });
    }

    // Método para verificar si hemos llegado al final del RecyclerView
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        assert layoutManager != null;
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        return lastVisibleItemPosition + 1 == totalItemCount;
    }

    // Método para cargar más datos desde la API
    private void loadAnimeData(int page) {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        apiService.getAnimes(PAGE_SIZE, page).enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getData() != null) {
                        List<AnimeData> newAnimes = response.body().getData();
                        animeList.addAll(newAnimes); // Agregar los nuevos animes a la lista existente
                        adapter.notifyDataSetChanged(); // Notificar al adaptador para que actualice la UI
                    }
                }
                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
