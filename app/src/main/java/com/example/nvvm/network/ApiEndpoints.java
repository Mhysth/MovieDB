package com.example.nvvm.network;
import com.example.nvvm.model.CastResponse;
import com.example.nvvm.model.GenreResponse;
import com.example.nvvm.model.MovieResponse;
import com.example.nvvm.model.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoints {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TVShowResponse> getTVShow(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<GenreResponse> getGenre(String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}")
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/credits")
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);
}