package com.example.nvvm.ui.main.tvShow;

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
import com.example.nvvm.adapter.TVShowAdapter;
import com.example.nvvm.model.Movie;
import com.example.nvvm.model.TVShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TVShowFragment extends Fragment {

    @BindView(R.id.RecyclerView)
    RecyclerView rv;

    private TVShowViewModel viewModel;
    private TVShowAdapter adapter;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new TVShowAdapter(getContext());

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = ViewModelProviders.of(requireActivity()).get(TVShowViewModel.class);
        viewModel.getTVShowCollection().observe(requireActivity(), observeViewModel);
        Movie movie = new Movie();

//        button.setOnClickListener(v -> {
//            NavDirections action = MovieFragmentDirections.actionDetailFragment(movie);
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private Observer<List<TVShow>> observeViewModel = TVshows -> {
        if (TVshows != null) {
//            Movie movie = movies.get(0);
//            button.setText(movie.getTitle());
//            Toast.makeText(requireActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();
            // set adapter
            adapter.setListTvShow(TVshows);
            adapter.notifyDataSetChanged();
            rv.setAdapter(adapter);
            // add adapter ro recyclerview
        }
    };
}