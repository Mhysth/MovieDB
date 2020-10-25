package com.example.nvvm.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.nvvm.model.Cast;
import com.example.nvvm.model.CastResponse;
import com.example.nvvm.model.Genre;
import com.example.nvvm.model.GenreResponse;
import com.example.nvvm.model.TVShow;
import com.example.nvvm.model.TVShowResponse;
import com.example.nvvm.network.RetrofitService;
import com.example.nvvm.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowRepository {
    private static TVShowRepository TVShowRepository;
    private RetrofitService service;
    private static final String TAG = "TVShowRepository";

    private TVShowRepository() {
        service = RetrofitService.getInstance();
    }

    public static TVShowRepository getInstance() {
        if (TVShowRepository == null) {
            TVShowRepository = new TVShowRepository();
        }
        return TVShowRepository;
    }


    public MutableLiveData<List<TVShow>> getTVShowCollection() {
        MutableLiveData<List<TVShow>> listTVShow = new MutableLiveData<>();

        service.getTVShow().enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listTVShow.postValue(response.body().getResults());
                    }

                }
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTVShow;
    }

    public MutableLiveData<List<Genre>> getGenres(int id) {
        MutableLiveData<List<Genre>> listGenres = new MutableLiveData<>();

        service.getGenre(Constants.Type.TV_SHOWS, id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getGenres().size());
                        listGenres.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listGenres;
    }

    public MutableLiveData<List<Cast>> getCasts(int id) {
        MutableLiveData<List<Cast>> listCasts = new MutableLiveData<>();

        service.getCast(Constants.Type.TV_SHOWS, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getCasts().size());
                        listCasts.postValue(response.body().getCasts());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCasts;
    }
}
