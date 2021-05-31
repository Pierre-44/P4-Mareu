package com.pierre44.mareu.ui_meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_list_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeetingRecyclerViewAdapter.ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
    }

    @Override
    public int getItemCount() { return mMeetings.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
        /** Todo comment faire le viewbinding ?
         *
         **/
    }
}
