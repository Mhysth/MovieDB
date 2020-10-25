package com.example.nvvm.network;

import com.example.nvvm.model.CastResponse;
import com.example.nvvm.model.GenreResponse;
import com.example.nvvm.model.MovieResponse;
import com.example.nvvm.model.TVShowResponse;
import com.example.nvvm.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private ApiEndpoints api;
    private static RetrofitService service;

    private RetrofitService() {
        api = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiEndpoints.class);

    }

    public static RetrofitService getInstance() {
        if (service == null) {
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies() {
        return api.getMovies(Constants.API_KEY);
    }

    public Call<TVShowResponse> getTVShow() {
        return api.getTVShow(Constants.API_KEY);
    }

    public Call<GenreResponse> getGenre(String type, int id) {
        return api.getGenres(type, id, Constants.API_KEY);
    }

    public Call<CastResponse> getCast(String type, int id) {
        return api.getCasts(type, id, Constants.API_KEY);
    }
}