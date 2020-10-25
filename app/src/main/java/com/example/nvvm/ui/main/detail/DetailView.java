package com.example.nvvm.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.nvvm.model.Cast;
import com.example.nvvm.model.Genre;
import com.example.nvvm.repository.MovieRepository;
import com.example.nvvm.repository.TVShowRepository;

import java.util.List;

public class DetailView extends ViewModel {
    private MovieRepository movieRepository;
    private TVShowRepository tvShowRepository;

    public DetailView() {
        movieRepository = MovieRepository.getInstance();
        tvShowRepository = TVShowRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id) {
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Genre>> getTvShowGenre(int id) {
        return tvShowRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getShowCast(int id) {
        return tvShowRepository.getCasts(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id) {
        return movieRepository.getCasts(id);
    }
}
