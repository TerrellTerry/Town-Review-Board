package dev.terry.data;

import dev.terry.entities.Meeting;

import java.util.List;

public interface MeetingDAO {
    public Meeting createMeeting(Meeting meeting);

    public List<Meeting> getAllMeetings();

    public Meeting getMeetingWithId(int id);
}
