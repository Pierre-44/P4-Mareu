package com.pierre44.mareu.ui_meeting_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

import static java.security.AccessController.getContext;

public class MeetingViewHolder extends Fragment {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetingList;
    private RecyclerView mRecyclerView;
    private int position;
    private static final String VH_POSITION = "VIEW_HOLDER_POSITION";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingList = (List<Meeting>) DI.getMeetingRepository();
        position = getArguments().getInt(VH_POSITION);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_list_activity, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        return view;
    }



}
