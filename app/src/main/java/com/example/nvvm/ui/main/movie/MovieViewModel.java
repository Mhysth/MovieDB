package com.example.nvvm.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.nvvm.model.Movie;
import com.example.nvvm.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository repository;
    public MovieViewModel(){
        repository = MovieRepository.getInstance();
    }
    public LiveData<List<Movie>> getMovieCollection(){
        return repository.getMovieCollection();
    }
}
