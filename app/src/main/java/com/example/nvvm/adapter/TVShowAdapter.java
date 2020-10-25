package com.example.nvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nvvm.R;
import com.example.nvvm.model.TVShow;
import com.example.nvvm.ui.main.tvShow.TVShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.CardViewViewHolder> {

    private Context context;
    private List<TVShow> listTVShow;

    private List<TVShow> getListTVShow() {
        return listTVShow;
    }

    public void setListTvShow(List<TVShow> listTvShow) {
        this.listTVShow = listTvShow;
    }

    public TVShowAdapter(Context context) {
        this.listTVShow = new ArrayList<TVShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TVShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new TVShowAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final TVShowAdapter.CardViewViewHolder holder, int position) {
        TVShow tvShow = getListTVShow().get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.img);
        holder.lbl_title.setText(tvShow.getName());
        holder.lbl_vote.setText(tvShow.getVote_average());
//        Log.d("Movie",movie.getPoster());

        holder.itemView.setOnClickListener(v -> {
            NavDirections action = TVShowFragmentDirections.actionDetailFragment(null, tvShow);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @NonNull
    @Override
    public int getItemCount() {
        return getListTVShow().size();
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView lbl_title;
        TextView lbl_vote;

        CardViewViewHolder(View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_vote = itemView.findViewById(R.id.lbl_vote);
            img = itemView.findViewById(R.id.lbl_gambar);

        }
    }
}