package com.example.nvvm.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.nvvm.model.TVShow;
import com.example.nvvm.repository.TVShowRepository;

import java.util.List;

public class TVShowViewModel extends ViewModel {

    private TVShowRepository repository;

    public TVShowViewModel() {
        repository = TVShowRepository.getInstance();
    }

    public LiveData<List<TVShow>> getTVShowCollection() {
        return repository.getTVShowCollection();
    }
}
