package com.example.nvvm.ui.main.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nvvm.R;
import com.example.nvvm.adapter.MovieAdapter;
import com.example.nvvm.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieFragment extends Fragment {

    @BindView(R.id.RecyclerView)
    RecyclerView rv;

    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new MovieAdapter(getContext());

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);
        Movie movie = new Movie();
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if (movies != null) {
            adapter.setListMovie(movies);
            adapter.notifyDataSetChanged();
            rv.setAdapter(adapter);
        }
    };
}