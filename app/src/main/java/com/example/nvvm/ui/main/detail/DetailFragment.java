package com.example.nvvm.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nvvm.R;
import com.example.nvvm.model.Genre;
import com.example.nvvm.model.Movie;
import com.example.nvvm.model.TVShow;
import com.example.nvvm.ui.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.back)
    ImageView background;

    @BindView(R.id.title_show)
    TextView title;

    @BindView(R.id.yearfilm)
    TextView year;

    @BindView(R.id.genrefilm)
    TextView genre;

    @BindView(R.id.agefilm)
    TextView adult;

    @BindView(R.id.descriptionfilm)
    TextView description;

    @BindView(R.id.imagefilm)
    ImageView cover;

    @BindView(R.id.ratingfilm)
    TextView vote;

    @BindView(R.id.rv_cast)
    RecyclerView rv;

    private Movie movie;
    private TVShow tvShow;
    private DetailView viewModel;
    private DetailCast adapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailView.class);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailCast(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();

            if (movie != null) {
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }

        }
    }

    private void observeShowViewModel(int idShow) {
        viewModel.getTvShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + "|");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });

        viewModel.getShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }
        });
    }

    private void observeMovieViewModel(int idMovie) {
        viewModel.getMovieGenre(idMovie).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + " | ");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });
        viewModel.getMovieCast(idMovie).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }
        });
    }

    private void initShow(TVShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getName());
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(cover);
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(background);
        adult.setVisibility(View.INVISIBLE);
        year.setText(tvShow.getReleaseDate());
        title.setText(tvShow.getName());
        vote.setText(tvShow.getVote_average());
        description.setText(tvShow.getDescription());
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getPoster()).into(cover);
        Glide.with(getActivity()).load(movie.getPoster()).into(background);
        adult.setVisibility(View.VISIBLE);
        if (movie.getAdult().equalsIgnoreCase("false")) {
            adult.setText("All age");
        } else {
            adult.setText("Adult");
        }
        title.setText(movie.getTitle());
        year.setText(movie.getReleaseDate());
        vote.setText(movie.getVote_average());
        description.setText(movie.getDescription());
    }

}