package com.pierre44.mareu.di;

import com.pierre44.mareu.repository.DummyMeetingRepository;
import com.pierre44.mareu.repository.MeetingRepository;

/**
 * Created by pmeignen on 25/05/2021.
 */
public class DI {
    
    private static MeetingRepository repository = new DummyMeetingRepository();
    
    /**
     * Get an instance on @{@link MeetingRepository}
     * @return
     */
    public static MeetingRepository getMeetingRepository() {
        return repository;
    }

    /**
     * Get always a new instance on @{@link MeetingRepository}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingRepository getNewInstanceMeetingRepository() {
        return new DummyMeetingRepository();
    }
}

