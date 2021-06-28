package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.FilterByRoomAdapter;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

/**
 * Created by pmeignen on 28/06/2021.
 */
public class RoomDialogFragment extends DialogFragment {

    private FilterByRoomAdapter mFilterByRoomAdapter;
    private List<Room> mRooms;
    private MeetingRepository mMeetingRepository;

    RecyclerView mListRoom;

    public static RoomDialogFragment newInstance() {
        return new RoomDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_room_fragment, container);
        mListRoom = view.findViewById(R.id.list_room);

        mMeetingRepository = DI.getMeetingRepository();
        mRooms = mMeetingRepository.getRooms();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFilterByRoomAdapter = new FilterByRoomAdapter(mRooms);
        mListRoom.setAdapter(mFilterByRoomAdapter);
    }

}
