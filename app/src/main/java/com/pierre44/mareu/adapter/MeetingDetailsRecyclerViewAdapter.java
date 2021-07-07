package com.pierre44.mareu.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pmeignen on 05/07/2021.
 */
public class MeetingDetailsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingDetailsRecyclerViewAdapter.GuestsViewHolder>  {

    private final List<User> mGuest;
    ViewGroup parents;



    public MeetingDetailsRecyclerViewAdapter(List<User> items) {
        this.mGuest = items;
    }

    @Override
    public GuestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_guest_fragment, parent, false);
        return new GuestsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(GuestsViewHolder holder, int position) {
        User user = mGuest.get(position);
        holder.mGuestDetail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        if (mGuest != null)
            return mGuest.size();
        else return 0;
    }

    public static class GuestsViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.guest_detail)
        TextView mGuestDetail;

        public GuestsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
