package dev.terry.services;

import dev.terry.entities.Meeting;

import java.util.List;

public interface MeetingService {
    public Meeting createMeeting(Meeting meeting);

    public List<Meeting> getAllMeetings();

    public Meeting getMeetingWithId(int id);
}
